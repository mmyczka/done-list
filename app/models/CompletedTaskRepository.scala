package models

import java.util.Date
import javax.inject.Inject

import anorm.SqlParser.{ get, scalar }
import anorm._
import play.api.db.DBApi

import scala.concurrent.Future
//import {CategoryRepository, Category}

case class CompletedTask(id: Option[Long] = None,
                    name: String,
                    reflections: Option[String],
                    categoryId: Option[Long],
                    termId: Option[Long],
                    achieved: Option[Date])

object CompletedTask {
  implicit def toParameters: ToParameterList[CompletedTask] =
    Macro.toParameters[CompletedTask]
}

/**
 * Helper for pagination.
 */
case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}


@javax.inject.Singleton
class CompletedTaskRepository @Inject()(dbapi: DBApi, categoryRepository: CategoryRepository, termRepository: TermRepository)(implicit ec: DatabaseExecutionContext) {

  private val db = dbapi.database("default")

  // -- Parsers

  /**
   * Parse a CompletedTask from a ResultSet
   */
  private val simple = {
    get[Option[Long]]("completed_task.id") ~
      get[String]("completed_task.name") ~
      get[Option[String]]("completed_task.reflections") ~
      get[Option[Long]]("completed_task.category_id") ~
      get[Option[Long]]("completed_task.term_id") ~
        get[Option[Date]]("completed_task.achieved") map {
      case id ~ name ~ reflections ~ categoryId ~ termId ~ achieved =>
        CompletedTask(id, name, reflections, categoryId, termId, achieved)
    }
  }

  /**
   * Parse a (CompletedTask,Category) from a ResultSet
   */
  private val withCategoryAndTerm = simple ~ (categoryRepository.simple.?) ~ (termRepository.simple.?) map {
    case completedTask ~ category ~ term => (completedTask, category, term)
  }

  // -- Queries

  /**
   * Retrieve a completed task from the id.
   */
  def findById(id: Long): Future[Option[CompletedTask]] = Future {
    db.withConnection { implicit connection =>
      SQL"select * from completed_task where id = $id".as(simple.singleOpt)
    }
  }(ec)

  /**
   * Return a page of (CompletedTask,Category).
   *
   * @param page Page to display
   * @param pageSize Number of computers per page
   * @param orderBy Computer property used for sorting
   * @param filter Filter applied on the name column
   */
  def list(page: Int = 0, pageSize: Int = 10, orderBy: Int = 1, filter: String = "%"): Future[Page[(CompletedTask, Option[Category], Option[Term])]] = Future {

    val offset = pageSize * page

    db.withConnection { implicit connection =>

      val computers = SQL"""
        select * from completed_task
        left join category on completed_task.category_id = category.id
        left join term on completed_task.term_id = term.id
        where completed_task.name like ${filter}
        order by ${orderBy} nulls last
        limit ${pageSize} offset ${offset}
      """.as(withCategoryAndTerm.*)

      val totalRows = SQL"""
        select count(*) from completed_task
        left join category on completed_task.category_id = category.id
        where completed_task.name like ${filter}
      """.as(scalar[Long].single)

      Page(computers, page, offset, totalRows)
    }
  }(ec)

  /**
   * Update a completedTask.
   *
   * @param id The completedTask id
   * @param completedTask The complitedTask values.
   */
  def update(id: Long, completedTask: CompletedTask) = Future {
    db.withConnection { implicit connection =>
      SQL("""
        update completed_task set name = {name}, achieved = {achieved}, 
          category_id = {categoryId}, term_id = {termId}, reflections = {reflections}
        where id = {id}
      """).bind(completedTask.copy(id = Some(id)/* ensure */)).executeUpdate()
      // case class binding using ToParameterList,
      // note using SQL(..) but not SQL.. interpolation
    }
  }(ec)

  /**
   * Insert a new completedTask.
   *
   * @param completedTask The completed task values.
   */
  def insert(completedTask: CompletedTask): Future[Option[Long]] = Future {
    db.withConnection { implicit connection =>
      SQL("""
        insert into completed_task values (
          (select next value for completed_task_seq),
          {name}, {reflections}, {categoryId}, {termId}, {achieved}
        )
      """).bind(completedTask).executeInsert()
    }
  }(ec)

  /**
   * Delete a completed task.
   *
   * @param id Id of the completed task to delete.
   */
  def delete(id: Long) = Future {
    db.withConnection { implicit connection =>
      SQL"delete from completed_task where id = ${id}".executeUpdate()
    }
  }(ec)

}

package models

import javax.inject.Inject

import scala.util.{ Failure, Success }

import anorm._
import anorm.SqlParser.{ get, str }

import play.api.db.DBApi

import scala.concurrent.Future

case class Category(id: Option[Long] = None, name: String)

@javax.inject.Singleton
class CategoryRepository @Inject()(dbapi: DBApi)(implicit ec: DatabaseExecutionContext) {

  private val db = dbapi.database("default")

  /**
   * Parse a Category from a ResultSet
   */
  private[models] val simple = {
    get[Option[Long]]("category.id") ~ str("category.name") map {
      case id ~ name => Category(id, name)
    }
  }

  /**
   * Construct the Seq[(String,String)] needed to fill a select options set.
   *
   * Uses `SqlQueryResult.fold` from Anorm streaming,
   * to accumulate the rows as an options list.
   */
  def options: Future[Seq[(String,String)]] = Future(db.withConnection { implicit connection =>
    SQL"select * from category order by name".
      fold(Seq.empty[(String, String)], ColumnAliaser.empty) { (acc, row) => // Anorm streaming
        row.as(simple) match {
          case Failure(parseErr) => {
            println(s"Fails to parse $row: $parseErr")
            acc
          }

          case Success(Category(Some(id), name)) =>
            (id.toString -> name) +: acc

          case Success(Category(None, _)) => acc
        }
      }
  }).flatMap {
    case Left(err :: _) => Future.failed(err)
    case Left(_) => Future(Seq.empty)
    case Right(acc) => Future.successful(acc.reverse)
  }
}

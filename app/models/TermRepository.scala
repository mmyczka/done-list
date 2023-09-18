package models

import javax.inject.Inject

import scala.util.{ Failure, Success }

import anorm._
import anorm.SqlParser.{ get, str }

import play.api.db.DBApi

import scala.concurrent.Future

case class Term(id: Option[Long] = None, name: String)

@javax.inject.Singleton
class TermRepository @Inject()(dbapi: DBApi)(implicit ec: DatabaseExecutionContext) {

  private val db = dbapi.database("default")

  /**
   * Parse a Term from a ResultSet
   */
  private[models] val simple = {
    get[Option[Long]]("term.id") ~ str("term.name") map {
      case id ~ name => Term(id, name)
    }
  }

  /**
   * Construct the Seq[(String,String)] needed to fill a select options set.
   *
   * Uses `SqlQueryResult.fold` from Anorm streaming,
   * to accumulate the rows as an options list.
   */
  def options: Future[Seq[(String,String)]] = Future(db.withConnection { implicit connection =>
    SQL"select * from term order by id".
      fold(Seq.empty[(String, String)], ColumnAliaser.empty) { (acc, row) => // Anorm streaming
        row.as(simple) match {
          case Failure(parseErr) => {
            println(s"Fails to parse $row: $parseErr")
            acc
          }

          case Success(Term(Some(id), name)) =>
            (id.toString -> name) +: acc

          case Success(Term(None, _)) => acc
        }
      }
  }).flatMap {
    case Left(err :: _) => Future.failed(err)
    case Left(_) => Future(Seq.empty)
    case Right(acc) => Future.successful(acc.reverse)
  }
}

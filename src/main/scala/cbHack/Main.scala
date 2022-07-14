package cbHack

import akka.actor.ActorSystem
import slick.dbio.DBIO
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.PostgresProfile

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("chemblHack")
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  println("Hello")
  val db = Database.forConfig("postgres")

  case class Domains(
                      domain_id: Int,
                      domain_type: String,
                      source_domain_id: String,
                      domain_name: String,
                      domain_description: String
                    )

  class SlickTables(val profile: PostgresProfile) {

    import profile.api._

    class DomainTable(tag: Tag) extends Table[Domains](tag, Some("domains"), "domains") {
      def domain_id = column[Int]("domain_id", O.PrimaryKey, O.AutoInc)
      def domain_type = column[String]("domain_type")
      def source_domain_id = column[String]("source_domain_id")
      def domain_name = column[String]("domain_name")
      def domain_description = column[String]("domain_description")

      override def * = (domain_id, domain_type, source_domain_id, domain_name, domain_description) <> (Domains.tupled, Domains.unapply)
    }

    lazy val domainsTable = TableQuery[DomainTable]

  }

  object SlickTables extends SlickTables(PostgresProfile)


  val getTableNamesQuery: DBIO[Seq[String]] = {
    //    sql""" select "name" from "Tables.public" where table_type = "base table" and table_schema = "chemblDb" """.as[String]
    sql""" select "domains" from "activities" limit 20 """.as[String]
  }

  val tableNames: Future[Seq[String]] = db.run(getTableNamesQuery)

  tableNames.onComplete {
    case Success(names) => names.foreach(println(_))
    case Failure(t) => println("Error.." + t.getMessage)
  }
}

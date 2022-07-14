package cbHack

import cbHack.Main.{Domains, SlickTables, db}
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.Future

class QueryOps {
  val db = Database.forConfig("postgres")

  val domains: Future[Seq[Domains]] = db.run(SlickTables.DomainTable.result)
}

package cbHack



import slick.jdbc.PostgresProfile

class Schema {
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
      def domain_id= column[Int]("domain_id", O.PrimaryKey, O.AutoInc)
      def domain_type = column[String]("domain_type")
      def source_domain_id = column[String]("source_domain_id")
      def domain_name = column[String]("domain_name")
      def domain_description = column[String]("domain_description")
      override def * = (domain_id, domain_type, source_domain_id, domain_name, domain_description) <> (Domains.tupled, Domains.unapply)
    }

    lazy val domainsTable = TableQuery[DomainTable]
  }
}

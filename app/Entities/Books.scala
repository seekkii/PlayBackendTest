package Entities

import scalikejdbc._



class Books{

}

object book {
  Class.forName("org.h2.Driver")
  ConnectionPool.singleton("jdbc:h2:mem:play;DB_CLOSE_DELAY=-1", "root","" )
  implicit val session = AutoSession

  val name = "Alice"
  // implicit session represents java.sql.Connection
  /*val memberId: Option[Long] = DB readOnly { implicit session =>
    sql"select id from members where name = ${name}" // don't worry, prevents SQL injection
      .map(rs => rs.long("id")) // extracts values from rich java.sql.ResultSet
      .single                   // single, list, traversable
      .apply()                  // Side effect!!! runs the SQL using Connection
  }

   */
  def main(args : Array[String]): Unit = {
    sql"""
        create table members (
        id serial not null primary key,
        name varchar(64),
        created_at timestamp not null
        )""".execute.apply()
  }
}


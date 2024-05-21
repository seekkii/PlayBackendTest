import scalikejdbc._

// initialize JDBC driver & connection pool
Class.forName("com.mysql.cj.jdbc.Driver")
ConnectionPool.singleton("jdbc:mysql://127.0.0.1/ManagementSystem", "root", "")

// ad-hoc session provider on the REPL
implicit val session: DBSession = AutoSession

// table creation, you can run DDL by using #execute as same as JDBC
sql"""
create table members  (
  userid serial not null primary key,
  role varchar(10) not null,
  username varchar(100) not null,
  email varchar(100) not null,
  slack_name varchar(100) not null,
  password varchar(255) not null,
  created_at timestamp not null,
  updated_at timestamp not null
)
""".execute.apply()

Seq("Alice", "Bob", "Chris") foreach { name =>
  sql"insert into users (role, username, created_at, updated_at) values (${"Members"}, ${name}, NOW(), NOW())".update.apply()
}

def getUserId(username: String): Option[Long] = {
  sql"select userid from users where username = $username".map(_.long("userid")).single.apply()
}

sql"""
create table request_order (
  request_id bigint not null primary key,
  created_at timestamp not null,
  updated_at timestamp not null,
  user_id serial not null,
  # book_id bigint not null,
  foreign key (user_id) references members(userid)
  # foreign key (book_id) references books(bookid)
)
""".execute.apply()

Seq("Alice", "Bob", "Chris") foreach { name =>
  sql"insert into request_order (request_id, created_at, updated_at, user_id) values (NOW(), NOW(),${getUserId(name)} )".update.apply()
}

import java.sql.Timestamp

// Insert requests
val aliceId = getUserId("Alice").get
val bobId = getUserId("Bob").get
val chrisId = getUserId("Chris").get

Seq(aliceId, bobId, chrisId) foreach { userId =>
  sql"insert into request_order (created_at, updated_at, user_id) values (NOW(), NOW(), ${userId}".update.apply()
}




/*
Seq("Alice", "Bob", "Chris") foreach { name =>
  sql"insert into members (name, created_at) values (${name}, current_timestamp)".update.apply()
}

sql"""
create borrowing_history  (
  id serial not null primary key,
  name varchar(64),
  created_at timestamp not null
)
""".execute.apply()

Seq("Alice", "Bob", "Chris") foreach { name =>
  sql"insert into members (name, created_at) values (${name}, current_timestamp)".update.apply()
}

sql"""
  id serial not null primary key,
create book (
  name varchar(64),
  created_at timestamp not null
)
""".execute.apply()

// insert initial data
Seq("Alice", "Bob", "Chris") foreach { name =>
  sql"insert into members (name, created_at) values (${name}, current_timestamp)".update.apply()
}
sql"""
create user  (
  id serial not null primary key,
  name varchar(64),
  created_at timestamp not null
)
""".execute.apply()

Seq("Alice", "Bob", "Chris") foreach { name =>
  sql"insert into members (name, created_at) values (${name}, current_timestamp)".update.apply()
}

sql"""
create category  (
  id serial not null primary key,
  name varchar(64),
  created_at timestamp not null
)
""".execute.apply()

Seq("Alice", "Bob", "Chris") foreach { name =>
  sql"insert into members (name, created_at) values (${name}, current_timestamp)".update.apply()
}

 */





// for now, retrieves all data as Map value
val entities: List[Map[String, Any]] = sql"select * from members".map(_.toMap).list.apply()

// defines entity object and extractor
import java.time._
case class Member(id: Long, name: Option[String], createdAt: ZonedDateTime)
object Member extends SQLSyntaxSupport[Member] {
  override val tableName = "users"
  def apply(rs: WrappedResultSet) = new Member(
    rs.long("id"), rs.stringOpt("name"), rs.zonedDateTime("created_at"))
}

// find all members
val members: List[Member] = sql"select * from members".map(rs => Member(rs)).list.apply()

// use paste mode (:paste) on the Scala REPL
val m = Member.syntax("m")
val name = "Alice"
val alice: Option[Member] = withSQL {
  select.from(Member as m).where.eq(m.name, name)
}.map(rs => Member(rs)).single.apply()
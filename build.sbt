name := """play-scala-seed"""
organization := "com.backend"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.14"

libraryDependencies += guice

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test

libraryDependencies ++= {
  val pekkoHttpV     = "1.0.1"
  val pekkoV         = "1.0.2"
  val pekkoHttpJsonV = "2.0.0"
  Seq(
    "org.apache.pekko"     %% "pekko-actor"        % pekkoV,
    "org.apache.pekko"     %% "pekko-stream"       % pekkoV,
    "org.apache.pekko"     %% "pekko-http"         % pekkoHttpV,
    "org.apache.pekko"     %% "pekko-testkit"      % pekkoV % "test",
    "org.apache.pekko"     %% "pekko-http-testkit" % pekkoHttpV % "test",
    "com.github.pjfanning" %% "pekko-http-circe"   % pekkoHttpJsonV,
  )
}

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18" % Test
libraryDependencies += "com.typesafe.play" %% "play-test" % "2.9.3" % Test

libraryDependencies ++= Seq(
  "org.scalatestplus" %% "mockito-5-10" % "3.2.18.0" % Test,
  "org.scalatest" %% "scalatest" % "3.2.18" % Test
)
/*
lazy val scalikejdbcVersion = "3.5.0"

// published dependency version
lazy val defaultPlayVersion = play.core.PlayVersion.current

// internal only
lazy val h2Version = "2.2.224"

lazy val commonSettings = Seq(
  scalaVersion := "2.13.14",
  crossScalaVersions := Seq("2.13.14", "3.3.3"),
  libraryDependencySchemes += "org.scala-lang.modules" %% "scala-parser-combinators" % "always",
  Test / fork := true,
  javaOptions ++= {
    if (scala.util.Properties.isWin) {
      Seq("-Dfile.encoding=Windows-31J")
    } else {
      Nil
    }
  },
  scalacOptions ++= Seq("-deprecation", "-unchecked")
)

commonSettings

lazy val baseSettings = commonSettings ++ Seq(
  organization := "org.scalikejdbc",
  version := "2.8.0-scalikejdbc-3.5",
  publishTo := (
    if (isSnapshot.value)
      None
    else
      Some(Opts.resolver.sonatypeStaging)
    ),
  publishMavenStyle := true,
  libraryDependencies += "org.specs2" %% "specs2-core" % "4.20.5" % "test",
  Global / transitiveClassifiers := Seq(Artifact.SourceClassifier),
  Test / publishArtifact := false,
  pomIncludeRepository := { x => false },
)

// scalikejdbc-play-initializer
lazy val scalikejdbcPlayInitializer = Project(
  id = "play-initializer",
  base = file("scalikejdbc-play-initializer")
).settings(
  baseSettings,
  name := "scalikejdbc-play-initializer",
  libraryDependencies ++= Seq(
    "org.scalikejdbc" %% "scalikejdbc" % scalikejdbcVersion % "provided",
    "org.scalikejdbc" %% "scalikejdbc-config" % scalikejdbcVersion % "provided",
    "org.playframework" %% "play" % defaultPlayVersion % "provided",
    // play-jdbc is needed to test with DBApi
    "org.playframework" %% "play-jdbc" % defaultPlayVersion % "test",
    "org.playframework" %% "play-test" % defaultPlayVersion % "test",
    "com.h2database" % "h2" % h2Version % "test",
    guice % "test"
  ),
)

// scalikejdbc-play-dbapi-adapter
lazy val scalikejdbcPlayDBApiAdapter = Project(
  id = "play-dbapi-adapter",
  base = file("scalikejdbc-play-dbapi-adapter")
).settings(
  baseSettings,
  name := "scalikejdbc-play-dbapi-adapter",
  libraryDependencies ++= Seq(
    "org.scalikejdbc" %% "scalikejdbc" % scalikejdbcVersion % "provided",
    "org.scalikejdbc" %% "scalikejdbc-config" % scalikejdbcVersion % "provided",
    "org.playframework" %% "play" % defaultPlayVersion % "provided",
    "org.playframework" %% "play-jdbc" % defaultPlayVersion % "compile",
    "org.playframework" %% "play-test" % defaultPlayVersion % "test",
    "com.h2database" % "h2" % h2Version % "test",
    guice % "test"
  ),
)

// scalikejdbc-play-fixture
lazy val scalikejdbcPlayFixture = Project(
  id = "play-fixture",
  base = file("scalikejdbc-play-fixture")
).settings(
  baseSettings,
  name := "scalikejdbc-play-fixture",
  libraryDependencies ++= Seq(
    "org.scalikejdbc" %% "scalikejdbc" % scalikejdbcVersion % "provided",
    "org.scalikejdbc" %% "scalikejdbc-config" % scalikejdbcVersion % "provided",
    "org.playframework" %% "play" % defaultPlayVersion % "provided",
    "org.playframework" %% "play-test" % defaultPlayVersion % "test",
    "com.h2database" % "h2" % h2Version % "test"
  ),
  Test / testOptions += Tests
    .Argument(TestFrameworks.Specs2, "sequential", "true"),
).dependsOn(scalikejdbcPlayInitializer)

 */



libraryDependencies ++= Seq(
  "com.h2database"  %  "h2"                           % "2.2.224", // your jdbc driver here
//  "org.scalikejdbc" %% "scalikejdbc"                  % "4.3.0",
  "org.scalikejdbc" %% "scalikejdbc"                  % "3.5.0",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "3.5.0",
//  "org.scalikejdbc" %% "scalikejdbc-config"           % "4.3.0",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.5"
    //libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.33"
    //"org.scalikejdbc" %% "scalikejdbc-play-initializer" % "3.0.1-scalikejdbc-4.3"
)

// https://mvnrepository.com/artifact/mysql/mysql-connector-java
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.33"





libraryDependencies ++= Seq(ws, evolutions, jdbc)


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.backend.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.backend.binders._"

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

libraryDependencies += ws



// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.backend.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.backend.binders._"

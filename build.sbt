ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

val akkaHttpVersion = "10.2.4"
val akkaStreamVersion = "2.6.11"

lazy val root = (project in file("."))
  .settings(
    name := "chemblHack"
  )


libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.postgresql" % "postgresql" % "42.2.14",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  "com.typesafe.akka"          %% "akka-http"            % akkaHttpVersion,
  "com.typesafe.akka"          %% "akka-stream-testkit"  % akkaStreamVersion,
  "com.typesafe.akka"          %% "akka-http-testkit"    % akkaHttpVersion,
  "com.typesafe.akka"          %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka"          %% "akka-stream"          % akkaStreamVersion,
)

val ApplicationVersion = "0.1.0-SNAPSHOT"
val AkkaVersion = "2.6.19"
val AkkaHttpVersion = "10.2.9"
val ScalaVersion = "2.13.8"

val akkaDependencies = Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion
)

libraryDependencies ++= akkaDependencies

addCommandAlias("lint-fmt", "scalafixAll; scalafmtAll")

lazy val root = (project in file("."))
  .settings(
    scalaVersion := ScalaVersion,
    version := ApplicationVersion,
    name := "lrad-backend",
    scalacOptions += "-Ywarn-unused:imports",
    inThisBuild(
      Seq(
        semanticdbEnabled := true,
        semanticdbVersion := scalafixSemanticdb.revision,
        scalafixScalaBinaryVersion := "2.13",
      )
    )
  )

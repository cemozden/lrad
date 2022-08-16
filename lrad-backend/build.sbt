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

val otherDependencies = Seq(
  "com.typesafe" % "config" % "1.4.2"
)

val dbDependencies = Seq(
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"
)

val loggingDependencies = Seq(
  "ch.qos.logback" % "logback-core" % "1.2.11",
  "org.slf4j" % "slf4j-api" % "1.7.36",
)

val slickDependencies = Seq(
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  "com.typesafe.slick" %% "slick-codegen" % "3.3.3"
)

libraryDependencies ++= akkaDependencies ++ slickDependencies ++ loggingDependencies ++ otherDependencies ++ dbDependencies

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

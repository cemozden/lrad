addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.10.1")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.6")

dependencyOverrides += "ch.epfl.scala" % "scalafix-interfaces" % "0.10.1"
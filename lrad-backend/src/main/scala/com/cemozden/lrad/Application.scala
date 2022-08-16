package com.cemozden.lrad

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.cemozden.lrad.http.routes.V1Routes
import com.typesafe.config.{Config, ConfigFactory}

import scala.io.StdIn
import slick.jdbc.PostgresProfile.api._

object Application extends App {

  val (host, serverPort) = ("localhost", 8080)
  private implicit val system: ActorSystem = ActorSystem("lrad-system")
  private implicit val config: Config = ConfigFactory.load()
  private val db = Database.forConfig("lrad.database")

  import system.dispatcher

  private val bindingFuture = Http()
    .newServerAt("localhost", serverPort)
    .bindFlow(V1Routes.routes)

  system.registerOnTermination {
    println("Shutting down the LRAD server!")
    db.close()
  }

  println(s"LRAD server is now online at http://$host:$serverPort/")
  StdIn.readLine()

  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}

package com.cemozden.lrad

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.cemozden.lrad.Routes.TopLevelRoute
import java.util.UUID

case class CompanyId(id: UUID) extends AnyVal

object Application extends App {

  private val ServerPort = 8080
  private implicit val system: ActorSystem = ActorSystem("my-system")
  import system.dispatcher

  Http().newServerAt("localhost", ServerPort).bind(TopLevelRoute).recoverWith { case error =>
    println(s"Error occurred. ${error.getMessage}")
    system.terminate().map(_ => ())
  }

  println(s"LRAD server is now online at http://localhost:$ServerPort/")
}

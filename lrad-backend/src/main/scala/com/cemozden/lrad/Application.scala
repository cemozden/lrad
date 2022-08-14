package com.cemozden.lrad

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.cemozden.lrad.http.routes.V1Routes

import java.util.UUID
import scala.concurrent.duration.DurationInt
import scala.concurrent.Future
import scala.io.StdIn
import scala.language.postfixOps

case class CompanyId(id: UUID) extends AnyVal

object Application extends App {

  val (host, serverPort) = ("localhost", 8080)
  val CurrentRouteVersion = "v1"
  private implicit val system: ActorSystem = ActorSystem("lrad-system")

  import system.dispatcher

  val bindingFuture: Future[Any] = Http()
    .newServerAt("localhost", serverPort)
    .bindFlow(V1Routes.routes)
    .map(_.addToCoordinatedShutdown(hardTerminationDeadline = 10 seconds))
    .recoverWith { case error =>
      println(s"Error occurred. ${error.getMessage}")
      system.terminate().map(_ => ())
    }

  println(s"LRAD server is now online at http://localhost:$serverPort/")
  StdIn.readLine()
}

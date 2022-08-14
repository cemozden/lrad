package com.cemozden.lrad.http.routes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

object V1Routes extends HttpRoute {

  val version: String = "v1"

  def routeTree: Route = concat(
    pathPrefix("feed") {
      concat(
        pathPrefix("add-feed") {
          post {
            complete(HttpEntity(ContentTypes.`application/json`, "{\"added\": false}"))
          }
        },
        pathPrefix("update-feed") {
          put {
            complete(HttpEntity(ContentTypes.`application/json`, "{\"updated\": false}"))
          }
        },
        pathPrefix("delete-feed") {
          post {
            complete(HttpEntity(ContentTypes.`application/json`, "{\"deleted\": false}"))
          }
        }
      )
    }
  )
}

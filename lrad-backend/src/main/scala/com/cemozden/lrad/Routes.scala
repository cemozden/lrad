package com.cemozden.lrad

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

object Routes {

  val TopLevelRoute: Route = concat(
    pathPrefix("feed") {
      get {
        complete(HttpEntity(ContentTypes.`application/json`, "{\"added\": true}"))
      }
    }
  )

}

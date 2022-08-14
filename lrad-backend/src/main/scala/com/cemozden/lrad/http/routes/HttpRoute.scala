package com.cemozden.lrad.http.routes

import akka.http.scaladsl.server.Directives.pathPrefix
import akka.http.scaladsl.server.Route

trait HttpRoute {

  val version: String

  private[routes] def routeTree: Route

  def routes: Route = pathPrefix(version) {
    routeTree
  }
}

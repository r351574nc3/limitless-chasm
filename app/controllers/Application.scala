package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action { request =>

    val source = request.getQueryString("state")
    val target = request.session.get("saved_state")

    val loggedIn = source eq target

    if (loggedIn) {
      Ok(views.html.index("Kualigan Customization Portal")).withSession(request.session + ("logged_in", "yes") - "saved_state" )
    }
    else {
      Ok(views.html.index("Kualigan Customization Portal"))
    }
  }
}

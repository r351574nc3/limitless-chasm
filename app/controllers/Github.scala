package controllers

import play.api._
import play.api.mvc._
import play.api.libs.oauth._
import java.util.UUID;

object Github extends Controller {

  val AUTHORIZE_URL = "https://github.com/login/oauth/authorize?client_id=%s&redirect_url=%s&state=%s"
  val CLIENT_ID     = "e54ff900cfe66c5510f1"
  val REDIRECT_URL  = "http://localhost:9000/";

  def authorize = Action { request =>
    val state = UUID.randomUUID().toString()
    Redirect(getAuthorizeUrl(state)).withSession(request.session + ("saved_state", state))
  }

  def getAuthorizeUrl(state : String) : String = {
    String.format(AUTHORIZE_URL, CLIENT_ID, REDIRECT_URL, state)
  }

  def isLoggedIn(implicit request: RequestHeader): Boolean = {
    var loggedIn = request.session.get("logged_in")
    loggedIn match {
      case Some(t) => return true
      case None    => return false
    }
  }
}

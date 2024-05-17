package controllers

import play.api.libs.ws.{WSClient, WSRequest}
import play.api.mvc._

import java.io.File
import javax.inject._
import scala.concurrent.duration.DurationInt

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(ws: WSClient, val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    val getRequest: WSRequest =
        ws.url("http://localhost:9000/test")
          .addHttpHeaders("Accept" -> "application/json")
          .addQueryStringParameters("search" -> "play")
          .withRequestTimeout(10000.millis)
    getRequest.get()

    val postRequest: WSRequest =
      ws.url("http://localhost:9000/test")
        .addHttpHeaders("Accept" -> "application/json")
        .addQueryStringParameters("search" -> "play")
        .withRequestTimeout(10000.millis)

    val file = new File("Module.scala")
    postRequest.post(file)
    Ok(views.html.index())
  }
}

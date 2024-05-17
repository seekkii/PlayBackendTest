package controllers
//import akka.actor.{ActorRef, ActorSystem}
//import akka.stream.Materializer
//import akka.stream.scaladsl.{Sink, Source}

import org.apache.pekko.actor.ActorRef
import org.apache.pekko.stream.Materializer
import org.apache.pekko.stream.scaladsl.{Sink, Source}
import play.api.mvc._

import javax.inject.{Inject, Named, Singleton}
import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TestController @Inject()
(
  @Named("configured-actor") configuredActor: ActorRef,
  components: ControllerComponents
)(implicit val materializer: Materializer) extends AbstractController(components)
  {
    implicit val ec: ExecutionContext = controllerComponents.executionContext

    private def handleGET(): Action[AnyContent] = Action.async{ implicit request:Request[AnyContent] =>
      val source = Source.single(request.hasBody.toString)
      val sink = Sink.foreach[String](println)
      val getFuture = source.runWith(sink)(materializer).map(i => Ok("get" + i))
      getFuture.recover {
        case ex => BadRequest("Failed to process GET request" + ex)
      }
    }

    private def handlePOST(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
      val source = Source.single(request.body.toString)
      val sink = Sink.foreach[String](println)
      val postFuture = source.runWith(sink)(materializer).map { i =>
        Ok("post" + i)
      }.recover {
        case ex => BadRequest("Failed to process POST request: " + ex.getMessage)
      }
      postFuture
    }


    def handleRequest() : Action[AnyContent] = Action.async{implicit request: Request[AnyContent] =>
      request.method match{
        case "GET" => handleGET()(request)
        case "POST" => handlePOST()(request)
        case _ => Future.successful(MethodNotAllowed("Supported method : GET, POST"))
      }
    }
  }


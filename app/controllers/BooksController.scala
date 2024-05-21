package controllers

import org.apache.pekko.actor.ActorRef
import org.apache.pekko.stream.Materializer
import org.apache.pekko.stream.scaladsl.{Sink, Source}
import play.api.libs.json
import play.api.libs.json.JsPath.\
import play.api.libs.json.JsValue
import play.api.mvc._
import play.libs.Json
import scalikejdbc.{AutoSession, DBSession, scalikejdbcSQLInterpolationImplicitDef}

import javax.inject.{Inject, Named, Singleton}
import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class BooksController @Inject()
(@Named("configured-actor") configuredActor: ActorRef, components: ControllerComponents)
(implicit val materializer: Materializer) extends AbstractController(components)
  {
    implicit val ec: ExecutionContext = controllerComponents.executionContext
    implicit val session: DBSession = AutoSession

    private def handleGET(): Action[AnyContent] = Action.async{ implicit request:Request[AnyContent] =>
      val source = Source.single(request.hasBody.toString)
      val sink = Sink.foreach[String](println)
      val getFuture = source.runWith(sink)(materializer).map(i => Ok("get" + i))
      getFuture.recover {
        case ex => BadRequest("Failed to process GET request" + ex)
      }
    }

    private def handlePOST(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
      val bodyAsJson =  request.body.asJson
      bodyAsJson match {
        case Some(json)=>{
          val bookName = (json \ "name").toOption
          val name = (json \"author").toOption
          val purchasedDate = (json\"purchasedDate").toOption
          val description = (json\"description").toOption
          val categoryId = (json\"categoryId").toOption
        }
        case None => println("Empty body")
      }

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
      val accounts = {
        try sql"select * from accounts".toMap.list.apply()
        catch { case e: Exception =>
          sql"create table accounts(name varchar(100) not null)".execute.apply()
          Seq("Alice", "Bob", "Chris").foreach { name =>
            sql"insert into accounts values ($name)".update.apply()
          }
          sql"select * from accounts".toMap.list.apply()
        }
      }
      request.method match{
        case "GET" => handleGET()(request)
        case "POST" => handlePOST()(request)
        case _ => Future.successful(MethodNotAllowed("Supported method : GET, POST"))
      }
    }
  }



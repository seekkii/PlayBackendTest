package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.Status.OK
import play.api.test.Helpers._
import play.api.test._
import org.apache.pekko.actor.{ActorRef, ActorSystem}
import org.apache.pekko.stream.Materializer
import org.apache.pekko.stream.scaladsl.{Sink, Source}
import org.apache.pekko.stream.Materializer
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.mockito.MockitoSugar
import play.api.Play.materializer
import play.api.http.HttpErrorHandlerExceptions
import org.mockito.Mockito._
import play.api.libs.ws
import play.api.libs.ws.WSRequest
import play.api.mvc.{Result, Results}
import play.libs.ws
import play.libs.ws.WSResponse


/*
class TestControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting{
  "TestController#index" should {
    "return 'Hello, world!'" in {
      val request = FakeRequest(GET, "/test")
      val result = route(app, request).get

      status(result) mustBe OK
      contentType(result) mustBe Some("text/plain")
      contentAsString(result) must include("Hello, world!")
    }
  }
}
*/


class BooksControllerSpec extends PlaySpec with MockitoSugar with Results {
  implicit val as: ActorSystem = ActorSystem("testControllerActorSystem")
  implicit val materializer = Materializer(as)
  "TestController" should {
    "handle GET requests properly" in {
      val mockConfiguredActor = mock[ActorRef]
      val controller = new BooksController(mockConfiguredActor, stubControllerComponents())(materializer)
      // Setup the request
      val request = FakeRequest(GET, "/test")

      // Call the controller action
      val result = controller.handleRequest()(request)

      // Assert the response
      status(result) mustEqual OK
      contentAsString(result) must include("get")
    }

    "handle POST requests properly" in {
      val mockMaterializer = mock[Materializer]
      val mockConfiguredActor = mock[ActorRef]

      // Mocking implicit parameter
      implicit val mockMaterializerImplicit: Materializer = mockMaterializer

      // Create the controller instance with the mocked dependencies
      val controller = new BooksController(mockConfiguredActor, stubControllerComponents())(materializer)

      val request = FakeRequest(POST, "/test")

      // Call the controller action
      val result = controller.handleRequest()(request)

      // Assert the response
      status(result) mustEqual OK
      contentAsString(result) must include("post")
    }
  }
}









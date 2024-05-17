package actors

import org.apache.pekko.actor.Actor
import play.api.Configuration

import javax.inject.Inject

object ConfiguredActor {
  case object GetConfig
}

class ConfiguredActor @Inject() (configuration: Configuration) extends Actor {
  import ConfiguredActor._

  val config = configuration.getOptional[String]("my.config").getOrElse("none")

  def receive = {
    case GetConfig =>
      sender() ! config
  }
}
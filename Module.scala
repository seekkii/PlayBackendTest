import actors.ConfiguredActor
import org.apache.pekko.actor._

import javax.inject._
import org.apache.pekko.actor._
import play.api.Configuration
import com.google.inject.AbstractModule
import play.api.libs.concurrent.PekkoGuiceSupport

class Module extends AbstractModule with PekkoGuiceSupport {
  override def configure = {
    bindActor[ConfiguredActor]("configured-actor")
  }
}
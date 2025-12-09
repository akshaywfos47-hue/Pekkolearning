package com.example.cluster

package com.example

import org.apache.pekko.actor.typed.*
import org.apache.pekko.actor.typed.scaladsl.*
import org.apache.pekko.actor.typed.receptionist.*

object Receiver {

  sealed trait Command
  case class Message(text: String) extends Command

  // ✅ Service Key for discovery
  val Key: ServiceKey[Command] = ServiceKey("receiver-service")

  def apply(): Behavior[Command] =
    Behaviors.setup { ctx =>

      // ✅ Register this actor with Receptionist
      ctx.system.receptionist ! Receptionist.Register(Key, ctx.self)

      Behaviors.receiveMessage {
        case Message(text) =>
          ctx.log.info(s"✅ Receiver got: $text")
          Behaviors.same
      }
    }
}

@main def receiverMain(): Unit = {
  val system = ActorSystem(Receiver(), "ClusterSystem")

  println("✅ Receiver node started. Press ENTER to stop...")
  scala.io.StdIn.readLine()   // ✅ Keeps JVM alive
  system.terminate()
}


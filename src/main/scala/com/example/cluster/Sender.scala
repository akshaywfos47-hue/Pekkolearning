package com.example.cluster

package com.example

import org.apache.pekko.actor.typed.*
import org.apache.pekko.actor.typed.scaladsl.*
import org.apache.pekko.actor.typed.receptionist.*

object Sender {

  sealed trait Command
  case object Start extends Command
  private case class ListingResponse(listing: Receptionist.Listing) extends Command

  def apply(): Behavior[Command] =
    Behaviors.setup { ctx =>

      val adapter = ctx.messageAdapter[Receptionist.Listing](ListingResponse.apply)

      Behaviors.receiveMessage {
        case Start =>
          // ✅ Ask receptionist for receivers
          ctx.system.receptionist ! Receptionist.Find(Receiver.Key, adapter)
          Behaviors.same

        case ListingResponse(Receiver.Key.Listing(refs)) =>
          refs.foreach { ref =>
            ref ! Receiver.Message("Hello from Sender ✅")
          }
          Behaviors.same
      }
    }
}

@main def run(): Unit = {

  val system = ActorSystem(Behaviors.empty, "ClusterSystem")

  val sender = system.systemActorOf(Sender(), "sender")

  sender ! Sender.Start

  println("✅ Sender started. Press ENTER to stop...")
  scala.io.StdIn.readLine()
  system.terminate()
}


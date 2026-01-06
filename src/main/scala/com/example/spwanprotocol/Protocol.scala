package com.example.spwanprotocol

import org.apache.pekko.actor.typed._
import org.apache.pekko.actor.typed.scaladsl._
import org.apache.pekko.actor.typed.SpawnProtocol
import org.apache.pekko.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import org.apache.pekko.actor.typed.scaladsl.AskPattern._

object MyActor {
  sealed trait Command
  final case class SayHello(name: String) extends Command

  def apply(): Behavior[Command] =
    Behaviors.receive { (context, message) =>
      message match {
        case SayHello(name) =>
          context.log.info(s"Hello, $name 👋 from MyActor")
          Behaviors.same
      }
    }
}

object MainGuardian {
  def apply(): Behavior[SpawnProtocol.Command] =
    Behaviors.setup { _ =>
      SpawnProtocol()
    }
}

object Protocol {
  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem[SpawnProtocol.Command] =
      ActorSystem(MainGuardian(), "SpawnDemoSystem")

    implicit val ec: ExecutionContext = system.executionContext
    implicit val timeout: Timeout = Timeout(3.seconds)

    val actorRefFuture =
      system.ask[ActorRef[MyActor.Command]](
        SpawnProtocol.Spawn(
          behavior = MyActor(),
          name = "my-actor",
          props = Props.empty,
          _
        )
      )

    actorRefFuture.foreach { ref =>
      ref ! MyActor.SayHello("Akshay")
    }
  }
}


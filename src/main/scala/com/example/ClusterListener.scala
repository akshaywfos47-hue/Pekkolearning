package com.example

import org.apache.pekko.actor.typed.*
import org.apache.pekko.actor.typed.scaladsl.*

import org.apache.pekko.cluster.typed.*
import org.apache.pekko.cluster.ClusterEvent.*


object ClusterListener {

  def apply(): Behavior[MemberEvent] =
    Behaviors.setup { ctx =>

      val cluster = Cluster(ctx.system)

      // subscribe to cluster events
      cluster.subscriptions ! Subscribe(ctx.self, classOf[MemberEvent])

      Behaviors.receiveMessage {
        case MemberUp(member) =>
          ctx.log.info(s"Node UP: ${member.address}")
          println(s"Node UP: ${member.address}")
          Behaviors.same

        case MemberRemoved(member, _) =>
          ctx.log.info(s"Node REMOVED: ${member.address}")
          println(s"Node REMOVED: ${member.address}")
          Behaviors.same

        case _ =>
          Behaviors.same
      }
    }
}

@main def run(): Unit = {
  val system = ActorSystem(ClusterListener(), "ClusterSystem")
}
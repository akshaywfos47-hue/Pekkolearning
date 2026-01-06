package com.example.spwanprotocol

import org.apache.pekko.actor.typed.ActorRef

sealed trait message
final case class sendmsg(msg:String) extends message
final case class receivemsg(msg:String, refrence:ActorRef[message])


object WatchingActor {


}

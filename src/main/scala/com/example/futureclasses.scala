package com.example

object futureclasses {
  def getprize(item:String):Option[Double]={
    item.toLowerCase match {
      case "apple" => Some(30.0)
      case _ => None
    }
  }
  @main def run2(): Unit = {
    val result1=getprize("apple")
    println(result1.getOrElse("not found "))

    val result2 =getprize("ban")
    println(result2.getOrElse("not found "))
  }

}

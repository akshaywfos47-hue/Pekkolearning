package com.example

object case_class {
  def main(args : Array[String]) : Unit ={
    var num=List(2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)
    println(num)
    case class Person(name: String, age: Int)

    val p = Person("Akshay", 25)
    println(p.name)
    println(p.copy(age = 26))
    val copyval=p.copy()
    println(s"$copyval this is the copied value ")
    for(i<-num if i%2==0;if i!=10) {
      println(i)

    }
  }

}

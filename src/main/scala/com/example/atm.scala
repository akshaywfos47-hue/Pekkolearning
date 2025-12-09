package com.example

class atm {
  sealed trait transation
  case class Deposit(amount:Int) extends transation
  case class Withdraw(amount: Int) extends transation
  case object CheckBalance extends transation

  def bank(balance:Int,tx:transation):Option[Int]=
    tx match
      case Deposit(a) => Some(balance+a)
      case Withdraw(b) if balance >=b  => Some(balance-b)
      case CheckBalance => Some(balance)
      case _ => Some(balance+10) //finally this case will match if above cases are not matched



}
@main def run1():Unit={
  val atm1=new atm()
 val bal=10
  println(s"deposit : ${atm1.bank(bal,atm1.Deposit(1000))}")
  println(s"withdrw : ${atm1.bank(bal,atm1.Withdraw(500))} ")// it fails then it matches default case
  println(s"balance : ${atm1.bank(bal,atm1.CheckBalance)}")


}


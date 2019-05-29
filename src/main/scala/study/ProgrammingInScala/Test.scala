package study.ProgrammingInScala

import java.io._

import scala.util.Sorting

package object random {
  var seed: Int = _
  val a = BigDecimal(1664525)
  val b = BigDecimal(1013904223)
  val n = 32

  def setSeed(seed: Int): Unit = this.seed = seed

  def nextInt(): Int = {
    val temp = (seed * a + b) % BigDecimal(2).pow(n)
    seed = temp.toInt
    seed
  }

  def nextDouble(): Double = {
    val temp = (seed * a + b) % BigDecimal(2).pow(n)
    seed = temp.toInt
    temp.toDouble
  }
}

object RichFile {


  //  def unapply(str:String): Option[(String,String,String)] ={
  //    val file = new File(str)
  //    val path = file.getAbsolutePath
  //    val parent = file.getParent
  //    val name = file.getName
  //    val last = name.split("\\.")(1)
  //    Some((parent,name,last))
  //  }
  def unapplySeq(str: String): Option[Seq[String]] = {
    if (str.nonEmpty)
      Some(str.split("/"))
    else
      None
  }

}


object Test extends App {
  //  val acc1 = new SavingsAccount with TimestampLogger with ShortLogger
  //  val acc2 = new SavingsAccount with ShortLogger with TimestampLogger
  //  import java.awt.Point
  //
  //  class OrderedPoint(x:Int,y:Int) extends Point(x,y) with Ordered[Point] {
  //    def compare(that: Point) = if (x <= that.x && y < that.y) -1
  //    else if (x == that.x && y == that.y) 0
  //    else 1
  //  }
  //  val p1 = new OrderedPoint(1,1)
  //
  //  val p2 = new OrderedPoint(1,2)
  //p1.compare(p2)
//  val RichFile(a,b,c) = "D:/IdeaProject/.txt"
//    println("a: %s,b: %s c: %s".format(a,b,c))
  import java.time._
  import scala.concurrent._
  import ExecutionContext.Implicits.global

  Future {
    Thread.sleep(2000)
    println(s"This is the future at ${LocalTime.now}")
  }
  println(s"This is the present at ${LocalTime.now}")
}

@SerialVersionUID(42L)
class Person(val name: String) extends Serializable {
  private var friends: Set[Person] = Set[Person]()

  def getFriends = friends

  def addFriend(p: Person) = friends += p

}

trait Logger {
  def log(msg: String)
}

trait ConsoloLogger extends Logger {
  def log(msg: String) = println(msg)
}

abstract class SavingsAccount extends Logger {
  log("SavingsAccount")
}


trait TimestampLogger extends ConsoloLogger {
  override def log(msg: String): Unit = {
    super.log(s"${java.time.Instant.now()} $msg")
  }
}

trait ShortLogger extends ConsoloLogger {
  val maxlength = 15

  override def log(msg: String): Unit = {
    super.log {
      if (msg.length <= maxlength) msg
      else
        msg.substring(0, 12) + "..."
    }
  }
}


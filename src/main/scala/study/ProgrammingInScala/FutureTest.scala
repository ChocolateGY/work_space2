package study.ProgrammingInScala

import java.time._
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
object FutureTest extends App {

//  Future {
//    Thread.sleep(2000)
//    println(s"This is the future at ${LocalTime.now}")
//  }
  println(s"This is the present at ${LocalTime.now}")
//  Future{ for(i<- 1 to 100 ){print("A") ; Thread.sleep(10)}}
//  Future{ for(i<- 1 to 100 ){print("B") ; Thread.sleep(10)}}

  val f = Future{
    Thread.sleep(3000)
    42
  }
//  val res = Await.result(f,3.seconds)
 Await.ready(f,3.seconds)
  val res = f.value
println(res.get)

}

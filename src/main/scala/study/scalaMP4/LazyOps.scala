package study.scalaMP4

import scala.io.Source

object LazyOps {
  def main(args: Array[String]): Unit = {
    lazy val file = Source.fromFile("123")
//    file.getLines().foreach(println)
    println("hello lazy")
  }

}

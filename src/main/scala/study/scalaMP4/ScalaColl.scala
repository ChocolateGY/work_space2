package study.scalaMP4

import scala.io.Source

object ScalaColl {
  def main(args: Array[String]): Unit = {
    val a = Tuple2("a",1)
    println(a._1)
    val b = ("b",2)
    println(b)
    val file = Source.fromURL("https://baike.baidu.com/item/%E6%AC%A7%E5%87%A0%E9%87%8C%E5%BE%B7%E7%AE%97%E6%B3%95/9002848?fr=aladdin")
    val lines = file.getLines()
    lines.take(10).foreach(println)

  }

}

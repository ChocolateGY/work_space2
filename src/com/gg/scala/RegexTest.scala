package com.gg.scala

/**
  * Created by GuanYu on 2017-3-18.
  */
object RegexTest {
  def main(args: Array[String]) {
    val str = "Android+5.0.1,Android+6.0,Android+4.3,Android+4.0,Android+4.2.1,Android+5.0"
    val regex = "\\+\\d\\.\\d".r
    val test = Array("123", "abc")
    //    test.filter(x=>pattern.findFirstMatchIn(x)).foreach(println)
    val ss = "1#a\n2#b\n3#c\nxx#yy\nabc"
    val maps = ss.split("\n").map(a => a.split("#"))
      .filter((b: Array[String]) => b.size == 2 && regex.findFirstMatchIn(b(0)) != None)
      .map(_ match { case Array(a, b) => (a.toInt -> b) })
    print(maps.mkString("==="))
    //    val a = pattern.findAllIn(str)
    //    println(a)
  }

}

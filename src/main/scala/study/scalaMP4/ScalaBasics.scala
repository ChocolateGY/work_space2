package study.scalaMP4

import scala.io.StdIn

object ScalaBasics {
  def main(args: Array[String]): Unit = {
    //    doWhile()
    exceptTest()
  }


  //object 为静态类，不在方法内的代码可以直接执行
  //  var line = ""
  //  do {
  //    line = StdIn.readLine()
  //    println(line)
  //  } while (line != "")

  //流程控制，dowhile
  def doWhile(): Unit = {
    var line = ""
    do {
      line = StdIn.readLine()
      println(line)
    } while (line != "")
  }

  //欧几里得算法，求最大公约数：两数的最大公约数就是两者之间较小的数与两数的余的最大公约数。
  def gcd(x: Int, y: Int): Int = {
    var a = x
    var b = y
    var temp = 0
    while (a != 0) { //这里判断小数不为0，所以a为小数
      temp = a
      a = b % a //这里大数%小数，b为大数
      b = temp
    }
    b
  }

  def exceptTest(): Unit = {
    var a = 99
    try {
      a = a % 2
      if (a == 0)
        println("a is event")
      else
        throw new RuntimeException("a is not event")
    } catch {
      case e: Exception =>println(e.getMessage)
    }
  }
}

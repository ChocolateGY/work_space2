package study.scala

import java.io.File

import scala.io.Source

/**
  * Created by root on 2017-12-22.
  */
object jike {
  def main(args: Array[String]): Unit = {
    /* if (args.length > 0) {
       for (line <- Source.fromFile(args(0)).getLines()) {
         println(line.length + "  " + line)
       }
     } else
       Console.err.print("please enter file name")*/
    //    println(sum(10))
    //    println(checkSum(sum(10)))
    //val longString=""" Welcome to Ultamix 3000. Type "Help" for help."""
    //    println ( ChecksumAccumulator.calculate("Welcome to Scala Chinese community"))
    //Rational 测试
    /* val a = new Rational(3, 6)
     val b = new Rational(4, 5)
     println(a + 3)
     println(b + 3)

     implicit def int2Ration(i: Int) = new Rational(i)

     println(3 + b)*/
    //for 测试
    /*val fileList = new File(".").listFiles()
    val fileList2 = new File(".").listFiles()
    for {f <- fileList
         if f.canWrite
         f2 <- fileList2
         if f2.canExecute

    }{
      println(f)
    }*/
    //    val n = 5
    //    val half = if(n%2==0)
    //      n/2
    //    else
    //      throw new RuntimeException("")

    /*println(f())
    println(g())*/


    //    var increase = (x: Int) => x + 1
    //    var more = 1
    //    val addmore = (x:Int)=>x+more
    //    addmore(100)

    findFile("txt", _.contains(_)).foreach(println)
    filesContaining("txt").foreach(println)
    filesHere.foreach(println)
  }

  def f(): Int = {
    try {
      return 1
    } finally {
      return 2
    }
  }

  def g(): Int = try {
    1
  } finally {
    2
  }

  def sum(b: Int): Int = {
    var num = 0
    num += b
    num
  }

  def checkSum(x: Int) = {
    ~(x & 0xff) + 1
  }

  def findFile(query: String, matcher: (String, String) => Boolean) = {
    for (file <- filesHere; if matcher(file.getName, query)) yield file
  }

  def filesHere = new File(".").listFiles()


  def filesMatching(
                     matcher: (String) => Boolean) = {
    for(file <- filesHere; if matcher(file.getName))
      yield file
  }

  def filesContaining(query:String)=
    filesMatching(_.contains(query))
}

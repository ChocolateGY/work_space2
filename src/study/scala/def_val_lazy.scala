package study.scala

/**
  * Created by root on 2017-12-5.
  */
object def_val_lazy {
  def main(args: Array[String]): Unit = {
    //    callByName(f())
//    callByValue(f())

  println(and(false,loop))
  }

  def f() = {
    println("hello")
    1
  }

  def callByValue(x: Int): Unit = {
    println("x=\t" + x)
    println("x=\t" + x)
  }

  def callByName(x: => Int) = {
    println("x=\t" + x)
    println("x=\t" + x)
  }

  def loop: Boolean = loop

  def and(x: Boolean, y: => Boolean) =
    if (x) y else false
}

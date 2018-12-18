package study.scala

/**
  * Created by root on 2018-5-9.
  */
object TailRecursive {
  def main(args: Array[String]): Unit = {
    boom(3)
  }

  def boom(x: Int): Int =
    if (x == 0) throw new Exception("boom!")
    else boom(x - 1)
}

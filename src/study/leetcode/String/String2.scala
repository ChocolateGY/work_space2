package study.leetcode.String

/**
  *
  * 颠倒整数
  * 给定一个 32 位有符号整数，将整数中的数字进行反转。
  *
  *
  * Created by root on 2018-6-3.
  */
object String2 {
  def reverse(x: Int): Int = {
    val flag = if (x > 0) true else false
    var abs = x.abs
    //    var abs = x
    var result: Long = 0
    while (abs > 0) {
      result = result * 10 + abs % 10
      abs /= 10
    }
    result = if (flag) result else -result
    if (result > Int.MaxValue || result < Int.MinValue)
      0
    else
      result.toInt
  }

  def main(args: Array[String]): Unit = {
    val x = 123
    println(reverse(x))
  }
}

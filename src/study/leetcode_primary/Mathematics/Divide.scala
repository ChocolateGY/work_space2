package study.leetcode_primary.Mathematics

/**
  * 两数相除
  * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
  *
  * 返回被除数 dividend 除以除数 divisor 得到的商。
  *
  * 示例 1:
  *
  * 输入: dividend = 10, divisor = 3
  * 输出: 3
  * 示例 2:
  *
  * 输入: dividend = 7, divisor = -3
  * 输出: -2
  * 说明:
  *
  * 被除数和除数均为 32 位有符号整数。
  * 除数不为 0。
  * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
  */
object Divide {
  def divide(dividend: Int, divisor: Int): Int = {
    if (divisor == 0) -1
    else {

      var dvd: Long = dividend.toLong.abs
      val dvs: Long = divisor.toLong.abs
      var result = 0l
      while (dvd > dvs) {
        var temp = dvs
        var mul = 1
        while (dvd >= (temp << 1)) {
          temp <<= 1
          mul <<= 1
        }
        dvd -= temp
        result += mul
      }
      if (dividend < 0 ^ divisor < 0) result = -result
      if (result > Int.MaxValue)
        Int.MaxValue
      else
        result.toInt
    }
  }

  def divide2(dividend: Int, divisor: Int): Int = {
    var m: Long = dividend.toLong.abs
    var n = divisor.toLong.abs
    var res = 0l
    if (m < n) 0
    else {
      while (m >= n) {
        var t = n
        var p = 1l
        while (m > (t << 1)) {
          t <<= 1
          p <<= 1
        }
        res += p
        m -= t
      }

    }
    if ((dividend < 0) ^ (divisor < 0)) res = -res
    if (res > Int.MaxValue)
      Int.MaxValue
    else
      res.toInt
  }

  def main(args: Array[String]): Unit = {
    val re = divide(20,3)
    println(re)
  }
}

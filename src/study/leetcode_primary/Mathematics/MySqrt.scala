package study.leetcode_primary.Mathematics

/**
  * x 的平方根
  * 实现 int sqrt(int x) 函数。
  *
  * 计算并返回 x 的平方根，其中 x 是非负整数。
  *
  * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
  *
  * 示例 1:
  *
  * 输入: 4
  * 输出: 2
  * 示例 2:
  *
  * 输入: 8
  * 输出: 2
  * 说明: 8 的平方根是 2.82842...,
  * 由于返回类型是整数，小数部分将被舍去。
  */
object MySqrt {
  def mySqrt1(x: Int): Int = {
    x match {
      case n: Int if n <= 1 => n
      case n: Int => {
        var left = 0
        var right = n
        while (left < right) {
          val mid = left + (right - left) / 2
          if (n / mid < mid) right = mid
          else left = mid + 1
        }
        right - 1
      }
    }
  }

  /**
    * 不通过，Int.max 报错
    *
    * @param x
    * @return
    */
  def mySqrt2(x: Int): Int = {
    x match {
      case n: Int if n <= 1 => n
      case n: Int => {
        var left = 0
        var right = n
        var ans = 0
        while (left <= right) {
          val mid = left + (right - left) / 2
          if (mid * mid <= n) {
            ans = mid
            left = mid + 1
          }
          else right = mid - 1
        }
        ans
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val n = mySqrt2(5)
    println(n)

  }
}

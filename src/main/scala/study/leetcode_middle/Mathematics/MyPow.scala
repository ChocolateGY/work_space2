package study.leetcode_middle.Mathematics

/**
  * Created by root on 2018-9-14.
  * Pow(x, n)
  * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
  * *
  * 示例 1:
  * *
  * 输入: 2.00000, 10
  * 输出: 1024.00000
  * 示例 2:
  * *
  * 输入: 2.10000, 3
  * 输出: 9.26100
  * 示例 3:
  * *
  * 输入: 2.00000, -2
  * 输出: 0.25000
  * 解释: 2-2 = 1/22 = 1/4 = 0.25
  * 说明:
  * *
  * -100.0 < x < 100.0
  * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
  */
object MyPow {
  /**
    * 超时
    * O(n)
    *
    * @param x
    * @param n
    * @return
    */
  def myPow(x: Double, n: Int): Double = {
    val num = if (n < 0) 1 / x else x
    var temp = num
    if (n == 0) {
      1
    } else if (n.abs == 1) {
      temp
    } else {
      for (i <- 2 to n.abs) {
        temp *= num
      }
      temp
    }
  }

  /**
    *通过
    * O(logn)
    * @param x
    * @param n
    * @return
    */
  def myPow2(x: Double, n: Int): Double = {
    if (n == 0)
      1.0
    else if (n < 0) { //对于n取值INT_MIN时，-n并不是INT_MAX，这时需要格外小心。
      if (n == Int.MinValue)
        1 / myPow2(x, Int.MaxValue) * x
      else
        1 / myPow2(x, -n)
    }
    else {
      val half = myPow2(x, n >> 1)
      if (n % 2 == 0)
        half * half
      else
        half * half * x
    }
  }

  def main(args: Array[String]): Unit = {
    //    println(myPow(2.000,10))
    val x = 2.00
    var temp = x
    for (i <- 2 to 10.abs) {
      temp *= x
      println(temp)
    }

  }

}

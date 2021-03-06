package study.interview.prepare.BinarySearch

/**
  * Pow(x, n)
  * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
  *
  * 示例 1:
  *
  * 输入: 2.00000, 10
  * 输出: 1024.00000
  * 示例 2:
  *
  * 输入: 2.10000, 3
  * 输出: 9.26100
  * 示例 3:
  *
  * 输入: 2.00000, -2
  * 输出: 0.25000
  * 解释: 2-2 = 1/22 = 1/4 = 0.25
  * 说明:
  *
  * -100.0 < x < 100.0
  * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
  */
object MyPow {

  def myPow(x: Double, n: Int): Double = {
    if (n == 0)
      return 1.0
    if (n < 0) {
      //Minvalue的绝对值比MaxValue大1
      if (n == Int.MinValue)
        1 / myPow(x, Int.MaxValue) * x
      else
        1 / myPow(x, -n)
    } else {
      val half = myPow(x,n>>1)
      if(n%2==0)
        half*half
      else
        half*half*x
    }
  }
}

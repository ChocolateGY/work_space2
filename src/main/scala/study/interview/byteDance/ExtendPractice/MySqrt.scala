package study.interview.byteDance.ExtendPractice


/**
  *
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
  /**
    * 牛顿法
    * 通过1阶泰勒展开式（即当前点的切线）代替原曲线，求直线与x轴的交点，重复这个过程知道收敛。
    * f(x) = (x+ a/x) / 2
    *
    * @param x
    * @return
    */
  def mySqrt(x: Int): Int = {
    val s = x

    def sqrts(x: Double): Double = {
      val res = (x + s / x) / 2
      if (res == x)
        x
      else
        sqrts(res)
    }

    if (x == 0)
      0
    else {
      sqrts(x).toInt
    }
  }

  /**
    * 二分法
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
    * 自己实现
    *
    * @param x
    * @return
    */
  def mySqrt3(x: Int): Int = x match {

    case n if n <= 1 => n
    case n: Int => {
      var left = 0
      var right = n
      while (left < right) {
        val mid = (left + right) >>> 1
        if (x / mid == mid)
          return mid
        else if (x / mid < mid)
          right = mid
        else
          left = mid + 1
      }
      left - 1
    }

  }

}

package study.interviewPrepare.BinarySearch

object MySqrt {

  //x的平方根
  def mySqrt(x: Int): Int = {
    if (x == 0)
      return 0
    var low = 1
    var high = x
    while (low <= high) {
      val mid = low + (high - low) / 2
      if (x / mid == x)
        return mid
      else if (x / mid > x)
        high = mid - 1
      else
        low = mid + 1
    }
    -1
  }

  //x的平方根 牛顿法 f(x) = x+(a+x)/2
  def mySqrt2(x: Int): Int = {
    if (x == 0)
      return 0
    val a = x

    def recursive(x: Double): Double = {
      val res = (x + a / x) / 2
      if (res == x)
        x
      else
        recursive(res)
    }

    recursive(a).toInt
  }

}

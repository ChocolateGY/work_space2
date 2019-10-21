package study.interview.byteDance

object PracticeExtendPractice {
  def main(args: Array[String]): Unit = {

    // * cache.put(1, 1);
    //  * cache.put(2, 2);
    //  * cache.get(1);       // 返回  1
    //  * cache.put(3, 3);    // 该操作会使得密钥 2 作废
    //  * cache.get(2);       // 返回 -1 (未找到)
    //  * cache.put(4, 4);    // 该操作会使得密钥 1 作废
    //  * cache.get(1);       // 返回 -1 (未找到)
    //  * cache.get(3);       // 返回  3
    //  * cache.get(4);       // 返回  4

    //    val map = new scala.collection.mutable.LinkedHashMap[Int, Int]()
    //    map += (1 -> 1)
    //    map += (2 -> 2)
    //    map += (3 -> 3)
    //    println(map.head)
    //    println(map.last)

    //
    //    val map = new LRUCache(2)
    //    map.put(1,1)
    //    map.put(2,2)
    //    println(map.get(1))
    //    map.put(3,3)
    //    println(map.get(2))
    //    map.put(4,4)
    //    println(map.get(1))
    //    println(map.get(3))
    //    println(map.get(4))

    //["LRUCache","get","put","get","put","put","get","get"]
    //[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
    val map = new LRUCache(2)
    println(map.get(2))
    map.put(2, 6)
    println(map.get(1))
    map.put(1, 5)
    map.put(1, 2)
    println(map.get(1))
    println(map.get(2))
  }

  /**
    * x的平方根
    */
  def mySqrt(x: Int): Int = x match {
    case n: Int if n <= 1 => n
    case n: Int =>
      var left = 1
      var right = n
      while (left <= right) {
        val mid = left + (right - left) / 2
        if (x / mid == mid)
          return mid
        else if (mid < x / mid) // 这里不能用mid*mid < x ，会造成溢出。
          left = mid + 1
        else
          right = mid - 1
      }
      right
  }

  /**
    * x的平方 ，牛顿法
    */
  def mySqrt2(x: Int): Int = x match {
    case x: Int if x <= 1 => x
    case a: Int =>
      var res: Double = 0

      def recur(x_ : Double): Double = {
        res = (x_ + a / x_) / 2
        if (res == x_)
          x_
        else
          recur(res)
      }

      recur(a).toInt
  }

  /**
    * 校验utf-8
    * 1、先将每个数字转为二进制字符串，并补全、截取为8位
    * 2、判断第一个字符串的开始字符来确定是几个字节。
    * 3、判断剩余字节是否符合规则
    * 4、判断最后是否还留有字节。
    */
  def validUtf8(data: Array[Int]): Boolean = {
    var flag = true
    var num = 0
    for (n <- data if flag) {
      var str = n.toBinaryString

      if (str.length < 8)
        str = "0" * (8 - str.length) + str
      else
        str = str.substring(str.length - 8)

      if (num == 0) {
        if (str.startsWith("0")) num = 0
        else if (str.startsWith("110")) num = 1
        else if (str.startsWith("1110")) num = 2
        else if (str.startsWith("11110")) num = 3
        else
          flag = false
      } else {
        if (str.startsWith("10"))
          num -= 1
        else
          flag = false
      }
    }
    if (num != 0)
      flag = false
    flag
  }

  /**
    * utf-8 位校验
    *
    */
  def validUtf82(data: Array[Int]): Boolean = {
    var flag = true
    //表示还有几个字节
    var num = 0
    for (x <- data if flag) {
      if (num == 0) {
        var mask = 1 << 7
        while ((x & mask) != 0) {
          num += 1
          mask >>= 1
        }
        //这里注意num =1 不在规则内
        if (num > 4 || num == 1)
          flag = false
        // num =0 的情况才是表示有一个字节
        if (num == 0)
          num += 1
      } else {
        val mask1 = 1 << 7
        val mask2 = 1 << 6
        if (((mask1 & x) == 0) || ((mask2 & x) != 0))
          flag = false

      }
      num -= 1
    }
    num == 0 & flag
  }

  class LRUCache(_capacity: Int) {
    val map = new scala.collection.mutable.LinkedHashMap[Int, Int]()

    def get(key: Int): Int = {
      val res = map.get(key)
      if (res.nonEmpty) {
        map -= key
        map += (key -> res.get)
        res.get
      } else
        -1
    }

    def put(key: Int, value: Int) {
      val res = map.get(key)
      if (res.nonEmpty)
        map -= key
      if (map.size == _capacity) {
        map.remove(map.head._1)
      }
      map += (key -> value)
    }
  }

}

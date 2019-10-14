package study.interview.byteDance

object Practice {
  def main(args: Array[String]): Unit = {
    println(lengthOfLongestSubstring("abcabcbb"))
  }

  //无重复最长字串 。滑动窗口 + 记录表
  def lengthOfLongestSubstring(s: String): Int = {
    s match {
      case a: String if a.isEmpty => 0
      case b: String if b.size == 1 => 1
      case _: String =>
        var res = 0
        var left = -1
        val arr = Array.fill(256)(-1)
        for (right <- s.indices) {
          left = left.max(arr(s(right)))
          arr(s(right)) = right
          res = res.max(right - left)
        }
        res
    }
  }

  //最长公共子串。 二分查找
  def longestCommonPrefix(strs: Array[String]): String = {
    if (strs.isEmpty) return ""
    if (strs.length == 1) return strs(0)
    if (strs.length == 1) strs(0)
    var low = 0
    var high = strs.map(_.count(_ => true)).min
    var res = ""
    while (low <= high) {
      val mid = low + (high - low) / 2
      val pre = strs(0).take(mid)
      if (strs.forall(_.startsWith(pre))) {
        res = pre
        low = mid + 1
      } else
        high = mid - 1
    }
    res
  }

  //字符串排列  滑动窗口,用两个表记录字符数量
  def checkInclusion(s1: String, s2: String): Boolean = {
    val len1 = s1.length
    val arr1 = Array.fill(26)(0)
    val arr2 = Array.fill(26)(0)
    s1.foreach(s => arr1(s - 'a') += 1)
    for (i <- s2.indices) {
      if (i >= len1) arr2(s2(i - len1) - 'a') -= 1
      arr2(s2(i) - 'a') += 1
      var flag = true
      for (a <- arr1.indices) {
        if (arr1(a) != arr2(a))
          flag = false
      }
      if (flag)
        return true
    }
    false
  }

  def multiply(num1: String, num2: String): String = {
    if (num1 == "0" || num2 == "0") return "0"
    val l1 = num1.length
    val l2 = num2.length
    val arr = new Array[Int](l1 + l2 - 1)
    for (i <- num1.indices; j <- num2.indices) {
      val a = num1(i) - '0'
      val b = num2(j) - '0'
      arr(i + j) += a * b
    }
    for (i <- (1 until arr.length).reverse) {
      arr(i - 1) += arr(i) / 10
      arr(i) %= 10
    }
    arr.mkString("")
  }
}

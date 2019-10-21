package study.interview.byteDance

object PracticeString {
  def main(args: Array[String]): Unit = {
    println(lengthOfLongestSubstring("abcabcbb"))
    println(reverseWords(" hello  world!"))
    println(simplifyPath("/a/./b/../../c/"))
    println(simplifyPath("/home//foo/"))

    println(restoreIpAddresses2("25525511135"))
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

  //字符串相乘
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

  //反转字符串里的单词  使用正则 \s+ 分割并反转
  def reverseWords(s: String): String =
    s.trim.split("\\s+").reverse.mkString(" ")


  //简化路径 使用 / 分割，并判 ""、"." 、".."的情况 ，存放stack中，最后输出并反转。
  def simplifyPath(path: String): String = {
    val arr = path.split("/")
    val stack = scala.collection.mutable.Stack[String]()
    arr.foreach {
      p =>
        if (p != "" && p != ".") {
          if (p == "..") {
            if (stack.nonEmpty)
              stack.pop
          } else
            stack.push(p)
        }
    }
    "/" + stack.reverse.mkString("/")
  }

  //复原ip地址。 暴力解法，把每一段的长度都尝试一遍
  def restoreIpAddresses(s: String): List[String] = {
    val arr = scala.collection.mutable.ArrayBuffer[String]()

    for (a <- 1 to 3; b <- 1 to 3; c <- 1 to 3; d <- 1 to 3 if a + b + c + d == s.length) {
      val str1 = s.substring(0, a)
      val str2 = s.substring(a, a + b)
      val str3 = s.substring(a + b, a + b + c)
      val str4 = s.substring(a + b + c, a + b + c + d)
      if (check(str1) && check(str2) && check(str3) && check(str4))
        arr += Array(str1, str2, str3, str4).mkString(".")
    }

    def check(str: String): Boolean = {
      str.toInt.toString.length == str.length && str.toInt >= 0 && str.toInt <= 255
    }
    arr.toList
  }

  //复原ip地址。递归解法
  def restoreIpAddresses2(s: String): List[String] = {
    val arr = scala.collection.mutable.ListBuffer[String]()
    recursive(0, s, "")

    def recursive(n: Int, str: String, res: String): Unit = {
      if (n == 4) {
        if (str.isEmpty)
          arr += res
      } else {
        for (k <- 1 to 3) {
          if (str.length >= k) { //note
            var value = str.take(k)
            if (java.lang.String.valueOf(value.toInt).length == k  && value.toInt <= 255) {
              if (n != 3) value += "."
              recursive(n + 1, str.substring(k), res + value)
            }
          }
        }
      }
    }
    arr.toList
  }

}

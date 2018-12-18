package study.leetcode_middle.ArrayAndString

/**
  * Created by root on 2018-8-18.
  *
  * 最长回文子串
  * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
  * *
  * 示例 1：
  * *
  * 输入: "babad"
  * 输出: "bab"
  * 注意: "aba"也是一个有效答案。
  * 示例 2：
  * *
  * 输入: "cbbd"
  * 输出: "bb"
  */
object LongestPalindrome {
  def longestPalindrome(s: String): String = {
    if (s.isEmpty) return s
    val flag = new Array[Array[Boolean]](s.length)
    for (i <- flag.indices) {
      flag(i) = new Array[Boolean](s.length)
    }

    var start, end, max = 0
    var i = s.length - 1
    while (i >= 0) {
      var j = i
      while (j < s.length) {
        if (s(i) == s(j) && (j - i <= 2 || flag(i + 1)(j - 1))) {
          flag(i)(j) = true
          if (max < j - i + 1) {
            max = j - i + 1
            start = i
            end = j
          }
        }
        j += 1
      }
      i -= 1
    }
    flag.foreach {
      x =>
        x.foreach(x => print(x + "|"))
        println("")
    }
    s.substring(start, end + 1)
  }


  /**
    *
    * 尝试不同顺序遍历，失败
    */
  def longestPalindrome2(s: String): String = {
    if (s.nonEmpty) {

      var start, end, max = 0
      val flag = new Array[Array[Boolean]](s.length)
      for (i <- flag.indices) {
        flag(i) = new Array[Boolean](s.length)
      }
      for (i <- s.indices) {
        for (j <- i until s.length) {
          if (s(i) == s(j) && (j - i <= 2 || flag(i + 1)(j - 1))) {
            flag(i)(j) = true
            if (max < i - j + 1) {
              max = i - j + 1
              start = i
              end = j
            }
          }
        }
      }
      s.substring(start, end + 1)

    } else
      s
  }

  def main(args: Array[String]): Unit = {

    val s = longestPalindrome2("babad")
    println(s)
  }
}

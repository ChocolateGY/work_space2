package study.leetcode_advanced.ArrayAndString

/**
  * 最小窗口子字符串
  * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
  *
  * 示例：
  *
  * 输入: S = "ADOBECODEBANC", T = "ABC"
  * 输出: "BANC"
  * 说明：
  *
  * 如果 S 中不存这样的子串，则返回空字符串 ""。
  * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
  */
object MinWindow {
  def minWindow(s: String, t: String): String = {
    val m = s.length
    val n = t.length
    if (m < n)
      return ""
    val freq = new Array[Int](256)
    for (i <- t)
      freq(i) += 1

    var min_l = -1
    var l, r = 0
    var count = 0
    var min_size = m + 1
    while (l <= m - n && r < m) {
      freq(s(r)) -= 1
      if (freq(s(r)) >= 0)
        count += 1
      if (count == n) {
        while (freq(s(l)) < 0) {
          freq(s(l)) += 1
          l += 1
        }
        if (r - l < min_size) {
          min_size = r - l
          min_l = l
        }
        freq(s(l)) += 1
        l += 1
        count -= 1
      }
      r += 1
    }
    if (min_size < m + 1)
      s.substring(min_l, min_l + min_size + 1)
    else
      ""
  }

  /**
    * leetcode 最优解
    *
    * @param s
    * @param t
    * @return
    */
  def minWindow2(s: String, t: String): String = {
    //计算 t 中每个字符出现的次数
    val srcHash = Array.fill(255)(0)
    t.foreach {
      srcHash(_) += 1
    }
    val destHash = Array.fill(255)(0)

    val tLen = t.length
    var min = Int.MaxValue
    var begin = -1
    var start, found = 0
    var end = s.length
    for (i <- s.indices) {
      val c = s.charAt(i)
      destHash(c) += 1

      if (destHash(c) <= srcHash(c)) {
        found += 1
      }

      if (found == tLen) {
        //找到首个 在 t 中的字符, 其他的字符进行统计恢复
        while (
          start < i &&
            destHash(s.charAt(start)) > srcHash(s.charAt(start))
        ) {
          destHash(s.charAt(start)) -= 1
          start += 1
        }

        // i 指向在 t中的最后一个字符, start 指向首个在 t 中的字符
        // 更新 min
        if (i - start < min) {
          min = i - start
          begin = start
          end = i
        }

        //下轮查询匹配是 从 start 开始, 当前字符属于 t,所以计数 -1
        // 不是清0 , 因为之前可能有多个当前字符
        destHash(s.charAt(start)) -= 1
        found -= 1
        start += 1
      }
    }

    if (begin == -1) ""
    else s.substring(begin, end + 1)
  }

  def main(args: Array[String]): Unit = {
    val S = "ADOBECODEBANC"
    val T = "ABC"
    val re = minWindow(S, T)
    println(re)
  }
}

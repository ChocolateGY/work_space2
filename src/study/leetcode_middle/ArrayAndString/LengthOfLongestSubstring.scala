package study.leetcode_middle.ArrayAndString

/**
  * Created by root on 2018-8-15.
  * 无重复字符的最长子串
  *
  * 给定一个字符串，找出不含有重复字符的最长子串的长度。
  * *
  * 示例：
  * *
  * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
  * *
  * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
  * *
  * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
  */

object LengthOfLongestSubstring {
  /**
    * https://www.cnblogs.com/grandyang/p/4480780.html
    */
  def lengthOfLongestSubstring(s: String): Int = {
    val m = Array.fill(256)(-1)
    var res = 0
    var left = -1
    for (i <- s.indices) {
      left = left.max(m(s(i)))
      m(s(i)) = i
      res = res.max(i - left)
    }
    res
  }

  /**
    * 下面这种解法使用了set，核心算法和上面的很类似，把出现过的字符都放入set中，遇到set中没有的字符就加入set中并更新结果res，如果遇到重复的，则从左边开始删字符，直到删到重复的字符停止：
    */
  def lengthOfLongestSubstring2(s: String): Int = {
    var res = 0
    var left = 0
    var right = 0
    val t = collection.mutable.Set[Char]()
    while (right < s.length) {
      if (!t.contains(s(right))) {
        right += 1
        t += s(right)
        res = res.max(t.size)
      } else {
        left += 1
        t -= s(left)
      }
    }
    res
  }

  def main(args: Array[String]): Unit = {
    println('Z'.toInt)
  }
}

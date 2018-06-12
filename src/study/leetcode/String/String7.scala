package study.leetcode.String

/**
  *
  * 实现strStr()
  * 实现 strStr() 函数。
  * *
  * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
  * *
  * 示例 1:
  * *
  * 输入: haystack = "hello", needle = "ll"
  * 输出: 2
  * 示例 2:
  * *
  * 输入: haystack = "aaaaa", needle = "bba"
  * 输出: -1
  * 说明:
  * *
  * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
  * *
  * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
  *
  * Created by root on 2018-6-5.
  */
object String7 {
  def strStr(haystack: String, needle: String): Int = {
    var index = -1
    if (needle.isEmpty)
      index = 0
    else {
      for (i <- 0 to haystack.length - needle.length if index == -1) {
        if (haystack(i) == needle(0)) {
          if (haystack.substring(i, i + needle.length) == needle)
            index = i
        }
      }
    }
    index
  }

  /**
    * 有很多算法
    * Rabin–Karp algorithm算法 - Hash 查找  https://blog.csdn.net/quzhongxin/article/details/46763489
    * Boyer-Moore算法  http://www.ruanyifeng.com/blog/2013/05/boyer-moore_string_search_algorithm.html
    * Knuth-Morris-Pratt算法（简称KMP） http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
    *
    * @param haystack
    * @param needle
    * @return
    */
  def strStr2(haystack: String, needle: String): Int = {
    var index = -1
    if (needle.isEmpty)
      index = 0
    else {
      for (i <- 0 to haystack.length - needle.length if index == -1) {
        if (haystack(i) == needle(0)) {
          if (haystack.substring(i, i + needle.length) == needle)
            index = i
        }
      }
    }
    index
  }
}

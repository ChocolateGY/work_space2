package study.leetcode_primary.String

/**
  *
  * 最长公共前缀
  * 编写一个函数来查找字符串数组中的最长公共前缀。
  * *
  * 如果不存在公共前缀，返回空字符串 ""。
  * *
  * 示例 1:
  * *
  * 输入: ["flower","flow","flight"]
  * 输出: "fl"
  * 示例 2:
  * *
  * 输入: ["dog","racecar","car"]
  * 输出: ""
  * 解释: 输入不存在公共前缀。
  * 说明:
  * *
  * 所有输入只包含小写字母 a-z 。
  *
  * Created by root on 2018-6-6.
  */
object LongestCommonPrefix {
  def longestCommonPrefix(strs: Array[String]): String = {
    val sb = new StringBuffer()
    if (strs.nonEmpty) {
      val n = strs.reduce((x, y) => if (x.length > y.length) y else x).length
      val set = collection.mutable.Set[Char]()
      var flag = true
      for (i <- 0 until n if flag) {
        for (j <- strs.indices) {
          set += strs(j)(i)
        }
        if (set.size == 1)
          sb.append(set.head)
        else
          flag = false
        set.clear()
      }
    }
    sb.toString
  }

  def main(args: Array[String]): Unit = {
    val arr = Array("c", "c")
    println(longestCommonPrefix(arr))
  }
}

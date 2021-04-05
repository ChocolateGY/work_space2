package study.interview.tencent

/**
  * 14. 最长公共前缀
  * 编写一个函数来查找字符串数组中的最长公共前缀。
  *
  * 如果不存在公共前缀，返回空字符串 ""。
  *
  * 示例 1:
  *
  * 输入: ["flower","flow","flight"]
  * 输出: "fl"
  * 示例 2:
  *
  * 输入: ["dog","racecar","car"]
  * 输出: ""
  * 解释: 输入不存在公共前缀。
  * 说明:
  *
  * 所有输入只包含小写字母 a-z 。
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/longest-common-prefix
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object LongestCommonPrefix_14 {
  //找到长度最小的值，在0到right之间用二分法遍历
  def longestCommonPrefix(strs: Array[String]): String = {
    if (strs.isEmpty) return ""
    if (strs.length == 1) return strs.head
    var left = 0
    var right = strs.map(_.count(_ => true)).min
    var res = ""
    while (left <= right) {
      val mid = left + (right - left) / 2
      val isExi = strs.forall(_.startsWith(strs.head.take(mid)))
      if (isExi) {
        res = strs.head.take(mid)
        left = mid + 1
      } else
        right = mid - 1
    }
    res
  }
}

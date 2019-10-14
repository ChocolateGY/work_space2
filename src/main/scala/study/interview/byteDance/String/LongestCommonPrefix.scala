package study.interview.byteDance.String

/**
  * 最长公共前缀
  * 编写一个函数来查找字符串数组中的最长公共前缀。
  *
  * 如果不存在公共前缀，返回空字符串 ""。
  *
  * 示例 1:
  *
  * 输入: ["flower","flow","flight"]
  * 输出: "fl"
  * 示例 2:
  *
  * 输入: ["dog","racecar","car"]
  * 输出: ""
  * 解释: 输入不存在公共前缀。
  * 说明:
  *
  * 所有输入只包含小写字母 a-z 。
  */
object LongestCommonPrefix {
  def main(args: Array[String]): Unit = {
    val arr1 = Array("flower", "flow", "flight")
    val arr2 = Array("dog", "racecar", "car")
    val arr3 = Array("a")
    println(longestCommonPrefix2(arr1))
    println(longestCommonPrefix(arr2))
    println(longestCommonPrefix(arr3))
    println("1234".indexOf("12"))
    println("1234".indexOf("34"))
    println("1234".indexOf("45"))

  }

  /**
    * 自己实现
    *
    * 总结：计算出最短字符案串长度，挨个匹配前缀
    * @param strs
    * @return
    */
  def longestCommonPrefix(strs: Array[String]): String = {
    var pre = ""
    if (strs.length == 1)
      pre = strs(0)
    else if (strs.nonEmpty) {
      val num = strs.map(_.count(_ => true)).min
      for (i <- 1 to num) {
        if (strs.forall(_.startsWith(strs(0).take(i))))
          pre = strs(0).take(i)
      }
    }
    pre
  }

  /**
    * 使用二分法查找
    *
    * 总结：计算出最短字符串长度，在0和length之间是用二分法查找。
    *
    * @param strs
    * @return
    */
  def longestCommonPrefix2(strs: Array[String]): String = {
    var pre = ""
    if (strs.nonEmpty) {
      var high = strs.map(_.count(_ => true)).min
      var low = 1
      while (low <= high) {
        var mid = (low + high) >>> 1
        pre = strs(0).take(mid)
        if (isCommonPre(pre))
          low = mid + 1
        else
          high = mid - 1
      }

      def isCommonPre(p: String): Boolean = {
        strs.forall(_.startsWith(p))
      }
      pre = strs(0).take((low+high)>>>1)
    }
    pre
  }
}

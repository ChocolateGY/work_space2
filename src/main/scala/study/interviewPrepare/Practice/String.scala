package study.interviewPrepare.Practice

object String {
  def main(args: Array[String]): Unit = {
    val str = "leetcode"
    firstUniqChar(str)
  }

  /**
    * 验证回文串
    * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
    *
    * 说明：本题中，我们将空字符串定义为有效的回文串。
    *
    * 示例 1:
    *
    * 输入: "A man, a plan, a canal: Panama"
    * 输出: true
    * 示例 2:
    *
    * 输入: "race a car"
    * 输出: false
    */
  def isPalindrome(s: String): Boolean = {
    val str = s.toLowerCase.replaceAll("[^a-z0-9]", "")
    var i = 0
    var j = str.length - 1
    while (i < j) {
      if (str(i) != str(j))
        return false
      i += 1
      j -= 1
    }
    true
  }

  /**
    * 分割回文串
    * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
    *
    * 返回 s 所有可能的分割方案。
    *
    * 示例:
    *
    * 输入: "aab"
    * 输出:
    * [
    * ["aa","b"],
    * ["a","a","b"]
    * ]
    *
    * 分治、回溯、动态规划
    */
  //https://leetcode-cn.com/problems/palindrome-partitioning/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-7/

  def partition(s: String): List[List[String]] = {
    List[List[String]]()
  }

  /**
    * 有效的字母异位词
    * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
    *
    * 示例 1:
    *
    * 输入: s = "anagram", t = "nagaram"
    * 输出: true
    * 示例 2:
    *
    * 输入: s = "rat", t = "car"
    * 输出: false
    * 说明:
    * 你可以假设字符串只包含小写字母。
    *
    * 进阶:
    * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
    */
  def isAnagram(s: String, t: String): Boolean = {
    val arr = new Array[Int](26)
    s.foreach {
      x =>
        arr(x - 'a') += 1
    }
    t.foreach {
      x =>
        if (arr(x - 'a') == 0)
          return false
        arr(x - 'a') -= 1
    }
    arr.sum == 0
  }

  /**
    * 字符串中的第一个唯一字符
    * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
    *
    * 案例:
    *
    * s = "leetcode"
    * 返回 0.
    *
    * s = "loveleetcode",
    * 返回 2.
    *
    *
    * 注意事项：您可以假定该字符串只包含小写字母。
    */

  def firstUniqChar(s: String): Int = {
    val arr = new Array[Int](26)
    s.foreach {
      x =>
        arr(x - 'a') += 1
    }
    for (i <- s.indices) {
      if (arr(s(i) - 'a') == 1)
        return i
    }
    -1
  }

  /**
    * 反转字符串
    * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
    *
    * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
    *
    * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
    *
    *
    *
    * 示例 1：
    *
    * 输入：["h","e","l","l","o"]
    * 输出：["o","l","l","e","h"]
    * 示例 2：
    *
    * 输入：["H","a","n","n","a","h"]
    * 输出：["h","a","n","n","a","H"]
    *
    */
  def reverseString(s: Array[Char]): Unit = {
    var i = 0
    var j = s.length - 1
    while (i < j) {
      val temp = s(i)
      s(i) = s(j)
      s(j) = temp
      i+=1
      j-=1
    }

  }
}

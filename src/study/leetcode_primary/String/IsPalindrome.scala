package study.leetcode_primary.String

/**
  * 验证回文字符串
  * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
  * *
  * 说明：本题中，我们将空字符串定义为有效的回文串。
  * *
  * 示例 1:
  * *
  * 输入: "A man, a plan, a canal: Panama"
  * 输出: true
  * 示例 2:
  * *
  * 输入: "race a car"
  * 输出: false
  *
  *
  * Created by root on 2018-6-4.
  */
object IsPalindrome {
  def isPalindrome(s: String): Boolean = {
    val str = s.toLowerCase().filter(ch => (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z'))
    var flag = true
    for (i <- 0 until str.length) {
      if (str(i) != str(str.length - 1 - i))
        flag = false
    }
    flag
  }
}

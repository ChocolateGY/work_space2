package study.interview.tencent

/**
  * 5. 最长回文子串
  * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
  *
  * 示例 1：
  *
  * 输入: "babad"
  * 输出: "bab"
  * 注意: "aba" 也是一个有效答案。
  * 示例 2：
  *
  * 输入: "cbbd"
  * 输出: "bb"
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object LongestPalindromicSubstring_5 {
  //由于存在奇数的字符串和偶数的字符串，所以我们需要从一个字符开始扩展，或者从两个字符之间开始扩展，
  // 所以总共有 n+n-1 个中心。
  //https://leetcode-cn.com/problems/longest-palindromic-substring/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-bao-gu/
  def longestPalindrome(s: String): String = {
    def expandAroundCenter(s: String, left: Int, right: Int): Int = {
      var L = left
      var R = right
      while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
        L -= 1
        R += 1
      }
      R - L - 1
    }

    if (s == "") return ""

    var start, end = 0
    for (i <- 0 until s.length) {
      //n个中心
      val len1 = expandAroundCenter(s, i, i)
      //n-1个中心
      val len2 = expandAroundCenter(s, i, i + 1)
      val len = len1.max(len2)
      if (len > end - start) {
        start = i - (len - 1) / 2
        end = i + len / 2
      }
    }
    s.substring(start, end + 1)

  }
  //和上面一样，遍历字符串，每个字符串的 i、i 中心点和i、i+1中心点扩展。
  //找到最大的长度，然后更新最长回文子串的指针（start，end）
  def longestPalindrome2(s: String): String = {
    def expand(s: String, left: Int, right: Int): Int = {
      var l = left
      var r = right
      while (l >= 0 && r < s.length && s(l) == s(r)) {
        l -= 1
        r += 1
      }
      r - l - 1
    }

    if (s == "") return ""
    var start, end = 0
    for (i <- s.indices) {
      val len1 = expand(s, i, i)
      val len2 = expand(s, i, i + 1)
      val len = len1.max(len2)
      if (len > end - start) {
        start = i - (len - 1) / 2
        end = i + (len / 2)
      }
    }
    s.substring(start, end + 1)
  }
}

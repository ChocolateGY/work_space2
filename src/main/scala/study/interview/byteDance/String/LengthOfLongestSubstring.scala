package study.interview.byteDance.String

/**
  * 给定一个字符串，找出不含有重复字符的 最长子串 的长度。
  *
  *
  *
  * 示例：
  *
  * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
  *
  * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
  *
  * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列 而不是子串
  *
  * 解题思路：
  *
  * 这是一道可以跟Two Sum媲美的题。给了我们一个字符串，让我们求最长的无重复字符的子串，
  * 注意这里是子串，不是子序列，所以必须是连续的。我们先不考虑代码怎么实现，
  * 如果给一个例子"abcabcbb"，让你手动找无重复字符的子串，该怎么找？一个字符一个字符的遍历，
  * 比如a，b，c，然后又出现了一个a，那么此时就应该去掉第一次出现的a，然后继续往后，
  * 又出现了一个b，则应该去掉一次出现的b，以此类推，最终发现最长的长度为3。
  * 所以说，我们需要记录之前出现过的字符，记录的方式有很多，最常见的是统计字符出现的个数，
  * 但是这道题字符出现的位置很重要，所以我们可以使用HashMap来建立字符和其出现位置之间的映射。
  * 进一步考虑，由于字符会重复出现，到底是保存所有出现的位置呢，还是只记录一个位置？
  * 我们之前手动推导的方法实际上是维护了一个滑动窗口，窗口内的都是没有重复的字符，
  * 我们需要尽可能的扩大窗口的大小。由于窗口在不停向右滑动，
  * 所以我们只关心每个字符最后出现的位置，并建立映射。
  * 窗口的右边界就是当前遍历到的字符的位置，为了求出窗口的大小，
  * 我们需要一个变量left来指向滑动窗口的左边界，这样，如果当前遍历到的字符从未出现过，
  * 那么直接扩大右边界，如果之前出现过，那么就分两种情况，在或不在滑动窗口内，
  * 如果不在滑动窗口内，那么就没事，当前字符可以加进来，如果在的话，
  * 就需要先在滑动窗口内去掉这个已经出现过的字符了，
  * 去掉的方法并不需要将左边界left一位一位向右遍历查找，
  * 由于我们的HashMap已经保存了该重复字符最后出现的位置，
  * 所以直接移动left指针就可以了。我们维护一个结果res，
  * 每次用出现过的窗口大小来更新结果res，就可以得到最终结果了。
  *
  * 建立一个256位大小的整型数组来代替哈希表，这样做的原因是ASCII表共能表示256个字符，
  * 所以可以记录所有字符，然后我们需要定义两个变量res和left，
  * 其中res用来记录最长无重复子串的长度，left指向该无重复子串左边的起始位置，
  * 然后我们遍历整个字符串，对于每一个遍历到的字符，如果哈希表中该字符串对应的值为0，
  * 说明没有遇到过该字符，则此时计算最长无重复子串，i - left +１，
  * 其中ｉ是最长无重复子串最右边的位置，left是最左边的位置，
  * 还有一种情况也需要计算最长无重复子串，就是当哈希表中的值小于left，
  * 这是由于此时出现过重复的字符，left的位置更新了，如果又遇到了新的字符，
  * 就要重新计算最长无重复子串。最后每次都要在哈希表中将当前字符对应的值赋值为i+1。
  */
object LengthOfLongestSubstring {
  def main(args: Array[String]): Unit = {
    val str = "abcabcbb"
    val str2 = "abba"
    val str3 = "tmmzuxt"
    //    println(lengthOfLongestSubstring(str3))
    val str4 = "au"
    val str5 = "abcabcbb"
    println(lengthOfLongestSubstring2(str5))
  }

  def lengthOfLongestSubstring(s: String): Int = {
    if (s.length == 0)
      0
    else if (s.length == 1)
      1
    else {
      var res = 1
      var left = 0
      var right = 1
      val map = collection.mutable.Map[Char, Int](s(left) -> left)

      while (right < s.length) {

        val t = map.getOrElse(s(right), -1)
        if (t != -1 && t >= left) {
          left = t + 1
        } else {
          res = res.max(right - left + 1)
        }
        map += (s(right) -> right)
        right += 1
      }
      res
    }
  }

  /**
    * 将hashmap换位 256的数组，精简程序
    */
  def lengthOfLongestSubstring2(s: String): Int = {
    s match {
      case a if a.isEmpty => 0
      case b if b.length == 1 => 1
      case _ =>
        var res = 0
        var left = -1
        val arr = Array.fill(256)(-1)
        for (right <- s.indices) {
          left = left.max(arr(s(right)))
          arr(s(right)) = right
          res = res.max(right - left)
        }
        res
    }

  }
}

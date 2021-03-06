package study.RecursivePrinciple

/**
  * 344. 反转字符串
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
  */
object ReverseString_344 {
  def reverseString(s: Array[Char]): Unit = {
    var i = 0
    var j = s.length - 1
    var temp = '0'
    while (i < j) {
      temp = s(i)
      s(i) = s(j)
      s(j) = temp
      i += 1
      j -= 1
    }
  }

  def reverseString2(s: Array[Char]): Unit = {
    def helper(i: Int, j: Int): Unit = {
      if (i < j) {
        val temp = s(i)
        s(i) = s(j)
        s(j) = temp
        helper(i + 1, j - 1)
      }
    }

    helper(0, s.length - 1)
  }

  def reverseString3(s: Array[Char]): Unit = {
    val arr = s.reverse
    for (i <- s.indices) {
      s(i) = arr(i)
    }
  }
}

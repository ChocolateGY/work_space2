package study.leetcode_primary.String

/**
  * 请编写一个函数，其功能是将输入的字符串反转过来。
  * *
  * 示例：
  * *
  * 输入：s = "hello"
  * 返回："olleh"
  * *
  *
  * 使用scala 编写了两个方法都超时，使用scala自带的方法也超时。
  * 尝试使用java编写
  *
  *
  * Created by root on 2018-6-3.
  */
object ReverseString {
  /**
    * 超时
    *
    * @param s
    * @return
    */
  def reverseString(s: String): String = {
    //    s.reverse
    //仿照scala
    val str = new StringBuffer()
    for (i <- s.indices.reverse) {
      str.append(s(i))
    }
    str.toString
  }

  /**
    * 超时
    *
    * @param s
    * @return
    */
  def reverseString2(s: String): String = {
    //    s.reverse
    //仿照scala
    val arr = new Array[Char](s.length)
    for (i <- s.indices) {
      arr(i) = s(s.length - i - 1)
    }
    new String(arr)
  }
}

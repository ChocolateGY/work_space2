package study.leetcode.String

import scala.collection.mutable.ArrayBuffer

/**
  * 数数并说
  * 报数序列是指一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
  * *
  *1.     1
  *2.     11
  *3.     21
  *4.     1211
  *5.     111221
  * 1 被读作  "one 1"  ("一个一") , 即 11。
  * 11 被读作 "two 1s" ("两个一"）, 即 21。
  * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
  * *
  * 给定一个正整数 n ，输出报数序列的第 n 项。
  * *
  * 注意：整数顺序将表示为一个字符串。
  * *
  * 示例 1:
  * *
  * 输入: 1
  * 输出: "1"
  * 示例 2:
  * *
  * 输入: 4
  * 输出: "1211"
  *
  * Created by root on 2018-6-5.
  *
  * 1.     1
  *2.     11
  *3.     21
  *4.     1211
  *5.     111221
  *6.     312211
  *7.     13112221
  *8.     1113213211
  *9.     31131211131221
  *10.    13211311123113112211
  *
  *
  */
object String8 {
  def countAndSay(n: Int): String = {
    var index = n
    val sb = new StringBuilder()
    if (index > 1) {
      val str = countAndSay(index - 1)

      var x = 0
      val arr = ArrayBuffer[Char]()
      while (x < str.length) {
        arr.append(str(x))
        if (x == str.length - 1 || str(x) != str(x + 1)) {
          sb.append(arr(0)).append(arr.length)
          arr.clear()
        }
        x += 1
      }
      index -= 1
    } else if (index == 1) {
      sb.append("1")
    }
    sb.toString
  }

  def main(args: Array[String]): Unit = {
    println(countAndSay(5))
  }
}

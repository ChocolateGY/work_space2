package study.leetcode.String

/**
  * 字符串转整数（atoi）
  * 实现 atoi，将字符串转为整数。
  * *
  * 在找到第一个非空字符之前，需要移除掉字符串中的空格字符。如果第一个非空字符是正号或负号，选取该符号，并将其与后面尽可能多的连续的数字组合起来，这部分字符即为整数的值。如果第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
  * *
  * 字符串可以在形成整数的字符后面包括多余的字符，这些字符可以被忽略，它们对于函数没有影响。
  * *
  * 当字符串中的第一个非空字符序列不是个有效的整数；或字符串为空；或字符串仅包含空白字符时，则不进行转换。
  * *
  * 若函数不能执行有效的转换，返回 0。
  * *
  * 说明：
  * *
  * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。如果数值超过可表示的范围，则返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
  * *
  * 示例 1:
  * *
  * 输入: "42"
  * 输出: 42
  * 示例 2:
  * *
  * 输入: "   -42"
  * 输出: -42
  * 解释: 第一个非空白字符为 '-', 它是一个负号。
  * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
  * 示例 3:
  * *
  * 输入: "4193 with words"
  * 输出: 4193
  * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
  * 示例 4:
  * *
  * 输入: "words and 987"
  * 输出: 0
  * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
  * 因此无法执行有效的转换。
  * 示例 5:
  * *
  * 输入: "-91283472332"
  * 输出: -2147483648
  * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
  * 因此返回 INT_MIN (−231) 。
  *
  *
  *
  * Created by root on 2018-6-4.
  */
object String6 {

  /**
    *
    * 失败
    *
    * 此方法行不通
    *
    * 会越来越复杂
    *
    * @param str
    * @return
    */
  def myAtoi(str: String): Int = {
    if (str.nonEmpty) {
      val s = str.filter(_ != ' ')
      var flag = true
      val sb = new StringBuilder()
      if (s.nonEmpty && (s(0) == '+' || s(0) == '-' || (s(0) >= '0' && s(0) <= '9'))) {
        sb += s(0)
        for (i <- 1 until s.length; if flag) {
          if (s(i) >= '0' && s(i) <= '9')
            sb += s(i)
          else
            flag = false
        }
        if (sb.toString == "+" || sb.toString == "-")
          0
        else if (sb.length > Int.MinValue.toString.length) {
          if (sb(0) == '-')
            Int.MinValue
          else
            Int.MaxValue
        }
        else {
          val result = sb.toLong
          if (result > Int.MaxValue)
            Int.MaxValue
          else if (result < Int.MinValue)
            Int.MinValue
          else
            result.toInt
        }
      }
      else
        0
    } else
      0
  }

  /**
    * 通过，字符串情况很多
    *
    * @param str
    * @return
    */
  def myAtoi2(str: String): Int = {
    if (str.nonEmpty) {
      var i = -1
      for (x <- str.indices if i == -1) {
        if (str(x) != ' ') {
          i = x
        }
      }
      if (i == -1)
        return 0
      var sign = 1
      if (str(i) == '-') {
        sign = -1
        i += 1
      } else if (str(i) == '+')
        i += 1
      var result: Long = 0
      var flag = true
      for (x <- i until str.length if flag) {
        if ((str(x) >= '0' && str(x) <= '9') && (result * sign <= Int.MaxValue && result * sign >= Int.MinValue))
          result = result * 10 + str(x) - 48
        else
          flag = false
      }
      result *= sign
      if (result >= Int.MaxValue)
        Int.MaxValue
      else if (result <= Int.MinValue)
        Int.MinValue
      else
        result.toInt
    } else
      0
  }

  /**
    * 使用正则
    *
    * 错误1：数字太大不能转换。Line 44 at myAtoi: java.lang.NumberFormatException: For input string: "20000000000000000000"
    * 错误2：如果判断位数，则不能满足这种情况 "  0000000000012345678"  输出  2147483647  预期  12345678
    *
    * 错误3：使用了BigInt，出现了 "   +0 123" 预期为 0 而不是123.
    *
    * 之去掉第一个非空格字符前的空格字符。成功
    *
    * res10: Int = 2147483647
    * res11: Int = -2147483648
    *
    * @param str
    * @return
    *
    */
  def myAtoi3(str: String): Int = {
    //    val temp = str.filter(_ != ' ')
    var index = -1
    var i = 0
    while (index == -1 && i < str.length) {
      if (str(i) != ' ')
        index = i
      i += 1
    }
    if (index != -1) {
      val temp = str.substring(index, str.length)
      if (temp.nonEmpty) {
        val regex = """^[-+]?\d+""".r
        val result = regex.findFirstIn(temp)
        if (result.nonEmpty) {
          //          if (result.get.length <= 11) {
          //          val resultNum = result.get
          val resultNum = BigInt(result.get)

          if (resultNum > Int.MaxValue)
            Int.MaxValue
          else if (resultNum < Int.MinValue)
            Int.MinValue
          else
            resultNum.toInt
          //          } else if (result.get.startsWith("-"))
          //            Int.MinValue
          //          else
          //            Int.MaxValue
        } else
          0
      } else
        0
    } else
      0

  }

  def main(args: Array[String]): Unit = {
    val str = "gaga 42"
    print(myAtoi2(str))

    //    println('0'.toInt)
    //    println("+123".toInt)

    //    for (x <- str.indices) {
    //      println("lhaha")
    //    }
  }


}

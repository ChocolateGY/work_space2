package study.interview.byteDance.String

/**
  * 字符串相乘
  * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，
  * 它们的乘积也表示为字符串形式。
  *
  * 示例 1:
  *
  * 输入: num1 = "2", num2 = "3"
  * 输出: "6"
  * 示例 2:
  *
  * 输入: num1 = "123", num2 = "456"
  * 输出: "56088"
  * 说明：
  *
  * num1 和 num2 的长度小于110。
  * num1 和 num2 只包含数字 0-9。
  * num1 和 num2 均不以零开头，除非是数字 0 本身。
  * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
  */
object MultiplyString {
  def main(args: Array[String]): Unit = {
    val str1 = "123"
    val str2 = "456"
    println(multiply(str1, str2))
  }

  /**
    * 一是我们习惯于右对齐，从右到左运算（这个其实无所谓，只要把任意两位的积累加到对应位置，无论按什么顺序都可以），
    * 二是我们习惯在列竖式时提前进位好，而编码时，不需要提前进位，最后统一进位即可。
    * 虽说一个 m 位的数乘一个 n 位的数，乘积最多有 m+n 位。理论上应该申请长度为 m+n 的数组，
    * 但最高位其实已经没必要再往前进位了，所以申请长度为 m+n-1 的数组就够用了。
    *
    * @param num1
    * @param num2
    * @return
    */
  def multiply(num1: String, num2: String): String = {
    if (num1 == "0" || num2 == "0") return "0"
    val l1 = num1.length
    val l2 = num2.length
    val arr = new Array[Int](l1 + l2 - 1)
    for (i <- num1.indices; j <- num2.indices) {
      val a = num1(i) - '0'
      val b = num2(j) - '0'
      arr(i + j) += a * b
    }

    for (i <- (1 until arr.length).reverse) {
      arr(i - 1) += arr(i) / 10
      arr(i) %= 10
    }
    arr.mkString("")
  }
}

package study.interview.tencent

/**
  * 43. 字符串相乘
  * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
  *
  * 示例 1:
  *
  * 输入: num1 = "2", num2 = "3"
  * 输出: "6"
  * 示例 2:
  *
  * 输入: num1 = "123", num2 = "456"
  * 输出: "56088"
  * 说明：
  *
  * num1 和 num2 的长度小于110。
  * num1 和 num2 只包含数字 0-9。
  * num1 和 num2 均不以零开头，除非是数字 0 本身。
  * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/multiply-strings
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object MultiplyStrings_43 {
  /**
    * * 总结：小学手解乘法，注意数组顺序，程序是先算最高位，再依次往下算的
    * * 1 2 3
    * * 4 5 6
    * * ------
    * * 4 5 6
    * *   8 10 12
    * *     12 15 18
    * * ------------
    * * 5 6 0 8 8
    * 使用 Array(len1+len2-1) 来存储对应的位，每位累计相加
    */
  def multiply(num1: String, num2: String): String = {
    if (num1 == "0" || num2 == "0") return "0"
    val l1 = num1.length
    val l2 = num2.length
    val arr = Array.fill(l1 + l2 - 1)(0)
    for (i <- num1.indices; j <- num2.indices) {
      val a = num1(i) - '0'
      val b = num2(j) - '0'
      arr(i + j) += a * b
    }
    for (i <- Range(arr.length - 1, 0, -1)) {
      arr(i - 1) += arr(i) / 10
      arr(i) %= 10
    }
    arr.mkString("")
  }
}

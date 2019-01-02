package study.leetcode_advanced.ArrayAndString

/**
  * 基本计算器 II
  * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
  *
  * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
  *
  * 示例 1:
  *
  * 输入: "3+2*2"
  * 输出: 7
  * 示例 2:
  *
  * 输入: " 3/2 "
  * 输出: 1
  * 示例 3:
  *
  * 输入: " 3+5 / 2 "
  * 输出: 5
  * 说明：
  *
  * 你可以假设所给定的表达式都是有效的。
  * 请不要使用内置的库函数 eval。
  */
object Calculate {
  def calculate(s: String): Int = {
    var result = 0
    var op = '+'
    var num = 0
    val stack = collection.mutable.Stack[Int]()
    for (i <- s.indices) {
      if (s(i) >= '0' && s(i) <= '9') {
        num = num * 10 + s(i) - '0'
      }
      if ((s(i) < '0' || s(i) > '9') && s(i) != ' ' || i == s.length - 1) {
        if (op == '+') stack.push(num)
        if (op == '-') stack.push(-num)
        if (op == '*') {
          val temp = stack.pop()
          stack.push(temp * num)
        }
        if (op == '/') {
          stack.push(stack.pop / num)
        }
        op = s(i)
        num = 0
      }
    }
    while (stack.nonEmpty) {
      result += stack.pop()
    }
    result
  }

  def main(args: Array[String]): Unit = {
    val str = " 3+5 / 2 "
    val res = calculate(str)
    println(res)
  }
}

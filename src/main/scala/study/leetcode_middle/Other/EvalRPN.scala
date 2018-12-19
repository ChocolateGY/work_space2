package study.leetcode_middle.Other

/**
  * 逆波兰表达式求值
  * 根据逆波兰表示法，求表达式的值。
  *
  * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
  *
  * 说明：
  *
  * 整数除法只保留整数部分。
  * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
  * 示例 1：
  *
  * 输入: ["2", "1", "+", "3", "*"]
  * 输出: 9
  * 解释: ((2 + 1) * 3) = 9
  * 示例 2：
  *
  * 输入: ["4", "13", "5", "/", "+"]
  * 输出: 6
  * 解释: (4 + (13 / 5)) = 6
  * 示例 3：
  *
  * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
  * 输出: 22
  * 解释:
  * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
  * = ((10 * (6 / (12 * -11))) + 17) + 5
  * = ((10 * (6 / -132)) + 17) + 5
  * = ((10 * 0) + 17) + 5
  * = (0 + 17) + 5
  * = 17 + 5
  * = 22
  */

object EvalRPN {
  def evalRPN(tokens: Array[String]): Int = {
    val stack = collection.mutable.Stack[Int]()
    tokens.foreach {
      case x if x == "+" => {
        stack.push(stack.pop() + stack.pop())
      }
      case x if x == "-" => {
        val num1 = stack.pop()
        val num2 = stack.pop()
        stack.push(num2 - num1)
      }
      case x if x == "*" => {
        stack.push(stack.pop() * stack.pop())
      }
      case x if x == "/" => {
        val num1 = stack.pop()
        val num2 = stack.pop()
        if (num1 != 0)
          stack.push(num2 / num1)
        else
          stack.push(-1)
      }
      case x => {
        stack.push(x.toInt)
      }
    }
    stack.pop()
  }

  def main(args: Array[String]): Unit = {
    val token = Array("4", "13", "5", "/", "+")
    print(evalRPN(token))

  }
}

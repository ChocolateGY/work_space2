package study.leetcode_middle.BacktrackingMethod

import scala.collection.mutable

/**
  * Created by root on 2018-8-29.
  *
  * 生成括号
  *
  * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
  * *
  * 例如，给出 n = 3，生成结果为：
  * *
  * [
  * "((()))",
  * "(()())",
  * "(())()",
  * "()(())",
  * "()()()"
  * ]
  *
  */
object GenerateParenthesis {
  /**
    * 这道题给定一个数字n，让生成共有n个括号的所有正确的形式，对于这种列出所有结果的题首先还是考虑用递归Recursion来解，
    * 由于字符串只有左括号和右括号两种字符，而且最终结果必定是左括号3个，右括号3个，
    * 所以我们定义两个变量left和right分别表示剩余左右括号的个数，如果在某次递归时，
    * 左括号的个数大于右括号的个数，说明此时生成的字符串中右括号的个数大于左括号的个数，
    * 即会出现')('这样的非法串，所以这种情况直接返回，不继续处理。如果left和right都为0，
    * 则说明此时生成的字符串已有3个左括号和3个右括号，且字符串合法，则存入结果中后返回。
    * 如果以上两种情况都不满足，若此时left大于0，则调用递归函数，注意参数的更新，若right大于0，则调用递归函数，同样要更新参数。代码如下：
    *
    * @param n
    * @return
    */
  def generateParenthesis(n: Int): List[String] = {
    val res = collection.mutable.ArrayBuffer[String]()
    helper(n, n, "", res)
    res.toList
  }

  def helper(left: Int, right: Int, out: String, res: collection.mutable.ArrayBuffer[String]): Unit = {
    if (left < 0 || right < 0 || left > right) return
    if (left == 0 && right == 0) {
      res += out
      return
    }
    helper(left - 1, right, out + "(", res)
    helper(left, right - 1, out + ")", res)
  }

  /**
    * 再来看那一种方法，这种方法是CareerCup书上给的方法，感觉也是满巧妙的一种方法，这种方法的思想是找左括号，
    * 每找到一个左括号，就在其后面加一个完整的括号，最后再在开头加一个()，就形成了所有的情况，需要注意的是，
    * 有时候会出现重复的情况，所以我们用set数据结构，好处是如果遇到重复项，不会加入到结果中，
    * 最后我们再把set转为vector即可，参见代码如下：
    *
    * https://www.cnblogs.com/grandyang/p/4444160.html
    * 转化为scala失败
    *
    * @param n
    * @return
    */
  def generateParenthesis2(n: Int): List[String] = {
    val res = collection.mutable.Set[String]()
    if (n == 0) {
      res += ""
      res.toList
    }
    else {
      val pre = generateParenthesis2(n - 1)
      pre.map {
        str =>
          var temp = ""
          for (i <- str.indices) {
            if (str(i) == '(') {
              temp = str.substring(0, i + 1) + "()" + str.substring(i + 1, str.length)
              res += temp
              temp = temp.substring(0, i + 1) + str.substring(i + 3, str.length)
            }
          }
          res += ("()" + temp)
          temp
      }
      res.toList
    }
  }

  def main(args: Array[String]): Unit = {
    generateParenthesis(2)
  }
}

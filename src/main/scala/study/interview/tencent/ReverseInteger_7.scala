package study.interview.tencent

/**
  * 7. 整数反转
  * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
  *
  * 示例 1:
  *
  * 输入: 123
  * 输出: 321
  *  示例 2:
  *
  * 输入: -123
  * 输出: -321
  * 示例 3:
  *
  * 输入: 120
  * 输出: 21
  * 注意:
  *
  * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/reverse-integer
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object ReverseInteger_7 {
  //1、定义res:Long=0. 2、从低位到高位将数字%10 加到res上。
  def reverse(x: Int): Int = {
    val flag = if (x > 0) 1 else -1
    var res: Long = 0
    var num = x.abs
    while (num > 0) {
      res = res * 10 + num % 10
      num /= 10
    }
    res = res * flag
    if (res > Int.MaxValue || res < Int.MinValue)
      0
    else
      res.toInt
  }

}

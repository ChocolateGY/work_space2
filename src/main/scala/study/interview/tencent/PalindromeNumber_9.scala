package study.interview.tencent

/**
  * 9. 回文数
  * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
  *
  * 示例 1:
  *
  * 输入: 121
  * 输出: true
  * 示例 2:
  *
  * 输入: -121
  * 输出: false
  * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
  * 示例 3:
  *
  * 输入: 10
  * 输出: false
  * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
  *
  * 进阶:
  *
  * 你能不将整数转为字符串来解决这个问题吗？
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/palindrome-number
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object PalindromeNumber_9 {
  /**
    * 所以这个解法的操作就是 取出后半段数字进行翻转。
    *
    * 这里需要注意的一个点就是由于回文数的位数可奇可偶，所以当它的长度是偶数时，它对折过来应该是相等的；当它的长度是奇数时，那么它对折过来后，有一个的长度需要去掉一位数（除以 10 并取整）。
    *
    * 具体做法如下：
    *
    * 每次进行取余操作 （ %10），取出最低的数字：y = x % 10
    * 将最低的数字加到取出数的末尾：revertNum = revertNum * 10 + y
    * 每取一个最低位数字，x 都要自除以 10
    * 判断 x 是不是小于 revertNum ，当它小于的时候，说明数字已经对半或者过半了
    * 最后，判断奇偶数情况：如果是偶数的话，revertNum 和 x 相等；如果是奇数的话，最中间的数字就在revertNum 的最低位上，将它除以 10 以后应该和 x 相等。
    *
    * 作者：MisterBooo
    * 链接：https://leetcode-cn.com/problems/palindrome-number/solution/dong-hua-hui-wen-shu-de-san-chong-jie-fa-fa-jie-ch/
    * 来源：力扣（LeetCode）
    * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    */
  def isPalindrome(x: Int): Boolean = {
    if (x < 0 || (x % 10 == 0 && x != 0)) return false
    var revertN = 0
    var n = x
    while (n > revertN) {
      revertN = revertN * 10 + n % 10
      n /= 10
    }
    n == revertN || n == revertN / 10
  }
}

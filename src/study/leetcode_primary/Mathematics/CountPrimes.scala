package study.leetcode_primary.Mathematics

/**
  * Created by root on 2018-8-6.
  *
  * 计数质数
  *
  * Count the number of prime numbers less than a non-negative number, n.
  * 统计所有小于非负整数 n 的质数的数量。
  * *
  * Example:
  * *
  * Input: 10
  * Output: 4
  * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
  *
  *
  */
object CountPrimes {
  /** *
    *
    */
  /*  def countPrimes(n: Int): Int = {
    (2 until n).foreach{
      x=>
        val count = (1 to x).count{
          s=>
            x % s == 0
        }
        if (count <= 2)
          println(x)
    }
    0
  }*/

  /** 埃拉托斯特尼筛法，简称埃氏筛或爱氏筛，是一种由希腊数学家埃拉托斯特尼所提出的一种简单检定素数的算法：
    * 要得到自然数n以内的全部素数，必须把不大于根号n的所有素数的倍数剔除，剩下的就是素数。
    *
    *
    * 这道题给定一个非负数n，让我们求小于n的质数的个数，题目中给了充足的提示，解题方法就在第二个提示埃拉托斯特尼筛法Sieve of Eratosthenes中，
    * 这个算法的过程如下图所示，我们从2开始遍历到根号n，先找到第一个质数2，然后将其所有的倍数全部标记出来，然后到下一个质数3，
    * 标记其所有倍数，一次类推，直到根号n，此时数组中未被标记的数字就是质数。我们需要一个n-1长度的bool型数组来记录每个数字是否被标记，
    * 长度为n-1的原因是题目说是小于n的质数个数，并不包括n。 然后我们用两个for循环来实现埃拉托斯特尼筛法，难度并不是很大，代码如下所示：
    *
    * @param n
    * @return
    */
  def countPrimes2(n: Int): Int = {
    if(n<=1)
      return 0
    val limit = scala.math.sqrt(n).toInt
    val notPrime = new Array[Boolean](n)
    notPrime(0) = true
    notPrime(1) = true
    // 如果i是一个质数，i将i的倍数设置为非质数
    // 如是i是一个合数，则它必定已经设置为true了，因为是从2开始处理的

    for (i <- 2 to limit) {
      if (!notPrime(i)) {
        var j = 2 * i
        while (j < n) {
          notPrime(j) = true
          j += i
        }
      }
    }
    notPrime.count(!_)
  }

  def main(args: Array[String]): Unit = {
    val notPrime = new Array[Boolean](2)
    println(notPrime(0))
    println(scala.math.sqrt(17).toInt)
    println(countPrimes2(10))
  }
}

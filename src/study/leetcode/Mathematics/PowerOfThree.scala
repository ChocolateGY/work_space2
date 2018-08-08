package study.leetcode.Mathematics

/**
  * Created by root on 2018-8-6.
  *
  *
  * 3的幂
  *
  * Given an integer, write a function to determine if it is a power of three.
  * *
  * Example 1:
  * *
  * Input: 27
  * Output: true
  * Example 2:
  * *
  * Input: 0
  * Output: false
  * Example 3:
  * *
  * Input: 9
  * Output: true
  * Example 4:
  * *
  * Input: 45
  * Output: false
  * Follow up:
  * Could you do it without using any loop / recursion?
  * *
  *
  * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
  * 你能不使用循环或者递归来完成本题吗？
  */
object PowerOfThree {
  def isPowerOfThree(n: Int): Boolean = {
    //    一个数是3的次方，那么以3为底n的对数一定是个 整数。
    if (n <= 0)
      false
    else {
      val re = math.log10(n) / math.log10(3) //这里log以10为底会
      re - re.toInt == 0
    }
  }

  /**
    * 3的幂数的特点是如果这个数是3的幂数，它所有的约数都是三的幂数，
    * 我们可以借助Math类的一些方法求出int不溢出情况下最大的3的幂数，然后判断所输入的数x是否能被最大幂数整除。
    */

  def isPowerOfThree2(n: Int): Boolean = {
    if (n > 0) {
      val max = math.pow(3.0, (math.log(Int.MaxValue) / math.log(3)).toInt)
      max % n == 0
    } else
      false
  }

  /** *
    * 循环法
    * 一直除3取余数，如果最后余数是1，则是3的幂数
    */

  def isPowerOfThree3(n: Int): Boolean = {
    if (n > 0) {
      var flag = true
      var num = n
      while (num > 1) {
        if (num % 3 != 0)
          flag = false
        num = num / 3
      }
      flag
    } else
      false
  }

  /**
    * 枚举法
    */
  def isPowerOfThree_Test() = {
    var s = 1
    var count = 3.0
    while (count < Int.MaxValue) {
      s += 1
      count = math.pow(3, s)
      println(s"s:$s \n count: $count")
    }
    //一共有20个值
  }

  def main(args: Array[String]): Unit = {
    println(isPowerOfThree(243))
    println(math.log(243))
    println(math.log(3))
    println(math.log(243) / math.log(3))
    println(math.log(243) / math.log(3) - (math.log(243) / math.log(3)).toInt)
    //    5.493061443340548
    //    1.0986122886681098
    //    4.999999999999999
    //    0.9999999999999991

    println(math.log10(243))
    println(math.log10(3))
    println(math.log10(243) / math.log10(3))
    println(math.log10(243) / math.log10(3) - (math.log10(243) / math.log10(3)).toInt)
    //    2.385606273598312
    //    0.47712125471966244
    //    5.0
    //    0.0

  }
}

/** *
  * 扩展
  * 1、判断是不是2的幂
  * *
  *
  *
  * 将2的幂写成二进制很容易看出，2的幂的二进制只有一个1，其余全是0，如下所示：
  * *
  *000010000...00
  * *
  * 而将2的幂的二进制减1，其二进制变为：
  * *
  *000001111...11
  * *
  * 所以判断一个数是不是2的幂的方法为使用按位与操作，如果结果为0，则是2的幂：
  * *
  * n & (n-1)
  * *
  * 2、判断是不是4的幂
  * *
  *
  *
  * 4的幂首先是2的幂，因为4^n = (2^2)^n，所以4的幂的二进制同样只有一个1，与2的幂不同的是，4的幂的二进制的1在偶数位上，所以判断一个数是不是4的幂的方式为：
  * *
  * 1）首先判断是不是2的幂，使用 n & (n-1)
  * *
  * 2）进一步判断与0x55555555的按位与结果，0x55555555是用十六进制表示的数，其奇数位上全是1，偶数位上全是0，判断 n & 0x55555555
  **/

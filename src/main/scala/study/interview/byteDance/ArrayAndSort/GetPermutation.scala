package study.interview.byteDance.ArrayAndSort

/**
  * 第k个排列
  * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
  *
  * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
  *
  * "123"
  * "132"
  * "213"
  * "231"
  * "312"
  * "321"
  * 给定 n 和 k，返回第 k 个排列。
  *
  * 说明：
  *
  * 给定 n 的范围是 [1, 9]。
  * 给定 k 的范围是[1,  n!]。
  * 示例 1:
  *
  * 输入: n = 3, k = 3
  * 输出: "213"
  * 示例 2:
  *
  * 输入: n = 4, k = 9
  * 输出: "2314"
  */
object GetPermutation {
  def main(args: Array[String]): Unit = {
    //    println(getPermutation(3, 3))
    //    println(getPermutation(4, 9))
    //    val list = scala.collection.mutable.ListBuffer[Int]((1 to 5): _*)
    //    println(list(3))
    //    list -= list(3)
    //    println(list(3))
    //    println((1 to 3).product)
    //    println((1 to 4).product)
        println((1 to 0).product)
    println(getPermutation2(3, 3))
    println(getPermutation2(4, 9))
    println(getPermutation2(2,2))
  }

  /**
    *
    * 暴力解法 超时
    * violent
    *
    * @param n
    * @param k
    * @return
    */

  def getPermutation(n: Int, k: Int): String = {
    val range = Range(1, n + 1)
    val arr = range.permutations.map(_.mkString("")).toArray.sorted
    arr(k - 1)
  }

  /**
    * 先把1到n的每个数存入list中，然后后边写一个数字，就可以从里面删掉一个了。
    * n个数字有n!个排列方式，n-1就有(n-1)！个排列方式，
    * 为了和下标保持一致，需要k--，k/（n-1）！是当前字符的下标（注意是从零开始啊，下标0对应的数字1）
    * 下一次的k就可以更新为 k%(n-1)!，循环n次
    * 需要一个times表示每次更新阶乘的除数。
    * ————————————————
    * 版权声明：本文为CSDN博主「Lynn_Baby」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    * 原文链接：https://blog.csdn.net/Lynn_Baby/article/details/80948414
    *
    * @param n
    * @param k
    * @return
    */
  def getPermutation2(n: Int, k: Int): String = {
    val list = scala.collection.mutable.ListBuffer[Int]((1 to n): _*)
    val str = new StringBuilder()
    var q = k -1
    for (i <- 1 to n) {
      val factorial = fac(n - i)
      val index = q / factorial
      str.append(list(index))
      list -= list(index)
      q %= factorial
    }

    def fac(i: Int): Int = (1 to i).product

    str.toString
  }
}

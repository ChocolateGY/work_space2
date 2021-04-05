package study.RecursivePrinciple

/**
  * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
  *
  * F(0) = 0,   F(1) = 1
  * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
  * 给定 N，计算 F(N)。
  *
  *  
  *
  * 示例 1：
  *
  * 输入：2
  * 输出：1
  * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
  * 示例 2：
  *
  * 输入：3
  * 输出：2
  * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
  * 示例 3：
  *
  * 输入：4
  * 输出：3
  * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
  *  
  *
  * 提示：
  *
  * 0 ≤ N ≤ 30
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/fibonacci-number
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object Fibonacci_509 {
  val map = collection.mutable.Map[Int, Int]((0, 0), (1, 1))

  def fib(N: Int): Int = {
    val res = map.get(N)
    if (res.nonEmpty)
      res.get
    else {
      val a = fib(N - 1) + fib(N - 2)
      map += ((N, a))
      a
    }
  }

  def fib2(N: Int): Int = {
    (1 to N).foldLeft((0, 1)) {
      case ((a, b), _) =>
        (b, a + b)
    }._1
    //    ((0, 1) /: (1 to N)) {
    //      case ((a, b), _) => (b, a + b)
    //    }._1
  }

  def fib3(N: Int): Int = {
    var res = 0
    var n1 = 1
    var n2 = 1
    if (N <= 1) return N
    if (N == 2) return 1
    for (i <- 3 to N) {
      res = n1 + n2
      n2 = n1
      n1 = res
    }
    res
  }

  def main(args: Array[String]): Unit = {
    //    val res = fib(4)
    val res = fib2(4)
    println(res)

  }
}

package study.interview.prepare.DynamicProgramming

object Fibonacci {
  //动态规划遵循一套固定的流程：递归的暴力解法 -> 带备忘录的递归解法 -> 非递归的动态规划解法。

  def main(args: Array[String]): Unit = {
    println(fib1(5))
    println(fib2(5))

  }

  //暴力解法
  def fib1(n: Int): Int = {
    if (n == 1 || n == 2)
      return 1
    fib1(n - 1) + fib1(n - 2)
  }

  //带数组的递归解法，自顶向下，从上面往下分解
  def fib2(n: Int): Int = {
    val arr = Array.fill(n + 1)(0)

    def helper(i: Int): Int = {
      if (i > 0 && arr(i) == 0)
        arr(i) = helper(i - 1) + helper(i - 2)
      arr(i)
    }

    arr(1) = 1
    arr(2) = 1
    helper(n)
  }

  //动态规划，从底向上，用循环
  def fib3(n: Int): Int = {
    var dp = Array.fill(n + 1)(0)
    dp(1) = 1
    dp(2) = 2
    for (i <- 3 to n) {
      dp(i) = dp(i - 1) + dp(i - 2)
    }
    dp(n)
  }

  //动态规划，状态转移，
  def fib4(n: Int): Int = {
    if (n < 2) return n
    var pre = 0
    var cur = 1
    for (i <- 0 until n - 1) {
      val sum = pre + cur
      pre = cur
      cur = sum
    }
    cur
  }
}

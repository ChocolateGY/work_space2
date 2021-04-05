package study.interview.prepare.DynamicProgramming

/**
  * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
  *
  * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
  *
  * 注意：给定 n 是一个正整数。
  */
object ClimbStairs {
  /**
    * 暴力
    */
  def climbStairs1(n: Int): Int = {
    def recursive(s: Int): Int = {
      if (s > n)
        0
      else if (s == n)
        1
      else
        recursive(s + 1) + recursive(s + 2)
    }

    recursive(0)
  }

  //带数组的递归
  def climbStairs2(n: Int): Int = {
    val arr = Array.fill(n + 1)(0)

    def recursive(s: Int): Int = {
      if (s > n)
        0
      else if (s == n)
        1
      else {
        if (arr(s) == 0)
          arr(s) = recursive(s + 1) + recursive(s + 2)
        arr(s)
      }
    }

    recursive(0)
  }

  //动态规划
  def climbStairs3(n: Int): Int = {
    val dp = Array.fill(n + 1)(0)

    if (n == 1) return 1
    dp(1) = 1
    dp(2) = 2
    for (i <- 3 to n) {
      dp(i) = dp(i - 1) + dp(i - 2)
    }
    dp(n)
  }

}

package study.leetcode_primary.Dynamicplanning

/**
  *
  * You are climbing a stair case. It takes n steps to reach to the top.
  * *
  * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
  * *
  * Note: Given n will be a positive integer.
  * *
  * 假设你正在爬楼梯。需要 n 步你才能到达楼顶。
  * *
  * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
  * *
  * 注意：给定 n 是一个正整数。
  * *
  * Example 1:
  * *
  * Input: 2
  * Output: 2
  * Explanation: There are two ways to climb to the top.
  *1. 1 step + 1 step
  *2. 2 steps
  * Example 2:
  * *
  * Input: 3
  * Output: 3
  * Explanation: There are three ways to climb to the top.
  *1. 1 step + 1 step + 1 step
  *2. 1 step + 2 steps
  *3. 2 steps + 1 step
  *
  *
  * Created by root on 2018-6-28.
  */
object ClimbingStairs {
  /**
    * 所以动态规划的思想就是把重叠子问题存储下来，下次调用直接查表即可。
    * 比如在第一次递归调用时，n=3已经将结果计算出来，在n=4的时候就不需要就算了，上代码：
    *
    * @param n
    * @return
    */
  def climbStairs(n: Int): Int = {
    if (n == 0 || n == 1 || n == 2)
      n
    else {
      val arr = new Array[Int](n + 1)
      arr(1) = 1
      arr(2) = 2
      for (i <- 3 to n) {
        arr(i) = arr(i - 1) + arr(i - 2)
      }
      arr(n)
    }
  }
}

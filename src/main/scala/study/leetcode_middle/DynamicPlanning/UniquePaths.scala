package study.leetcode_middle.DynamicPlanning

/**
  * Created by root on 2018-9-5.
  * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
  * *
  * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
  * *
  * 问总共有多少条不同的路径？
  * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
  * *
  * 说明：m 和 n 的值均不超过 100。
  * *
  * 示例 1:
  * *
  * 输入: m = 3, n = 2
  * 输出: 3
  * 解释:
  * 从左上角开始，总共有 3 条路径可以到达右下角。
  *1. 向右 -> 向右 -> 向下
  *2. 向右 -> 向下 -> 向右
  *3. 向下 -> 向右 -> 向右
  * 示例 2:
  * *
  * 输入: m = 7, n = 3
  * 输出: 28
  */
object UniquePaths {
  def uniquePaths(m: Int, n: Int): Int = {
    val dp = new Array[Array[Int]](n).map(x=>new Array[Int](m))
    for (i <- 0 until n) {
      dp(i)(0) = 1
    }
    for (i <- 0 until m) {
      dp(0)(i) = 1
    }
    for (i <- 1 until n) {
      for (j <- 1 until m) {
        dp(i)(j) = dp(i - 1)(j) + dp(i)(j - 1)
      }
    }
    dp(n - 1)(m - 1)
  }

  def main(args: Array[String]): Unit = {
    println(uniquePaths(7,3))
  }
}

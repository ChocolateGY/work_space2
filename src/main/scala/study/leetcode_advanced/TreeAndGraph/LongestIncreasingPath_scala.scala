package study.leetcode_advanced.TreeAndGraph


object LongestIncreasingPath_scala {
  private val dir = Array(Array(0, -1), Array(-1, 0), Array(0, 1), Array(1, 0))

  def longestIncreasingPath(matrix: Array[Array[Int]]): Int = {
    if (matrix != null && matrix.length != 0) {
      val n = matrix.length
      val m = matrix(0).length
      val dp = Array.ofDim[Int](n, m)

      var max = 0
      for (i <- 0 until n; j <- 0 until m) {
        max = max.max(dfs(matrix, dp, i, j))
      }
      max
    } else
      0
  }

  def dfs(matrix: Array[Array[Int]], dp: Array[Array[Int]], i: Int, j: Int): Int = {
    if (dp(i)(j) > 0)
      dp(i)(j)
    else {
      val n = matrix.length
      val m = matrix(0).length
      var max = 1
      for (k <- 0 to 3) {
        val x = dir(k)(0) + i
        val y = dir(k)(1) + j
        if (x >= 0 && y >= 0 && x < n && y < m && matrix(x)(y) > matrix(i)(j)) {
          max = max.max(dfs(matrix, dp, x, y) + 1)
        }
      }
      dp(i)(j) = max
      max
    }
  }

  def main(args: Array[String]): Unit = {
    val matrix = Array(Array(9, 9, 4), Array(6, 6, 8), Array(2, 1, 1))
    val res = longestIncreasingPath(matrix)
    println(res)
  }
}

package study.interview.byteDance.ArrayAndSort

/**
  * 岛屿的最大面积
  * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
  *
  * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
  *
  * 示例 1:
  *
  * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
  *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
  * [0,1,1,0,1,0,0,0,0,0,0,0,0],
  * [0,1,0,0,1,1,0,0,1,0,1,0,0],
  * [0,1,0,0,1,1,0,0,1,1,1,0,0],
  * [0,0,0,0,0,0,0,0,0,0,1,0,0],
  * [0,0,0,0,0,0,0,1,1,1,0,0,0],
  * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
  * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
  *
  * 示例 2:
  *
  * [[0,0,0,0,0,0,0,0]]
  * 对于上面这个给定的矩阵, 返回 0。
  *
  * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
  */
object MaxAreaOfIsland {
  def main(args: Array[String]): Unit = {
    val grid = Array(
      Array(0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
      Array(0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0),
      Array(0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0)
    )
    val res = maxAreaOfIsland(grid)
    println(res)
  }

  /**
    *
    *解法：这道题我们可以利用深度优先遍历的思想，如果当前位置是1（岛屿），那么我们寻找当前位置上下左右是岛屿的位置，在下个位置重复当前的操作，直到岛屿四周都是0（海水）为止；我们利用递归来实现这个算法，需要借助一个额外的二维数组来标记访问过的位置；
    * @param grid
    * @return
    */
  def maxAreaOfIsland(grid: Array[Array[Int]]): Int = {
    var max = 0
    var num = 0
    val grid_bak = Array.fill(grid.length, grid(0).length)(0)

    for (i <- grid.indices) {
      for (j <- grid(0).indices) {
        count(i, j)
        max = max.max(num)
        num = 0
      }
    }

    def count(i: Int, j: Int): Unit = {
      if (i >= 0 && i < grid.length && j >= 0 && j < grid(0).length) {
        if (grid_bak(i)(j) == 0) {
          grid_bak(i)(j) = 1
          if (grid(i)(j) == 1) {
            num += 1
            count(i + 1, j)
            count(i, j + 1)
            count(i - 1, j)
            count(i, j - 1)
          }
        }
      }
    }

    max
  }
}

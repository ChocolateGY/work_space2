package study.leetcode_middle.GraphAndTree

/**
  * Created by root on 2018-8-28.
  *
  * 岛屿的个数
  * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
  * *
  * 示例 1:
  * *
  * 输入:
  * 11110
  * 11010
  * 11000
  * 00000
  * *
  * 输出: 1
  * 示例 2:
  * *
  * 输入:
  * 11000
  * 11000
  * 00100
  * 00011
  * *
  * 输出: 3
  */
object NumIslands {

  /**
    * 错误方法
    * @param grid
    * @return
    */

  def numIslands(grid: Array[Array[Char]]): Int = {
    var sum = 0
    for (i <- grid.indices) {
      for (j <- grid(0).indices) {
        if (grid(i)(j) == '1') {
          val up = i - 1 < 0 || grid(i - 1)(j) == '0'
          val down = i + 1 >= grid.length || grid(i + 1)(j) == '0'
          val left = j - 1 < 0 || grid(i)(j - 1) == '0'
          val right = j + 1 >= grid(0).length || grid(i)(j + 1) == '0'
          if (sum == 0)
            sum += 1
          else if (up && down && left && right)
            sum += 1
        }
      }
    }
    sum
  }

  /**
    * 这道求岛屿数量的题的本质是求矩阵中连续区域的个数，很容易想到需要用深度优先搜索DFS来解，
    * 我们需要建立一个visited数组用来记录某个位置是否被访问过，对于一个为‘1’且未被访问过的位置，
    * 我们递归进入其上下左右位置上为‘1’的数，将其visited对应值赋为true，继续进入其所有相连的邻位置，
    * 这样可以将这个连通区域所有的数找出来，并将其对应的visited中的值赋true，
    * 找完次区域后，我们将结果res自增1，然后我们在继续找下一个为‘1’且未被访问过的位置，
    * 以此类推直至遍历完整个原数组即可得到最终结果，代码如下：
    *
    * @param grid
    * @return
    */
  def numIslands2(grid: Array[Array[Char]]): Int = {
    if (grid == null || grid.isEmpty || grid(0).isEmpty)
      return 0

    var count = 0
    for (i <- grid.indices) {
      for (j <- grid(0).indices) {
        if (grid(i)(j) == '1') {
          count += 1
          combine(grid, i, j)
        }
      }
    }
    count
  }

  def combine(grid: Array[Array[Char]], i: Int, j: Int): Unit = {
    //标识遍历过
    grid(i)(j) = '2'
    if (i > grid.length - 1 && j > grid(0).length - 1) {
      return
    }
    //判断上面的元素
    if (i < grid.length - 1 && grid(i + 1)(j) == '1') {
      combine(grid, i + 1, j)
    }
    //判断右边的元素
    if (j < grid(0).length - 1 && grid(i)(j + 1) == '1') {
      combine(grid, i, j + 1)
    }
    //判断下面的元素
    if (i > 0 && grid(i - 1)(j) == '1') {
      combine(grid, i - 1, j)
    }
    //判断左边的元素
    if (j > 0 && grid(i)(j - 1) == '1')
      combine(grid, i, j - 1)
  }

}

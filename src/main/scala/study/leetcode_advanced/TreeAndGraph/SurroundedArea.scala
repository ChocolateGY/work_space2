package study.leetcode_advanced.TreeAndGraph

/**
  * 被围绕的区域
  * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
  *
  * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
  *
  * 示例:
  *
  * X X X X
  * X O O X
  * X X O X
  * X O X X
  * 运行你的函数后，矩阵变为：
  *
  * X X X X
  * X X X X
  * X X X X
  * X O X X
  * 解释:
  *
  * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
  * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
  */
object SurroundedArea {
  def main(args: Array[String]): Unit = {
    val board = Array(Array('X', 'X', 'X', 'X'),
      Array('X', 'O', 'O', 'X'),
      Array('X', 'X', 'O', 'X'),
      Array('X', 'O', 'X', 'X'))

      solve(board)
      board.foreach(x=>println(x.mkString(" ")))
  }


  var dep, wid = 0
  var map = Array[Array[Char]]()

  def solve(board: Array[Array[Char]]): Unit = {
    map = board
    dep = board.length
    if (dep == 0) return
    wid = board(0).length
    for (i <- board.indices) {
      if (board(i)(0) == 'O') dfs(i, 0)
      if (board(i)(wid - 1) == 'O') dfs(i, wid - 1)
    }
    for (i <- board(0).indices) {
      if (board(0)(i) == 'O') dfs(0, i)
      if (board(dep - 1)(i) == 'O') dfs(dep - 1, i)
    }
    for (i <- board.indices) {
      for (j <- board(0).indices) {
        if (map(i)(j) == 'O') map(i)(j) = 'X'
        else if (map(i)(j) == 'Q') map(i)(j) = 'O'
      }
    }
  }

  def dfs(depth: Int, width: Int): Unit = {
    if (depth < 0 || depth >= dep || width < 0 || width >= wid || map(depth)(width) != 'O') return
    map(depth)(width) = 'Q'
    dfs(depth - 1, width)
    dfs(depth + 1, width)
    dfs(depth, width - 1)
    dfs(depth, width + 1)
  }

  /**
    * 时间最短
    *
    * @param board
    */
  def solve2(board: Array[Array[Char]]): Unit = {
    if (board.length == 0) return
    var l = List[(Int, Int)]()
    for {
      i <- Set(0, board.length - 1)
      j <- board(i).indices
      if board(i)(j) == 'O'
    } l ::= (i, j)

    for {
      i <- 1 to board.length - 2
      j <- Set(0, board(i).indices.last)
      if board(i)(j) == 'O'
    } l ::= (i, j)
    val n = board.length
    val m = board(0).length
    val ans = Array.fill(board.length, board(0).length)('X')

    def inBound(n: Int, m: Int)(x: (Int, Int)): Boolean = x._1 >= 0 && x._2 >= 0 && x._1 < n && x._2 < m

    val dir = List((1, 0), (-1, 0), (0, 1), (0, -1))
    while (l.nonEmpty) {
      val h = l.head
      l = l.tail
      ans(h._1)(h._2) = 'O'
      l = dir
        .map(x => (x._1 + h._1, x._2 + h._2))
        .filter(inBound(n, m))
        .filter(x => ans(x._1)(x._2) == 'X')
        .filter(x => board(x._1)(x._2) == 'O') ++ l
    }
    for {
      i <- board.indices
      j <- board(i).indices
    } board(i)(j) = ans(i)(j)
  }
}

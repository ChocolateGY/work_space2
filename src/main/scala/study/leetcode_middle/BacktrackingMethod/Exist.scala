package study.leetcode_middle.BacktrackingMethod

/**
  * Created by root on 2018-8-30.
  * 单词搜索
  * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
  * *
  * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
  * *
  * 示例:
  * *
  * board =
  * [
  * ['A','B','C','E'],
  * ['S','F','C','S'],
  * ['A','D','E','E']
  * ]
  * *
  * 给定 word = "ABCCED", 返回 true.
  * 给定 word = "SEE", 返回 true.
  * 给定 word = "ABCB", 返回 false.
  *
  */
object Exist {
  def exist(board: Array[Array[Char]], word: String): Boolean = {
    var flag = false
    for (i <- board.indices if !flag) {
      for (j <- board(0).indices if !flag) {
        val gird = new Array[Array[Int]](board.length).map(x => new Array[Int](board(0).length))
        flag = find(word, 0, i, j, board, gird)
      }
    }
    flag
  }

  def find(str: String, num: Int, i: Int, j: Int, board: Array[Array[Char]], gird: Array[Array[Int]]): Boolean = {
    if (num < str.length - 1 && str(num) == board(i)(j)) {
      gird(i)(j) = 1
      val up = if (i >= 1 && gird(i - 1)(j) == 0) {
        find(str, num + 1, i - 1, j, board, gird)
      } else false
      val down = if (i + 1 < gird.length && gird(i + 1)(j) == 0) {
        find(str, num + 1, i + 1, j, board, gird)
      } else false
      val left = if (j >= 1 && gird(i)(j - 1) == 0) {
        find(str, num + 1, i, j - 1, board, gird)
      } else false
      val right = if (j + 1 < gird(0).length && gird(i)(j + 1) == 0) {
        find(str, num + 1, i, j + 1, board, gird)
      } else false
      val flag = up || down || left || right
      //如果这条路没走通，则标记还原为0
      if (!flag)
        gird(i)(j) = 0
      flag
    } else if (num == str.length - 1 && str(num) == board(i)(j)) {
      true
    } else
      false

  }

  def main(args: Array[String]): Unit = {
    val arr = Array(
      Array('A', 'B', 'C', 'E'),
      Array('S', 'F', 'E', 'S'),
      Array('A', 'D', 'E', 'E')
    )
    val flag = exist(arr, "ABCESEEEFS")
    println(flag)

  }
}

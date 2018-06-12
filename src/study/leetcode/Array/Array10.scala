package study.leetcode.Array

/**
  *
  * 有效的数独
  * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
  * *
  * 数字 1-9 在每一行只能出现一次。
  * 数字 1-9 在每一列只能出现一次。
  * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
  * *
  * 数独部分空格内已填入了数字，空白格用 '.' 表示。
  * *
  * 示例 1:
  * *
  * 输入:
  * [
  * ["5","3",".",".","7",".",".",".","."],
  * ["6",".",".","1","9","5",".",".","."],
  * [".","9","8",".",".",".",".","6","."],
  * ["8",".",".",".","6",".",".",".","3"],
  * ["4",".",".","8",".","3",".",".","1"],
  * ["7",".",".",".","2",".",".",".","6"],
  * [".","6",".",".",".",".","2","8","."],
  * [".",".",".","4","1","9",".",".","5"],
  * [".",".",".",".","8",".",".","7","9"]
  * ]
  * 输出: true
  * *
  * 示例 2:
  * *
  * 输入:
  * [
  * ["8","3",".",".","7",".",".",".","."],
  * ["6",".",".","1","9","5",".",".","."],
  * [".","9","8",".",".",".",".","6","."],
  * ["8",".",".",".","6",".",".",".","3"],
  * ["4",".",".","8",".","3",".",".","1"],
  * ["7",".",".",".","2",".",".",".","6"],
  * [".","6",".",".",".",".","2","8","."],
  * [".",".",".","4","1","9",".",".","5"],
  * [".",".",".",".","8",".",".","7","9"]
  * ]
  * 输出: false
  * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
  * 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
  * 说明:
  * *
  * 一个有效的数独（部分已被填充）不一定是可解的。
  * 只需要根据以上规则，验证已经填入的数字是否有效即可。
  * 给定数独序列只包含数字 1-9 和字符 '.' 。
  * 给定数独永远是 9x9 形式的。
  *
  *
  * Created by root on 2018-6-1.
  */
object Array10 {
  def isValidSudoku(board: Array[Array[Char]]): Boolean = {
    var flag = true
    for (x <- board if flag) {
      if (x.count(_ != '.') != x.filter(_ != '.').toSet.size)
        flag = false
    }
    for (y <- board.indices if flag) {
      val arr = new Array[Char](9)
      for (x <- board.indices if flag) {
        arr(x) = board(x)(y)
      }
      if (arr.count(_ != '.') != arr.filter(_ != '.').toSet.size)
        flag = false
    }
    for (x <- board.indices; y <- board.indices; if flag) {
      if (x % 3 == 0 && y % 3 == 0) {
        val arr = new collection.mutable.ArrayBuffer[Char]()
        for (i <- x until x + 3; j <- y until y + 3) {
          arr += board(i)(j)
        }
        if (arr.count(_ != '.') != arr.filter(_ != '.').toSet.size)
          flag = false
      }

    }
    flag
  }


  def isValidSudoku2(board: Array[Array[Char]]): Boolean = {
    var flag = true
    val col = new collection.mutable.ArrayBuffer[Char]()
    val block = new collection.mutable.ArrayBuffer[Char]()
    for (x <- board.indices if flag) {
      col.clear()
      for (y <- board.indices if flag) {

        //row
        if (board(x).count(_ != '.') != board(x).filter(_ != '.').toSet.size)
          flag = false

        //col
        col += board(y)(x)
        if (col.count(_ != '.') != col.filter(_ != '.').toSet.size)
          flag = false

        //block
        if (x % 3 == 0 && y % 3 == 0 && flag) {
          block.clear()
          for (i <- x until x + 3; j <- y until y + 3) {
            block += board(i)(j)
          }
          if (block.count(_ != '.') != block.filter(_ != '.').toSet.size)
            flag = false
        }
      }
    }
    flag
  }

}

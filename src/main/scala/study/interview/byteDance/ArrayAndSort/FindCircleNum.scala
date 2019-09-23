package study.interview.byteDance.ArrayAndSort

/**
  * 朋友圈
  * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
  *
  * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
  *
  * 示例 1:
  *
  * 输入:
  * [[1,1,0],
  *  [1,1,0],
  * [0,0,1]]
  * 输出: 2
  * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
  * 第2个学生自己在一个朋友圈。所以返回2。
  * 示例 2:
  *
  * 输入:
  * [[1,1,0],
  *  [1,1,1],
  * [0,1,1]]
  * 输出: 1
  * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
  * 注意：
  *
  * N 在[1,200]的范围内。
  * 对于所有学生，有M[i][i] = 1。
  * 如果有M[i][j] = 1，则有M[j][i] = 1。
  */
object FindCircleNum {

  def main(args: Array[String]): Unit = {
    val M1 = Array(
      Array(1, 1, 0),
      Array(1, 1, 0),
      Array(0, 0, 1)
    )
    val M2 = Array(
      Array(1, 1, 0),
      Array(1, 1, 1),
      Array(0, 1, 1)
    )
    val M3 = Array(
      Array(1, 0, 0, 1),
      Array(0, 1, 1, 0),
      Array(0, 1, 1, 1),
      Array(1, 0, 1, 1))
    println(findCircleNum(M1))
    println(findCircleNum(M2))
    println(findCircleNum(M3))
  }

  /**
    *
    * @param M
    * @return
    */
  def findCircleNum(M: Array[Array[Int]]): Int = {
    //与MaxAreaOfIsland不同，这个相当于是记录每个人是否已经在某个圈子，并顺着圈子把所有人标记。
    val tArr = Array.fill(M.length)(false)
    var res = 0
    for (i <- M.indices) {
      //没标记过的说明是一个新的圈子
      if (!tArr(i)) {
        res += 1
        recursive(i)
      }
    }

    def recursive(i: Int): Unit = {
      for (j <- M.indices) {
        if (!tArr(j) && M(i)(j) == 1) {
          tArr(j) = true
          recursive(j)
        }
      }
    }

    res
  }
}

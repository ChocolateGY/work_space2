package study.RecursivePrinciple

/**
  *119. 杨辉三角 II
  * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
  * *
  *
  *
  * 在杨辉三角中，每个数是它左上方和右上方的数的和。
  * * [
  * * [1],
  * * [1,1],
  * * [1,2,1],
  * * [1,3,3,1],
  * * [1,4,6,4,1]
  * * ]
  * *
  * 示例:
  * *
  * 输入: 3
  * 输出: [1,3,3,1]
  * 进阶：
  * *
  * 你可以优化你的算法到 O(k) 空间复杂度吗？
  */
object YangHuiTriangle2_119 {
  def getRow(rowIndex: Int): List[Int] = {
    val arr = collection.mutable.ArrayBuffer[Int](1)
    for (i <- 1 to rowIndex) {
      for (j <- Range(i - 1, 0, -1)) {
        arr(j) = arr(j - 1) + arr(j)
      }
      arr += 1
    }
    arr.toList
  }

  def main(args: Array[String]): Unit = {
    val res = getRow(1)
    res.foreach(print)
  }
}

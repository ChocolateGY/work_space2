package study.leetcode_middle.ArrayAndString

/**
  * Created by root on 2018-8-13.
  *
  * 矩阵置零
  *
  *
  * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
  * *
  * 示例 1:
  * *
  * 输入:
  * [
  * [1,1,1],
  * [1,0,1],
  * [1,1,1]
  * ]
  * 输出:
  * [
  * [1,0,1],
  * [0,0,0],
  * [1,0,1]
  * ]
  * 示例 2:
  * *
  * 输入:
  * [
  * [0,1,2,0],
  * [3,4,5,2],
  * [1,3,1,5]
  * ]
  * 输出:
  * [
  * [0,0,0,0],
  * [0,4,5,0],
  * [0,3,1,0]
  * ]
  * 进阶:
  * *
  * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
  * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
  * 你能想出一个常数空间的解决方案吗？
  */
object MatrixSetZeroes {
  def setZeroes(matrix: Array[Array[Int]]): Unit = {
    val setRow = collection.mutable.Set[Int]()
    val setCol = collection.mutable.Set[Int]()
    var i = 0
    while (i < matrix.length) {
      var j = 0
      while (j < matrix(i).length) {
        if (matrix(i)(j) == 0) {
          setRow += i
          setCol += j
        }
        j += 1
      }
      i += 1
    }
    for (i <- matrix.indices) {
      for (j <- matrix(i).indices) {
        if (setRow.contains(i) || setCol.contains(j))
          matrix(i)(j) = 0
      }
    }

  }

  /**
    * 实际上，我们只需要直到哪些行，哪些列需要被置0就行了，最简单的方法就是建两个大小分别为M和N的数组，来记录哪些行哪些列应该被置0。
    * 那有没有可能不用额外空间呢？我们其实可以借用原矩阵的首行和首列来存储这个信息。
    * 这个方法的缺点在于，如果我们直接将0存入首行或首列来表示相应行和列要置0的话，我们很难判断首行或者首列自己是不是该置0。
    * 这里我们用两个boolean变量记录下首行和首列原本有没有0，然后在其他位置置完0后，
    * 再单独根据boolean变量来处理首行和首列，就避免了干扰的问题。
    */
  def setZeroes2(matrix: Array[Array[Int]]): Unit = {
    if (matrix.length > 0) {
      var rowFlag = false
      var colFlag = false
      for (i <- matrix.indices) {
        for (j <- matrix(0).indices) {
          if (matrix(i)(j) == 0) {
            if (i == 0)
              rowFlag = true
            if (j == 0)
              colFlag = true
            matrix(0)(j) = 0
            matrix(i)(0) = 0
          }
        }
      }
            for (i <- 1 until matrix.length) {
              for (j <- 1 until matrix(0).length) {
                if (matrix(0)(j) == 0 || matrix(i)(0) == 0) {
                  matrix(i)(j) = 0
                }
              }
            }

      if (rowFlag)
        for (j <- matrix(0).indices)
          matrix(0)(j) = 0

      if (colFlag)
        for (i <- matrix.indices)
          matrix(i)(0) = 0
    }
  }
}

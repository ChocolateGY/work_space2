package study.leetcode_advanced.ArrayAndString

/**
  * 螺旋矩阵
  * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
  *
  * 示例 1:
  *
  * 输入:
  * [
  * [ 1, 2, 3 ],
  * [ 4, 5, 6 ],
  * [ 7, 8, 9 ]
  * ]
  * 输出: [1,2,3,6,9,8,7,4,5]
  * 示例 2:
  *
  * 输入:
  * [
  * [1, 2, 3, 4],
  * [5, 6, 7, 8],
  * [9,10,11,12]
  * ]
  * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
  */
object SpiralOrder {
  def spiralOrder(matrix: Array[Array[Int]]): List[Int] = {
    //    val flagMatrix = matrix.map(x => x.map(x => 0))
    //    val m = matrix.length
    //    val n = matrix(0).length
    //    val arr = new Array[Int](m * n)
    //    var x, y = 0
    //    for (i <- arr.indices) {
    //      arr(i) = arr(x)(y)
    //      if(flagMatrix(x)(y)==1)
    //    }
    List[Int]()
  }

  def spiralOrder2(matrix: Array[Array[Int]]): List[Int] = {
    val res = collection.mutable.ListBuffer[Int]()
    if (matrix == null || matrix.length == 0) {
      return res.toList
    }
    var numi = matrix.length
    var numj = matrix(0).length

    var loop = if (numi > numj) (numj + 1) / 2 else (numi + 1) / 2

    var i = 0
    while (i < loop) {
      var col = i
      while (col < i + numj) {
        res += matrix(i)(col)
        col += 1
      }
      var row = i + 1
      while (row < i + numi) {
        res += matrix(row)(i + numj - 1)
        row += 1
      }

      if (numi == 1 || numj == 1)
        return res.toList

      var col2 = i + numj - 2
      while (col2 >= i) {
        res += matrix(i + numi - 1)(col2)
        col2 -= 1
      }
      var row2 = i + numi - 2
      while (row2 > i) {
        res += matrix(row2)(i)
        row2 -= 1
      }

      i += 1
      numi -= 2
      numj -= 2
    }
    res.toList
  }

  /**
    * leetcode
    */
  def spiralOrder3(matrix: Array[Array[Int]]): List[Int] = {
    var result = List[Int]()
    if(matrix == null || matrix.isEmpty) return result
    val m = matrix.length
    val n = matrix(0).length
    var count = 0
    var left = 0
    var right = n - 1
    var top = 0
    var bottom = m  -1
    while(count < m * n){
      var x1 = left
      while(x1 <= right){
        result :+= matrix(top)(x1)
        count += 1
        x1 += 1
      }
      top += 1
      var x2 = top
      while(x2 <= bottom){
        result :+= matrix(x2)(right)
        count += 1
        x2 += 1
      }
      right -= 1
      var x3 = right
      while( x3 >= left){
        result :+= matrix(bottom)(x3)
        count += 1
        x3 -= 1
      }
      bottom -= 1
      var x4 = bottom
      while( x4 >= top){
        result :+= matrix(x4)(left)
        count += 1
        x4 -= 1
      }
      left += 1
    }
    if(result.length > m * n) result.take(m * n) else result
  }
}

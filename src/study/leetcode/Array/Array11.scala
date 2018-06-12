package study.leetcode.Array

/**
  *
  * 旋转图像
  * 给定一个 n × n 的二维矩阵表示一个图像。
  * *
  * 将图像顺时针旋转 90 度。
  * *
  * 说明：
  * *
  * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
  * *
  * 示例 1:
  * *
  * 给定 matrix =
  * (
  * (1,2,3),
  * (4,5,6),
  * (7,8,9)
  * ),
  * *
  * 原地旋转输入矩阵，使其变为:
  * (
  * (7,4,1),
  * (8,5,2),
  * (9,6,3)
  * )
  * 示例 2:
  * *
  * 给定 matrix =
  * (
  * ( 5, 1, 9,11),
  * ( 2, 4, 8,10),
  * (13, 3, 6, 7),
  * (15,14,12,16)
  * ),
  * *
  * 原地旋转输入矩阵，使其变为:
  * (
  * (15,13, 2, 5),
  * (14, 3, 4, 1),
  * (12, 6, 8, 9),
  * (16, 7,10,11)
  * )
  *
  * Created by root on 2018-6-1.
  */
object Array11 {
  /**
    * 在计算机图像处理里，旋转图片是很常见的，由于图片的本质是二维数组，所以也就变成了对数组的操作处理，翻转的本质就是某个位置上数移动到另一个位置上，比如用一个简单的例子来分析：
    * *
    * 1  2  3　　　 　　 7  4  1　
    * *
    * 4  5  6　　-->　　 8  5  2　　
    * *
    * 7  8  9 　　　 　　9  6  3
    * *
    * 对于90度的翻转有很多方法，一步或多步都可以解，我们先来看一种直接的方法，对于当前位置，计算旋转后的新位置，然后再计算下一个新位置，第四个位置又变成当前位置了，所以这个方法每次循环换四个数字，如下所示：
    * *
    * 1  2  3                 7  2  1                  7  4  1
    * *
    * 4  5  6      -->      4  5  6　　 -->  　 8  5  2　　
    * *
    * 7  8  9                 9  8  3　　　　　 9  6  3
    *
    * @param matrix
    */
  def rotate(matrix: Array[Array[Int]]): Unit = {
    val n = matrix.length
    for (x <- 0 until n / 2; y <- x until n - 1 - x) {
      val temp = matrix(x)(y)
      matrix(x)(y) = matrix(n - 1 - y)(x)
      matrix(n - 1 - y)(x) = matrix(n - 1 - x)(n - 1 - y)
      matrix(n - 1 - x)(n - 1 - y) = matrix(y)(n - 1 - x)
      matrix(y)(n - 1 - x) = temp
    }
  }

  /**
    * 还有一种解法，首先以从对角线为轴翻转，然后再以x轴中线上下翻转即可得到结果，如下图所示(其中蓝色数字表示翻转轴)：
    * *
    * 1  2  3　　　 　　 9  6  3　　　　　  7  4  1
    * *
    * 4  5  6　　-->　　 8  5  2　　 -->   　 8  5  2　　
    * *
    * 7  8  9 　　　 　　7  4  1　　　　　  9  6  3
    *
    * @param matrix
    */
  def rotate2(matrix: Array[Array[Int]]): Unit = {
    val n = matrix.length
    for (i <- 0 until n - 1; j <- 0 until n - 1) {
      val temp = matrix(i)(j)
      matrix(i)(j) = matrix(n - 1 - j)(n - 1 - i)
      matrix(n - 1 - j)(n - 1 - i) = temp
    }
    for (i <- 0 until n / 2; j <- 0 until n) {
      val temp = matrix(i)(j)
      matrix(i)(j) = matrix(n - 1 - i)(j)
      matrix(n - 1 - i)(j) = temp
    }
  }

  /**
    * 最后再来看一种方法，这种方法首先对原数组取其转置矩阵，然后把每行的数字翻转可得到结果，如下所示(其中蓝色数字表示翻转轴)：
    * *
    * 1  2  3　　　 　　 1  4  7　　　　　  7  4  1
    * *
    * 4  5  6　　-->　　 2  5  8　　 -->  　  8  5  2　　
    * *
    * 7  8  9 　　　 　　3  6  9　　　　      9  6  3
    *
    * @param matrix
    */
  def rotate3(matrix: Array[Array[Int]]): Unit = {
    val n = matrix.length
    for (i <- 0 until n-1 ; j <- i + 1 until n) {
      val temp = matrix(i)(j)
      matrix(i)(j) = matrix(j)(i)
      matrix(j)(i) = temp
    }
    for (i <- 0 until n; j <- 0 until n / 2) {
      val temp = matrix(i)(j)
      matrix(i)(j) = matrix(i)(n - 1 - j)
      matrix(i)(n - 1 - j) = temp
    }
  }

  def main(args: Array[String]): Unit = {
    val matrix = Array(
      Array(5, 1, 9, 11),
      Array(2, 4, 8, 10),
      Array(13, 3, 6, 7),
      Array(15, 14, 12, 16)
    )
    rotate3(matrix)
    matrix.foreach { x =>
      x.foreach(x => print(x + "\t"))
      println("")
    }
  }
}

package study.interview.prepare.Practice

object HashMapSolution {
  def main(args: Array[String]): Unit = {
    println(Math.pow(26, 0).toInt)
    println(titleToNumber("AB"))
  }

  /**
    * Excel表列序号
    * 给定一个Excel表格中的列名称，返回其相应的列序号。
    *
    * 例如，
    *
    * A -> 1
    * B -> 2
    * C -> 3
    * ...
    * Z -> 26
    * AA -> 27
    * AB -> 28
    * ...
    * 示例 1:
    *
    * 输入: "A"
    * 输出: 1
    * 示例 2:
    *
    * 输入: "AB"
    * 输出: 28
    * 示例 3:
    *
    * 输入: "ZY"
    * 输出: 701
    * 致谢：
    * 特别感谢 @ts 添加此问题并创建所有测试用例。
    *
    */
  def titleToNumber(s: String): Int = {

    //    var res = 0
    //    val n = s.length - 1
    //    for (i <- s.indices.reverse) {
    //      res += Math.pow(26, n - i).toInt * (s(i) - 'A' + 1)
    //    }
    //    res
    (0 /: s) {
      (n, c) =>
        println(n + "\t" + c)
        n * 26 + c - 'A' + 1
    }
  }

  /**
    * 四数相加 II
    * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
    *
    * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
    *
    * 例如:
    *
    * 输入:
    * A = [ 1, 2]
    * B = [-2,-1]
    * C = [-1, 2]
    * D = [ 0, 2]
    *
    * 输出:
    * 2
    *
    * 解释:
    * 两个元组如下:
    * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
    * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
    *
    */
  def fourSumCount(A: Array[Int], B: Array[Int], C: Array[Int], D: Array[Int]): Int = {
    val map = scala.collection.mutable.Map[Int, Int]()
    var count = 0
    for (i <- A.indices; j <- B.indices) {
      map += (A(i) + B(j)) -> (map.getOrElse(A(i) + B(j), 0) + 1)
    }
    for (i <- C.indices; j <- D.indices) {
      count += map.getOrElse(-(C(i) + D(j)), 0)
    }
    count
  }
}

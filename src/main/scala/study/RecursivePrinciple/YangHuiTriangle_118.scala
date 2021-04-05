package study.RecursivePrinciple


/**
  * 118. 杨辉三角
  * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
  *
  *
  *
  * 在杨辉三角中，每个数是它左上方和右上方的数的和。
  *
  * 示例:
  *
  * 输入: 5
  * 输出:
  * [
  * [1],
  * [1,1],
  * [1,2,1],
  * [1,3,3,1],
  * [1,4,6,4,1]
  * ]
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/pascals-triangle
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object YangHuiTriangle_118 {
  def generate(numRows: Int): List[List[Int]] = {
    import collection.mutable.ListBuffer
    val res = List.fill[ListBuffer[Int]](numRows)(ListBuffer[Int]())
    for (i <- 0 until numRows) {
      var j = 0
      for (l <- 0 to i) {
        if (j == 0)
          res(i) += 1
        else if (j == i)
          res(i) += 1
        else {
          res(i) += (res(i - 1)(j - 1) + res(i - 1)(j))
        }
        j += 1
      }
    }
    res.map(_.toList)
  }

  def main(args: Array[String]): Unit = {
    val res = generate(5)
    res.foreach {
      x =>
        println(x.mkString("\t"))

    }
  }
}

package study.leetcode_middle.Mathematics

/**
  * Created by root on 2018-9-13.
  * Excel表列序号
  * 给定一个Excel表格中的列名称，返回其相应的列序号。
  * *
  * 例如，
  * *
  * A -> 1
  * B -> 2
  * C -> 3
  * ...
  * Z -> 26
  * AA -> 27
  * AB -> 28
  * ...
  * 示例 1:
  * *
  * 输入: "A"
  * 输出: 1
  * 示例 2:
  * *
  * 输入: "AB"
  * 输出: 28
  * 示例 3:
  * *
  * 输入: "ZY"
  * 输出: 701
  */
object TitleToNumber {
  def titleToNumber(s: String): Int = {
    var count = 0
    val str = s.reverse
    for (i <- s.indices) {
      val num = str(i) - 'A' + 1
      count += Math.pow(26, i).toInt * num
    }
    count
  }

  def main(args: Array[String]): Unit = {
    val r = titleToNumber("AB")
  }
}

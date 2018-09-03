package study.leetcode_primary.Others

import scala.collection.mutable.ListBuffer

/**
  * Created by root on 2018-8-7.
  *
  * 帕斯卡三角形
  * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
  * *
  *
  *
  * 在杨辉三角中，每个数是它左上方和右上方的数的和。
  * *
  * 示例:
  * *
  * 输入: 5
  * 输出:
  * [
  * [1],
  * [1,1],
  * [1,2,1],
  * [1,3,3,1],
  * [1,4,6,4,1]
  * ]
  */
object PasikaTriangle {

  def generate(numRows: Int): List[List[Int]] = {
    val list = new ListBuffer[List[Int]]()
    if (numRows >= 1) {
      val data = List[Int](1)
      list.append(data)
    }
    if (numRows >= 2) {
      val data = List[Int](1, 1)
      list.append(data)
    }
    if (numRows >= 3) {
      for (i <- 3 to numRows) {
        val data = ListBuffer[Int]()
        val prev = list(i - 2)
        data += 1
        for (j <- 2 until i) {
          data += (prev(j - 2) + prev(j - 1))
        }
        data += 1
        list += data.toList
      }
    }
    list.toList
  }
}

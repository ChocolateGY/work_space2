package study.interview.byteDance.ArrayAndSort

/**
  * 合并区间
  * 给出一个区间的集合，请合并所有重叠的区间。
  *
  * 示例 1:
  *
  * 输入: [[1,3],[2,6],[8,10],[15,18]]
  * 输出: [[1,6],[8,10],[15,18]]
  * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
  * 示例 2:
  *
  * 输入: [[1,4],[4,5]]
  * 输出: [[1,5]]
  * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
  */
object Merge {
  def main(args: Array[String]): Unit = {
    val intervals1 = Array(
      Array(1, 3),
      Array(2, 6),
      Array(8, 10),
      Array(15, 18)
    )
    val intervals2 = Array(
      Array(1, 4),
      Array(4, 5)
    )
    val res = merge(intervals2)
    res.foreach(x => println(x.mkString(",")))
  }

  def merge(intervals: Array[Array[Int]]): Array[Array[Int]] = {
    val res = scala.collection.mutable.ArrayBuffer[Array[Int]]()
    if (intervals.nonEmpty) {
      val intervalsSort=  intervals.sortBy(_.head)
      var num = Int.MinValue
      val tempArr = scala.collection.mutable.ArrayBuffer[Int]()
      for (arr <- intervalsSort) {
        if (num < arr(0)) {
          if (tempArr.size == 1) {
            tempArr += num
            res += tempArr.toArray
            tempArr.clear()
          }
          tempArr += arr(0)
          num = arr(1)
        } else {
          num = num.max(arr(1))
        }
      }
      tempArr += num
      res += tempArr.toArray
    }
    res.toArray
  }
}

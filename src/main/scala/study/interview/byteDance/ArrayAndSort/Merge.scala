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
    //结果集
    val res = scala.collection.mutable.ArrayBuffer[Array[Int]]()
    //判断输入是否为空
    if (intervals.nonEmpty) {
      //讲输入集合按每个数组的第一个值排序
      val intervalsSort = intervals.sortBy(_.head)
      //临界值
      var num = Int.MinValue
      //临时数组
      val tempArr = scala.collection.mutable.ArrayBuffer[Int]()
      //遍历排序后的集合
      for (arr <- intervalsSort) {
        //判断 临界值是否小于该数组的第一个值
        if (num < arr(0)) {
          //如果小于，说明需要添加值

          //如果临时数组已经有值了，那就把临界值num放入并把数组放到结果集合中。
          if (tempArr.size == 1) {
            tempArr += num
            res += tempArr.toArray
            tempArr.clear()
          }
          //添加该数组的第一个值
          tempArr += arr(0)
          //把临界值改为该数组的第二个值
          num = arr(1)
        } else {
          //如果不小于，则比较临界值和数组第二个值，赋值大的。
          num = num.max(arr(1))
        }
      }

      //最后收尾，把最后一个临界值放入数组，并加到结果集合
      tempArr += num
      res += tempArr.toArray
    }
    res.toArray
  }
}

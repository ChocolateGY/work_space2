package study.leetcode_middle.SortAndSearch

/**
  * Created by root on 2018-9-3.
  * 合并区间
  * 给出一个区间的集合，请合并所有重叠的区间。
  * *
  * 示例 1:
  * *
  * 输入: [[1,3],[2,6],[8,10],[15,18]]
  * 输出: [[1,6],[8,10],[15,18]]
  * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
  * 示例 2:
  * *
  * 输入: [[1,4],[4,5]]
  * 输出: [[1,5]]
  * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
  */
object Merge {
  /**
    * 需要先给集合按照start排序
    * 如果 [[1,4],[0,4]] 则不通过， [[0,4]]
    *
    * @param intervals
    * @return
    */
  def merge(intervals: List[Interval]): List[Interval] = {
    val list = collection.mutable.ArrayBuffer[Interval]()
    if (intervals.nonEmpty) {
      val sortList = intervals.sortBy(_.start)
      var i = 0
      var start = sortList.head.start
      var end = sortList.head.end
      while (i < sortList.length) {
        if (i + 1 < sortList.length) {
          if (end > sortList(i + 1).end) {
            i += 1
          } else if (end >= sortList(i + 1).start) {
            end = sortList(i + 1).end
            i += 1
          } else {
            list += new Interval(start, end)
            start = sortList(i + 1).start
            end = sortList(i + 1).end
            i += 1
          }
        } else {
          list += new Interval(start, end)
          i += 1
        }

      }
    }
    list.toList
  }

  /**
    * 网上改良方法
    *
    * @param intervals
    * @return
    */
  def merge2(intervals: List[Interval]): List[Interval] = {
    val list = collection.mutable.ArrayBuffer[Interval]()
    if (intervals.nonEmpty) {
      val sortIntervals = intervals.sortBy(_.start)
      var pre: Interval = null
      for (item <- sortIntervals) {
        if (pre == null || pre.end < item.start) {
          list += item
          pre = item
        } else if (pre.end < item.end) {
          pre.end = item.end
        }
      }
    }
    list.toList
  }

  def main(args: Array[String]): Unit = {
    val list = List(
      new Interval(1, 3),
      new Interval(2, 6),
      new Interval(8, 10),
      new Interval(15, 18)
    )
    merge(list).foreach {
      x =>
        println(x.start + "," + x.end)
    }
  }
}

//Definition for an interval.

class Interval(var _start: Int = 0, var _end: Int = 0) {
  var start: Int = _start
  var end: Int = _end
}

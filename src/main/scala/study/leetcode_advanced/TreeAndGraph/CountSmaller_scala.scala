package study.leetcode_advanced.TreeAndGraph

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object CountSmaller_scala {
  def countSmaller(nums: Array[Int]): List[Int] = {
    val sort = ArrayBuffer[Int]()
    val res = new Array[Int](nums.length)
    for (i <- nums.indices.reverse) {
      var left = 0
      var right = sort.length
      while (left < right) {
        var mid = left + (right - left) / 2
        if (sort(mid) >= nums(i)) {
          right = mid
        } else
          left = mid + 1
      }
      res(i) = left
      sort.insert(left, nums(i))
    }
    res.toList
  }

  def main(args: Array[String]): Unit = {
    val nums = Array(5, 2, 6, 1)

    countSmaller(nums).foreach(println)
  }
}

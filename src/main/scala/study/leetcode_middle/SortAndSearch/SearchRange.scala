package study.leetcode_middle.SortAndSearch

/**
  * Created by root on 2018-9-3.
  * 在排序数组中查找元素的第一个和最后一个位置
  * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
  * *
  * 你的算法时间复杂度必须是 O(log n) 级别。
  * *
  * 如果数组中不存在目标值，返回 [-1, -1]。
  * *
  * 示例 1:
  * *
  * 输入: nums = [5,7,7,8,8,10], target = 8
  * 输出: [3,4]
  * 示例 2:
  * *
  * 输入: nums = [5,7,7,8,8,10], target = 6
  * 输出: [-1,-1]
  *
  *
  */
object SearchRange {
  /**
    * 采用二分法，当前数大于目标数，则查找左半部分，当前数小于目标数，则查找右半部分，当等于目标数时，分别从左右两边进行查找，直到不等于目标数
    * *
    * 返回两个数的数组
    *
    * @param nums
    * @param target
    * @return
    */
  def searchRange(nums: Array[Int], target: Int): Array[Int] = {
    val arr = Array(-1, -1)
    if (nums.nonEmpty) {
      var low = 0
      var high = nums.length-1
      while (low <= high) {
        val middle = (low + high) >>> 1
        if (nums(middle) == target) {
          var i, j = middle
          while (i >= 0 && nums(i) == target) {
            i -= 1
          }
          while (j < nums.length && nums(j) == target) {
            j += 1
          }
          arr(0) = i + 1
          arr(1) = j - 1
          return arr
        } else if (nums(middle) > target) {
          high = middle - 1
        } else {
          low = middle + 1
        }
      }
    }

    arr
  }

  /**
    * 通过
    * 但时间不符合
    *
    * @param nums
    * @param target
    * @return
    */
  def searchRange2(nums: Array[Int], target: Int): Array[Int] = {
    var start, end = -1
    for (i <- nums.indices) {
      if (nums(i) == target) {
        if (start == -1) {
          start = i
        }
        end = i
      }
    }
    Array(start, end)
  }

  def main(args: Array[String]): Unit = {
    searchRange(Array(5, 7, 7, 8, 8, 10), 8).foreach(println)
  }
}

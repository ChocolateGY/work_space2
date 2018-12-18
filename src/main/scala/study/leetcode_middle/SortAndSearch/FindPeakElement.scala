package study.leetcode_middle.SortAndSearch

/**
  * Created by root on 2018-8-31.
  *
  * 寻找峰值
  *
  * 峰值元素是指其值大于左右相邻值的元素。
  * *
  * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
  * *
  * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
  * *
  * 你可以假设 nums[-1] = nums[n] = -∞。
  * *
  * 示例 1:
  * *
  * 输入: nums = [1,2,3,1]
  * 输出: 2
  * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
  * 示例 2:
  * *
  * 输入: nums = [1,2,1,3,5,6,4]
  * 输出: 1 或 5
  * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
  * 或者返回索引 5， 其峰值元素为 6。
  * 说明:
  * *
  * 你的解法应该是 O(logN) 时间复杂度的。
  */
object FindPeakElement {
  def findPeakElement(nums: Array[Int]): Int = {
    var result = -1
      var low = 0
      var high = nums.length - 1
      while (low <= high && result == -1) {
        val middle = (low + high) >>> 1
        if (middle - 1 >= 0 && nums(middle - 1) > nums(middle)) {
          high = middle - 1
        } else if (middle + 1 < nums.length && nums(middle) < nums(middle + 1)) {
          low = middle + 1
        } else
          result = middle
      }
    result
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(1, 2, 3, 1)
    val arr2 = Array(3, 4, 3, 2, 1)
    val a = findPeakElement(arr2)
    println(a)
  }
}

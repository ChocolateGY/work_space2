package study.interviewPrepare.Practice

object SortAndSearch {

  /**
    * 寻找峰值
    * 峰值元素是指其值大于左右相邻值的元素。
    *
    * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
    *
    * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
    *
    * 你可以假设 nums[-1] = nums[n] = -∞。
    *
    * 示例 1:
    *
    * 输入: nums = [1,2,3,1]
    * 输出: 2
    * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
    * 示例 2:
    *
    * 输入: nums = [1,2,1,3,5,6,4]
    * 输出: 1 或 5
    * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
    * 或者返回索引 5， 其峰值元素为 6。
    * 说明:
    *
    * 你的解法应该是 O(logN) 时间复杂度的。
    *
    */
  def findPeakElement(nums: Array[Int]): Int = {
    if (nums.size == 1)
      return nums.head
    var left = 0
    var right = nums.length - 1
    while (left <= right) {
      val mid = left + (right - left) / 2
      if (nums(mid) > nums(mid + 1)) {
        if (nums(mid) > nums(mid - 1))
          return nums(mid)
        else
          right = mid - 1

      } else {
        left = mid + 1
      }
    }
    left
  }

  /**
    * 寻找重复数
    * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
    *
    * 示例 1:
    *
    * 输入: [1,3,4,2,2]
    * 输出: 2
    * 示例 2:
    *
    * 输入: [3,1,3,4,2]
    * 输出: 3
    * 说明：
    *
    * 不能更改原数组（假设数组是只读的）。
    * 只能使用额外的 O(1) 的空间。
    * 时间复杂度小于 O(n2) 。
    * 数组中只有一个重复的数字，但它可能不止重复出现一次。
    *
    */
  def findDuplicate(nums: Array[Int]): Int = {
    var left = 0
    var right = nums.length - 1
    while (left <= right) {
      val mid = left + (right - left) / 2
      val count = nums.count(_ < mid)
      if (count < mid)
        left = mid + 1
      else
        right = mid - 1
    }
    left - 1
  }

  def findDuplicate2(nums: Array[Int]): Int = {
    var slow = nums(0)
    var fast = nums(0)
    while (slow != fast) {
      slow = nums(slow)
      fast = nums(nums(fast))
    }
    var cur = 0
    while (cur != fast) {
      cur = nums(cur)
      fast = nums(fast)
    }
    cur
  }

  /**
    * 计算右侧小于当前元素的个数
    * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
    *
    * 示例:
    *
    * 输入: [5,2,6,1]
    * 输出: [2,1,1,0]
    * 解释:
    * 5 的右侧有 2 个更小的元素 (2 和 1).
    * 2 的右侧仅有 1 个更小的元素 (1).
    * 6 的右侧有 1 个更小的元素 (1).
    * 1 的右侧有 0 个更小的元素.
    *
    * 困难
    * https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/solution/gui-bing-pai-xu-suo-yin-shu-zu-python-dai-ma-java-/
    * 归并排序+ 索引数组
    */
  def countSmaller(nums: Array[Int]): List[Int] = {
    Nil
  }
}

package study.interviewPrepare.BinarySearch

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
  */
object FindDuplicate {
  //快慢链表，寻找入环点 这个平台超时
  //https://leetcode-cn.com/problems/find-the-duplicate-number/solution/kuai-man-zhi-zhen-de-jie-shi-cong-damien_undoxie-d/
  def findDuplicate(nums: Array[Int]): Int = {
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
    fast
  }

  //二分法，一次通过。good
  def findDuplicate2(nums: Array[Int]): Int = {
    var left = 1
    var right = nums.length - 1
    while (left <= right) {
      val mid = left + (right - left) / 2
      var count = nums.count(_ < mid)
      if (count < mid) {
        left = mid + 1
      }else
        right = mid - 1
    }
    left - 1
  }
}

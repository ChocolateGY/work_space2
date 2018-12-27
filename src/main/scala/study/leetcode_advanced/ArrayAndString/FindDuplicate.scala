package study.leetcode_advanced.ArrayAndString

/**
  * 寻找重复数
  * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
  * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
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
  /**
    * 双指针解法，类似于有环链表中找出环的入口
    *
    * 有环找入口：慢指针走过的路程是：
    *
    * S_slow = |OA| +|AB| = x + y.
    * 快指针走过的路程是：
    * S_fast =  |OA| + |AB| + |BA| + |AB| = x + y + z + y.
    * 又因为快指针的速度是慢指针的两倍，所以在相同时间内快指针走过的路程是慢指针的两倍，所以
    * S_fast = 2 * S_slow
    * 即2(x + y) = x + y + z + y.
    * 求得z = x.
    * 所以说明
    * |BA| = |OA|.
    * 所以在两个指针相遇后，将慢指针移到O点起始位置，即链表头指针位置，快指针仍然在B点。
    * 然后它们一起向前移动，每次移动一个位置，由于|BA| = |OA|, 所以他们最终肯定会在A点相遇，A点这个相遇点就是环的入口点。
    */
  def findDuplicate(nums: Array[Int]): Int = {
    var slow = nums(0)
    var fast = nums(nums(0))
    while (slow != fast) {
      slow = nums(slow)
      fast = nums(nums(fast))
    }
    fast = 0
    while (slow != fast) {
      slow = nums(slow)
      fast = nums(fast)
    }
    slow
  }
  def findDuplicate2(nums: Array[Int]): Int = {
    var res = -1
    if (nums == null || nums.size < 1)
      return res

    for (i <- nums.indices) {
      for (j <- i+1 until nums.length) {
        if (nums(i) == nums(j))
          res = nums(i)
      }
    }
    res
  }
}

package study.leetcode_advanced.ArrayAndString

import scala.collection.mutable


/**
  * Sliding Window Maximum
  * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
  * 你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
  *
  * 返回滑动窗口最大值。
  *
  * 示例:
  *
  * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
  * 输出: [3,3,5,5,6,7]
  * 解释:
  *
  * 滑动窗口的位置                最大值
  * ---------------               -----
  * [1  3  -1] -3  5  3  6  7       3
  * 1 [3  -1  -3] 5  3  6  7       3
  * 1  3 [-1  -3  5] 3  6  7       5
  * 1  3  -1 [-3  5  3] 6  7       5
  * 1  3  -1  -3 [5  3  6] 7       6
  * 1  3  -1  -3  5 [3  6  7]      7
  * 注意：
  *
  * 你可以假设 k 总是有效的，1 ≤ k ≤ 输入数组的大小，且输入数组不为空。
  *
  * 进阶：
  *
  * 你能在线性时间复杂度内解决此题吗？
  */
object MaxSlidingWindow {
  /**
    * 自己实现
    * O(n*n)
    * 超出时间限制
    *
    * @param nums
    * @param k
    * @return
    */
  def maxSlidingWindow(nums: Array[Int], k: Int): Array[Int] = {
    if (nums == null || nums.isEmpty)
      return Array[Int]()
    var max = 0
    var low = 0
    var high = k - 1
    val arr = new Array[Int](nums.length - k + 1)
    while (high < nums.length) {
      max = nums(low)
      for (i <- low to high) {
        if (nums(i) > max)
          max = nums(i)
      }
      arr(low) = max
      low += 1
      high += 1
    }
    arr
  }

  /**
    * 优先队列
    *
    * @param nums
    * @param k
    * @return
    */
  def maxSlidingWindow2(nums: Array[Int], k: Int): Array[Int] = {
    //    if (nums == null || nums.isEmpty)
    //      Array[Int]()
    //    else {
    //      val pq = mutable.PriorityQueue()
    //
    //    }
    nums
  }

  def main(args: Array[String]): Unit = {
    //    val nums = Array(1, 3, -1, -3, 5, 3, 6, 7)
    //    val k = 3
    val nums = Array(1, -1)
    val k = 1
    maxSlidingWindow(nums, k).foreach(x => print(x + " "))

  }
}

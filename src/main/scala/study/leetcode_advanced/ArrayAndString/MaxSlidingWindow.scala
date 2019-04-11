package study.leetcode_advanced.ArrayAndString

import java.util


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
    * 双向队列 double ended queue
    *
    * scala语言环境 超出时间限制
    *
    * @param nums
    * @param k
    * @return
    */
  def maxSlidingWindow2(nums: Array[Int], k: Int): Array[Int] = {
    if (nums == null || nums.isEmpty)
      Array[Int]()
    else {
      val deque: util.LinkedList[Int] = new util.LinkedList[Int]()
      val res = new Array[Int](nums.length - k + 1)
      for (i <- nums.indices) {
        //当队列不为空时，如果发现队列的头是最左边数的下标，就删除
        if (!deque.isEmpty && deque.peekFirst() == i - k) deque.poll()
        //当队列不为空时，把队尾比新数小的删除。 保证队列是降序的
        while (!deque.isEmpty && nums(deque.peekLast()) < nums(i)) deque.removeLast()
        //加入新数
        deque.offerLast(i)
        // 队列头部就是窗口内第一大的
        if ((i + 1) >= k) res(i + 1 - k) = nums(deque.peek())
      }
      res
    }

  }

  /**
    * 速度最快的方法
    * scala语言环境 超出时间限制
    *
    * @param nums
    * @param k
    * @return
    */

  def maxSlidingWindow3(nums: Array[Int], k: Int) = {
    if (nums == null || nums.isEmpty)
      Array[Int]()
    else {
      val res = new Array[Int](nums.length - k + 1)
      var maxI = -1
      for (i <- res.indices; j = k - 1 + i) {
        if (maxI >= i && maxI <= j) {
          if (nums(j) > nums(maxI))
            maxI = j
        } else
          maxI = getMaxIndex(nums, i, j)

        res(i) = nums(maxI)
      }
      res
    }

  }

  def getMaxIndex(nums: Array[Int], i: Int, j: Int): Int = {
    var maxIndex = i
    for (m <- 0 until (j - i); k <- (i + 1) until (j + 1)) {
      if (nums(k) > nums(maxIndex))
        maxIndex = k
    }
    maxIndex
  }

  def main(args: Array[String]): Unit = {
    val nums = Array(1, 3, -1, -3, 5, 3, 6, 7)
    val k = 3
    //    val nums = Array(1, -1)
    //    val k = 1
    //    maxSlidingWindow(nums, k).foreach(x => print(x + " "))
    //    maxSlidingWindow2(nums, k).foreach(x => print(x + " "))
    maxSlidingWindow3(nums, k).foreach(x => print(x + " "))
    //    val q: util.Deque[Int] = new util.ArrayDeque[Int]()
    //    Range(1, 5).foreach(x => q.add(x))
    //    println(q.peek())
    //    println(q.poll())
    //    println(q.poll())

  }
}

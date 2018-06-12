package study.leetcode.Array

/**
  * Created by root on 2018-5-30.
  * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
  * *
  * 示例 1:
  * *
  * 输入: [1,2,3,4,5,6,7] 和 k = 3
  * 输出: [5,6,7,1,2,3,4]
  * 解释:
  * 向右旋转 1 步: [7,1,2,3,4,5,6]
  * 向右旋转 2 步: [6,7,1,2,3,4,5]
  * 向右旋转 3 步: [5,6,7,1,2,3,4]
  * 示例 2:
  * *
  * 输入: [-1,-100,3,99] 和 k = 2
  * 输出: [3,99,-1,-100]
  * 解释:
  * 向右旋转 1 步: [99,-1,-100,3]
  * 向右旋转 2 步: [3,99,-1,-100]
  * 说明:
  * *
  * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
  * 要求使用空间复杂度为 O(1) 的原地算法。
  */
object Array3 {
  def rotate(nums: Array[Int], k: Int): Unit = {
    for (i <- 1 to k) {
      val last = nums.last
      for (j <- (1 until nums.length).reverse) {
        nums(j) = nums(j - 1)
      }
      nums(0) = last
    }
  }

  def rotate2(nums: Array[Int], k: Int): Unit = {
    val kr = k % nums.length
    for (i <- 1 to kr) {
      val last = nums.last
      for (j <- (1 until nums.length).reverse) {
        nums(j) = nums(j - 1)
      }
      nums(0) = last
    }
  }

  def solution1(nums: Array[Int], k: Int): Unit = {
    val t = nums.clone()

    for (i <- nums.indices) {
      nums((i + k) % nums.length) = t(i)
    }
  }

  /**
    * 原版
    * 1 2 3 4 5 6 7
    * 1 2 3 1 5 6 7
    * 1 2 3 1 5 6 4
    * 1 2 7 1 5 6 4
    * 1 2 7 1 5 3 4
    * 1 6 7 1 5 3 4
    * 1 6 7 1 2 3 4
    * 5 6 7 1 2 3 4
    */
  def solution2(nums: Array[Int], k: Int): Unit = {
    if (nums.nonEmpty && k % nums.size != 0) {
      var i = 0
      var start = 0
      var cur = nums(0)
      for (cnt <- nums.indices) {
        i = (i + k) % nums.length
        val t = nums(i)
        nums(i) = cur
        if (i == start) {
          start += 1
          i += 1
          cur = nums(i)
        } else
          cur = t


      }
    }
  }

  /**
    * val k = 2
    * val nums = Array(1, 2, 3, 4, 5, 6)
    *
    */
  def solution2_(nums: Array[Int], k: Int): Unit = {
    if (nums.nonEmpty && k % nums.size != 0) {
      var cur = 0
      var index = 0
      var start = 0
      var beforeNum = nums(0)
      for (cnt <- nums.indices) {
        index = (index + k) % nums.length
        cur = nums(index)
        nums(index) = beforeNum
        if (index == start) {
          index += 1
          start += 1
          beforeNum = nums(index)
        } else
          beforeNum = cur
      }
    }
  }

  /**
    * 这道题其实还有种类似翻转字符的方法，思路是先把前n-k个数字翻转一下，再把后k个数字翻转一下，最后再把整个数组翻转一下：
    * *
    * 1 2 3 4 5 6 7
    * 4 3 2 1 5 6 7
    * 4 3 2 1 7 6 5
    * 5 6 7 1 2 3 4
    *
    * @param nums
    * @param k
    */

  def solution3(nums: Array[Int], k: Int): Unit = {
    val n = nums.length
    if (nums.nonEmpty && k % n != 0) {
      val index = nums.length - k % n - 1
      for (i <- 0 to index / 2) {
        val temp = nums(i)
        nums(i) = nums(index - i)
        nums(index - i) = temp
      }
      for (i <- 1 to (n - index - 1) / 2) {
        val temp = nums(index + i)
        nums(index + i) = nums(n - i)
        nums(n - i) = temp
      }
      for (i <- 0 to (n - 1) / 2) {
        val temp = nums(i)
        nums(i) = nums(n - 1 - i)
        nums(n - 1 - i) = temp
      }
    }
  }

  /**
    * 根据c++中的 由于旋转数组的操作也可以看做从数组的末尾取k个数组放入数组的开头，所以我们用STL的push_back和erase可以很容易的实现这些操作。
    * 理解为是用了pop原理
    *
    * @param nums
    * @param k
    */
  def solution4(nums: Array[Int], k: Int): Unit = {

  }

  /**
    * 下面这种方法其实还蛮独特的，通过不停的交换某两个数字的位置来实现旋转，根据网上这个帖子的思路改写而来，数组改变过程如下：
    * *
    * 1 2 3 4 5 6 7
    * 5 2 3 4 1 6 7
    * 5 6 3 4 1 2 7
    * 5 6 7 4 1 2 3
    * 5 6 7 1 4 2 3
    * 5 6 7 1 2 4 3
    * 5 6 7 1 2 3 4
    *
    * 原理不懂
    *
    * 帖子：
    * Every swap will put one number into its correct position, so the running time is O(n)
    * For example,
    * at first, nums[] is [1,2,3,4,5,6,7], n is 7, k is 3
    * after first outer loop, nums[] is [4,1,2,3], n is 4, k is 3
    * after second outer loop, nums[] is [4], n is 1, k is 0
    * loop ends.
    *
    * void rotate(int nums[], int n, int k) {
    *   for (; k %= n; n -= k)
    *     for (int i = 0; i < k; i++)
    *       swap(*nums++, nums[n - k]);
    * }
    *
    * @param nums
    * @param k
    */
  def solution5(nums: Array[Int], k: Int): Unit = {
    if (nums.nonEmpty) {
      var n = nums.length
      var start = 0
      var kr = k % n //3
      while (n != 0 && (kr != 0)) {
        for (i <- 0 until kr) {
          val temp = nums(i + start)
          nums(i + start) = nums(n - kr + i + start)
          nums(n - kr + i + start) = temp
        }
        n -= kr //4
        start += kr //4
        kr %= n // 3
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val k = 3
    val nums = Array(1, 2, 3, 4, 5, 6, 7)

    solution5(nums, k)
    nums.foreach(print)
  }
}

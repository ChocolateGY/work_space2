package study.leetcode.Design

import scala.util.Random

/**
  * 打乱一个没有重复元素的数组。
  * *
  * 示例:
  *
  * // 以数字集合 1, 2 和 3 初始化数组。
  * int[] nums = {1,2,3};
  * Solution solution = new Solution(nums);
  *
  * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
  *solution.shuffle();
  *
  * // 重设数组到它的初始状态[1,2,3]。
  *solution.reset();
  *
  * // 随机返回数组[1,2,3]打乱后的结果。
  *solution.shuffle();
  *
  * Created by root on 2018-6-29.
  */
class ShuffleAnArray(nums: Array[Int]) {
  /** Resets the array to its original configuration and return it. */
  def reset(): Array[Int] = {
    nums
  }

  /** Returns a random shuffling of the array. */
  def shuffle(): Array[Int] = {
    val random = new Random()
    for (i <- nums.indices) {
      swap(nums, i, random.nextInt(i + 1))
    }
    nums
  }

  def swap(arr: Array[Int], i: Int, j: Int) = {
    val temp = arr(i)
    arr(i) = arr(j)
    arr(j) = temp
  }
}

/**
  * Your Solution object will be instantiated and called as such:
  * var obj = new Solution(nums)
  * var param_1 = obj.reset()
  * var param_2 = obj.shuffle()
  */
object ShuffleAnArray {
  val nums = Array(1, 2, 3)
  val obj = new ShuffleAnArray(nums)
  val param_1 = obj.reset()
  val param_2 = obj.shuffle()
}
package study.leetcode.Array

/**
  *
  * 两数之和
  * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
  * *
  * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
  * *
  * 示例:
  * *
  * 给定 nums = [2, 7, 11, 15], target = 9
  * *
  * 因为 nums[0] + nums[1] = 2 + 7 = 9
  * 所以返回 [0, 1]
  *
  * Created by root on 2018-6-1.
  */
object Array9 {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val arr = new Array[Int](2)
    for (i <- 0 until nums.length - 1; j <- i + 1 until nums.length if nums(i) + nums(j) == target) {
      arr(0) = i
      arr(1) = j
    }
    arr
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(2, 7, 11, 15)
    var target = 9
    twoSum(arr, target).foreach(println)
  }
}

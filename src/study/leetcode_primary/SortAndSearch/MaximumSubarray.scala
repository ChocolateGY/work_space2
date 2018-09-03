package study.leetcode_primary.SortAndSearch

/**
  *
  * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
  * *
  * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
  * Example:
  * *
  * Input: [-2,1,-3,4,-1,2,1,-5,4],
  * Output: 6
  * Explanation: [4,-1,2,1] has the largest sum = 6.
  * Follow up:
  * *
  * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
  * *
  * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
  * Created by root on 2018-6-29.
  */
object MaximumSubarray {

  /**
    * 超出时间限制
    *
    * @param nums
    * @return
    */
  def maxSubArray(nums: Array[Int]): Int = {
    var max = nums.sum
    for (num <- 1 until nums.length) {
      for (i <- 0 to nums.length - num) {
        val seq = i until i + num
        var sum = 0
        seq.foreach(x => sum += nums(x))
        max = math.max(max, sum)
      }
    }
    max
  }

  def maxSubArray2(nums: Array[Int]): Int = {
    var sum = Int.MinValue
    var curSum = nums(0)
    for (i <- 1 until nums.size ) {
      curSum = math.max(curSum + nums(i), nums(i))
      sum = math.max(sum, curSum)
    }
    sum
  }

  def main(args: Array[String]): Unit = {
    //        val arr = Array(-2, 1, -3, 4, -1, 2, 1, -5, 4)
    val arr = Array(-2, 1, -3)
            print(maxSubArray2(arr))

    //reduce() 在seq只有1个值的时候不会执行所给的操作函数，只会返回这个值。详情查看源码
    val seq = 1 until 2
    val sum = seq.reduce((i, j) => arr(i) + arr(j))
    //    var sum2 = 0
    //    seq.foreach(x => sum2 += arr(x))
    //    val sum = seq.reduce(arr(_)+arr(_))
    for (i <- seq) println(i)
    println(sum)
    //    print(sum2)
    seq.foreach(println)
    println(seq)
  }
}

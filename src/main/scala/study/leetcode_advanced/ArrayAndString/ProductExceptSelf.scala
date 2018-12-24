package study.leetcode_advanced.ArrayAndString


/**
  * Product of Array Except Self
  * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
  *
  * 示例:
  *
  * 输入: [1,2,3,4]
  * 输出: [24,12,8,6]
  * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
  *
  * 进阶：
  * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
  */
object ProductExceptSelf {
  /**
    * 仅实现功能，没有完成要求
    *
    * @param nums
    * @return
    */
  def productExceptSelf(nums: Array[Int]): Array[Int] = {
    val product = nums.product
    val arr = nums.map {
      x =>
        product / x
    }
    arr
  }

  /**
    * 这道题给定我们一个数组，让我们返回一个新数组，对于每一个位置上的数是其他位置上数的乘积，
    * 并且限定了时间复杂度O(n)，并且不让我们用除法。如果让用除法的话，那这道题就应该属于Easy，
    * 因为可以先遍历一遍数组求出所有数字之积，然后除以对应位置的上的数字。但是这道题禁止我们使用除法，
    * 那么我们只能另辟蹊径。我们想，对于某一个数字，如果我们知道其前面所有数字的乘积，
    * 同时也知道后面所有的数乘积，那么二者相乘就是我们要的结果，所以我们只要分别创建出这两个数组即可，
    * 分别从数组的两个方向遍历就可以分别创建出乘积累积数组。参见代码如下：
    *
    * @param nums
    * @return
    */
  def productExceptSelf2(nums: Array[Int]): Array[Int] = {
    var index = 1
    val preArr = new Array[Int](nums.length)
    preArr(0) = 1
    while (index < nums.length) {
      preArr(index) = nums(index - 1) * preArr(index - 1)
      index += 1
    }
    index -= 2
    val postArr = new Array[Int](nums.length)
    postArr(nums.length - 1) = 1
    while (index >= 0) {
      postArr(index) = postArr(index + 1) * nums(index + 1)
      index -= 1
    }
    for (i <- nums.indices) {
      nums(i) = preArr(i) * postArr(i)
    }
    nums
  }

  def productExceptSelf3(nums: Array[Int]): Array[Int] = {
    val n = nums.length
    val res = new Array[Int](n)
    var right = 1
    res(0) = 1
    for (i <- 1 until n) {
      res(i) = res(i - 1) * nums(i - 1)
    }
    for (i <- (0 until n).reverse) {
      res(i) *= right
      right *= nums(i)
    }
    res
  }

  def main(args: Array[String]): Unit = {
    val nums = Array(1, 2, 3, 4)
    productExceptSelf3(nums).foreach(println)
  }
}

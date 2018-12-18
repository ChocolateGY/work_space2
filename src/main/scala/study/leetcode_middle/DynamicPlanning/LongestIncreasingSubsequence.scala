package study.leetcode_middle.DynamicPlanning

/**
  * Created by root on 2018-9-10.
  * Longest Increasing Subsequence
  * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
  * *
  * 示例:
  * *
  * 输入: [10,9,2,5,3,7,101,18]
  * 输出: 4
  * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
  * 说明:
  * *
  * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
  * 你算法的时间复杂度应该为 O(n2) 。
  * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
  *
  *
  *
  */
object LongestIncreasingSubsequence {
  /**
    * 首先来看一种动态规划Dynamic Programming的解法，这种解法的时间复杂度为O(n2)，
    * 类似brute force的解法，我们维护一个一维dp数组，其中dp[i]表示以nums[i]为结尾的最长递增子串的长度，
    * 对于每一个nums[i]，我们从第一个数再搜索到i，如果发现某个数小于nums[i]，我们更新dp[i]，
    * 更新方法为dp[i] = max(dp[i], dp[j] + 1)，即比较当前dp[i]的值和那个小于num[i]的数的dp值加1的大小，
    * 我们就这样不断的更新dp数组，到最后dp数组中最大的值就是我们要返回的LIS的长度，参见代码如下：
    *
    * @param nums
    * @return
    */
  def lengthOfLIS(nums: Array[Int]): Int = {
    var count = 0
    //    val arr = collection.mutable.ArrayBuffer[Int]()
    val arr = new Array[Int](nums.length).map(x => 1)
    for (i <- nums.indices) {

      for (j <- 0 until i) {
        if (nums(i) > nums(j))
          arr(i) = arr(i).max(arr(j) + 1)
      }
      count = count.max(arr(i))
    }
    count
  }

  /**
    * 下面几个方法是求最大子序列的方法。
    * 这个是 o（N2）
    * @param arr
    * @return
    */
  def getdp1(arr: Array[Int]): Array[Int] = {
    val dp = new Array[Int](arr.length)
    for (i <- arr.indices) {
      dp(i) = 1
      for (j <- 0 until i) {
        if (arr(i) > arr(j))
          dp(i) = dp(i).max(dp(j) + 1)
      }
    }
    dp
  }

  /**
    *
    * 这个是o（nlogn)
    *
    * @param arr
    * @return
    */
  def getdp2(arr: Array[Int]): Array[Int] = {
    val dp = new Array[Int](arr.length)
    val ends = new Array[Int](arr.length)
    ends(0) = arr(0)
    dp(0) = 1
    var right = 0
    var l = 0
    var r = 0
    var m = 0
    for (i <- 1 until arr.length) {
      l = 0
      r = right
      while (l <= r) {
        m = (l + r) >>> 1
        if (arr(i) > ends(m))
          l = m + 1
        else
          r = m - 1
      }
      right = right.max(l)
      ends(l) = arr(i)
      dp(i) = l + 1
    }
    dp
  }

  def generateLIS(arr: Array[Int], dp: Array[Int]): Array[Int] = {
    var len = 0
    var index = 0
    for (i <- dp.indices) {
      if (dp(i) > len) {
        len = dp(i)
        index = i
      }
    }
    val lis = new Array[Int](len)
    len -= 1
    lis(len) = arr(index)
    var i = index
    while (i >= 0) {
      if (arr(i) < arr(index) && dp(i) == dp(index) - 1) {
        len -= 1
        lis(len) = arr(i)
        index = i
      }
      i -= 1
    }
    lis
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(10, 9, 2, 5, 3, 4)
    //    val t = lengthOfLIS(arr)
    //    println(t)
    val temp = getdp2(arr)
    val result = generateLIS(arr, temp)
    result.foreach(x => print(x + " "))
  }
}

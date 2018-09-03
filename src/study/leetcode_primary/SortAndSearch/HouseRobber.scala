package study.leetcode_primary.SortAndSearch

/**
  *
  * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
  * *
  * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
  * *
  * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
  * *
  * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
  * *
  * Example 1:
  * *
  * Input: [1,2,3,1]
  * Output: 4
  * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
  * Total amount you can rob = 1 + 3 = 4.
  * Example 2:
  * *
  * Input: [2,7,9,3,1]
  * Output: 12
  * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
  * Total amount you can rob = 2 + 9 + 1 = 12.
  *
  * Created by root on 2018-6-29.
  */
object HouseRobber {
  /**
    * 考查动态规划，基本思路是当前节点处最大值curMax = Math.max(curMax, curPreMax + cur)。
    * @param nums
    * @return
    */
  def rob(nums: Array[Int]): Int = {
    var curMax = 0
    var curPreMax = 0
    for (cur <- nums) {
      val temp = curMax
      curMax = math.max(curMax, curPreMax + cur)
      curPreMax = temp
    }
    curMax
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(2,7,9,3,1)
    println(rob(arr))
  }
}

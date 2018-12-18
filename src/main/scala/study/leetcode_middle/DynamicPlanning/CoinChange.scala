package study.leetcode_middle.DynamicPlanning

/**
  * Created by root on 2018-9-7.
  * 零钱兑换
  * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
  * *
  * 示例 1:
  * *
  * 输入: coins = [1, 2, 5], amount = 11
  * 输出: 3
  * 解释: 11 = 5 + 5 + 1
  * 示例 2:
  * *
  * 输入: coins = [2], amount = 3
  * 输出: -1
  * 说明:
  * 你可以认为每种硬币的数量是无限的。
  */
object CoinChange {

  /**
    * 报错
    *
    * @param coins
    * @param amount
    * @return
    */
  def coinChange(coins: Array[Int], amount: Int): Int = {
    if (amount == 0) return 0
    if (amount < 0) return -1
    val nums = new Array[Int](amount + 1)
    for (i <- coins.indices) {
      if (coins(i) <= amount)
        nums(coins(i)) = 1
    }
    for (i <- 1 until amount) {
      if (nums(i) != 0) {
        for (j <- coins.indices) {
          if (i + coins(j) <= amount && (nums(i + coins(j)) == 0 || nums(i) + 1 < nums(i + coins(j))))
            nums(i + coins(j)) = nums(i) + 1
        }
      }
    }
    if (nums(amount) == 0) return -1
    nums(amount)
  }

  /**
    * 通过
    * @param coins
    * @param amount
    * @return
    */
  def coinChange2(coins: Array[Int], amount: Int): Int = {
    if (amount == 0) {
      return 0
    }
    val dp = new Array[Int](amount + 1).map(x => -1)
    dp(0) = 0
    for (i <- 1 to amount) {
      for (j <- coins.indices) {
        if (i >= coins(j) && dp(i - coins(j)) != -1)
          if (dp(i) == -1 || dp(i) > dp(i - coins(j)) + 1)
            dp(i) = dp(i - coins(j)) + 1
      }
    }
    dp(amount)
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(1,3,5)
    println(coinChange2(arr,11))
  }
}

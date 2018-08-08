package study.leetcode.SortAndSearch

/**
  *
  * 买卖股票的最佳时机
  * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
  * *
  * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
  * *
  * 注意你不能在买入股票前卖出股票。
  * *
  * 示例 1:
  * *
  * 输入: [7,1,5,3,6,4]
  * 输出: 5
  * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
  * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
  * 示例 2:
  * *
  * 输入: [7,6,4,3,1]
  * 输出: 0
  * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
  *
  * Created by root on 2018-6-28.
  */
object BestTimeToBuyAndSellStock {
  /**
    * 时间复杂度O(n2)
    *
    * @param prices
    * @return
    */
  def maxProfit(prices: Array[Int]): Int = {
    var max = 0
    for (i <- 0 until prices.length - 1) {
      for (j <- (i + 1) until prices.length) {
        val profit = prices(j) - prices(i)
        if (profit > max)
          max = profit
      }
    }
    max
  }

  /**
    * 时间复杂度为O(n)
    *
    * @param prices
    * @return
    */

  def maxProfit2(prices: Array[Int]): Int = {
    if (prices.size >= 2) {
      var max = 0
      var low = prices(0)
      for (i <- 1 until prices.length) {
        max = math.max(max, prices(i) - low)
        low = math.min(low, prices(i))
      }
      max
    } else
      0
  }
}

package study.interview.byteDance.DynamicAndGreedy


/**
  * 买卖股票的最佳时机
  * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
  *
  * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
  *
  * 注意你不能在买入股票前卖出股票。
  *
  * 示例 1:
  *
  * 输入: [7,1,5,3,6,4]
  * 输出: 5
  * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
  * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
  * 示例 2:
  *
  * 输入: [7,6,4,3,1]
  * 输出: 0
  * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
  */

// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/
object MaxProfit {
  def main(args: Array[String]): Unit = {
    val arr1 = Array(7, 1, 5, 3, 6, 4)
    val arr2 = Array(7, 6, 4, 3, 1)
    val arr3 = Array(1, 2, 3, 4, 5)

    println(maxProfit1a(arr1))
    println(maxProfit1a(arr2))

    println(maxProfitk(arr1))
    println(maxProfitk(arr2))
    println(maxProfitk(arr3))

    val arr4 = Array(1, 2, 3, 0, 2)
    println(maxProfitkWithCoolDown(arr4))

    val arr5 = Array(1, 3, 2, 8, 4, 9)
    println(maxProfitkWithFee(arr5, 2))

    val arr6 = Array(3, 3, 5, 0, 0, 3, 1, 4)
    println(maxProfitk2a(arr6))
    println(maxProfitk2b(arr6))

    val arr7 = Array(2,4,1)
    val arr8 = Array(3,2,6,5,0,3)
    val arr9 = Array(3,3,5,0,0,3,1,4)
    println(maxProfitkAny(2,arr9))

  }

  /**
    * 只能用一次交易： k=1
    *
    * @param prices
    * @return
    */
  def maxProfit1a(prices: Array[Int]): Int = {
    val n = prices.length
    val dp = Array.fill(n, 2)(0)
    //解释：dp(0)(0) = max(dp(-1)(0),dp(-1)(1) + prices(i) = max(0,-infinity + prices(i)) = 0
    dp(0)(0) = 0
    //解释：dp(0)(1) = max(dp(-1)(1), dp(-1)(0) - prices(i)) = max(-infinity, 0 - prices(i)) = -prices(i)
    dp(0)(1) = -prices(0)
    for (i <- 1 until n) {
      dp(i)(0) = dp(i - 1)(0).max(dp(i - 1)(1) + prices(i))
      dp(i)(1) = dp(i - 1)(1).max(-prices(i))
    }
    dp(n - 1)(0)
  }

  /**
    * O(1) 方法
    *
    * @param prices
    * @return
    */
  def maxProfit1b(prices: Array[Int]): Int = {
    var dp_i_0 = 0
    var dp_i_1 = Int.MinValue
    for (p <- prices) {
      dp_i_0 = dp_i_0.max(dp_i_1 + p)
      dp_i_1 = dp_i_1.max(-p)
    }
    dp_i_0
  }

  /**
    * k = +infinity
    * O(1)
    */

  def maxProfitk(prices: Array[Int]): Int = {
    var dp_i_0 = 0
    var dp_i_1 = Int.MinValue
    for (p <- prices) {
      val temp = dp_i_0
      dp_i_0 = dp_i_0.max(dp_i_1 + p)
      dp_i_1 = dp_i_1.max(dp_i_0 - p)
    }
    dp_i_0
  }

  /**
    * k = +infinity
    * 每次 sell 之后要等一天才能继续交易。
    *
    * @param prices
    * @return
    */
  def maxProfitkWithCoolDown(prices: Array[Int]): Int = {
    var dp_i_0 = 0
    var dp_i_1 = Int.MinValue
    var dp_pre_0 = 0
    for (p <- prices) {
      val temp = dp_i_0
      dp_i_0 = dp_i_0.max(dp_i_1 + p)
      dp_i_1 = dp_i_1.max(dp_pre_0 - p)
      dp_pre_0 = temp
    }
    dp_i_0
  }

  /**
    * k = +infinity
    * 每次交易要支付手续费，只要把手续费从利润中减去即可。
    *
    * @param prices
    * @return
    */
  def maxProfitkWithFee(prices: Array[Int], fee: Int): Int = {
    var dp_i_0 = 0
    var dp_i_1 = Int.MinValue
    for (p <- prices) {
      val temp = dp_i_0
      dp_i_0 = dp_i_0.max(dp_i_1 + p)
      dp_i_1 = dp_i_1.max(temp - p - fee)
    }
    dp_i_0
  }

  /**
    * k = 2
    *
    * @param prices
    * @return
    */
  def maxProfitk2a(prices: Array[Int]): Int = {
    val max_k = 2
    val n = prices.length
    val dp = Array.fill(n, max_k + 1, 2)(0)

    for (i <- prices.indices) {
      for (k <- (1 to max_k).reverse) {
        if (i - 1 == -1) {
          dp(i)(k)(0) = 0
          dp(i)(k)(1) = -prices(i)
        } else {
          dp(i)(k)(0) = dp(i - 1)(k)(0).max(dp(i - 1)(k)(1) + prices(i))
          dp(i)(k)(1) = dp(i - 1)(k)(1).max(dp(i - 1)(k - 1)(0) - prices(i))
        }
      }
    }
    dp(n - 1)(max_k)(0)
  }


  def maxProfitk2b(prices: Array[Int]): Int = {
    var dp_i10, dp_i20 = 0
    var dp_i11, dp_i21 = Int.MinValue
    for (p <- prices) {
      dp_i20 = dp_i20.max(dp_i21 + p)
      dp_i21 = dp_i21.max(dp_i10 - p)
      dp_i10 = dp_i10.max(dp_i11 + p)
      dp_i11 = dp_i11.max(-p)
    }
    dp_i20
  }


  /**
    * k = any integer
    *
    * @param prices
    * @return
    */
  def maxProfitkAny(k: Int, prices: Array[Int]): Int = {
    val n = prices.length
    if (k > n / 2)
      maxProfitk(prices)
    else {
      val dp = Array.fill(n, k + 1, 2)(0)
      for (i <- prices.indices) {
        for (kf <- (1 to k).reverse) {
          if (i - 1 == -1) {
            dp(i)(kf)(0) = 0
            dp(i)(kf)(1) = -prices(i)
          } else {
            dp(i)(kf)(0) = dp(i - 1)(kf)(0).max(dp(i - 1)(kf)(1) + prices(i))
            dp(i)(kf)(1) = dp(i - 1)(kf)(1).max(dp(i - 1)(kf - 1)(0) - prices(i))
          }
        }
      }
      dp(n - 1)(k)(0)
    }
  }
}

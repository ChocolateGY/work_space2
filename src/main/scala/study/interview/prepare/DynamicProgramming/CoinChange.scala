package study.interview.prepare.DynamicProgramming

/**
  * 题目：给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，再给一个总金额 n，
  * 问你最少需要几枚硬币凑出这个金额，如果不可能凑出，则回答 -1 。
  *
  * 比如说，k = 3，面值分别为 1，2，5，总金额 n = 11，那么最少需要 3 枚硬币，
  * 即 11 = 5 + 5 + 1 。下面走流程。
  */
object CoinChange {
  /**
    * 其实，这个方程就用到了「最优子结构」性质：原问题的解由子问题的最优解构成。
    * 即 f(11) 由 f(10), f(9), f(6) 的最优解转移而来。
    *
    * 记住，要符合「最优子结构」，子问题间必须互相独立。啥叫相互独立？你肯定不想看数学证明，我用一个直观的例子来讲解。
    * 比如说，你的原问题是考出最高的总成绩，那么你的子问题就是要把语文考到最高，数学考到最高...... 为了每门课考到最高，
    * 你要把每门课相应的选择题分数拿到最高，填空题分数拿到最高...... 当然，最终就是你每门课都是满分，这就是最高的总成绩。
    * 得到了正确的结果：最高的总成绩就是总分。因为这个过程符合最优子结构，“每门科目考到最高”这些子问题是互相独立，
    * 互不干扰的。
    * 但是，如果加一个条件：你的语文成绩和数学成绩会互相制约，此消彼长。这样的话，显然你能考到的最高总成绩就达不到总分了，
    * 按刚才那个思路就会得到错误的结果。因为子问题并不独立，语文数学成绩无法同时最优，所以最优子结构被破坏。
    *
    * 回到凑零钱问题，显然子问题之间没有相互制约，而是互相独立的。所以这个状态转移方程是可以得到正确答案的。
    */

  //暴力
  def coinChange1(coins: Array[Int], amount: Int): Int = {
    if (amount == 0) return 0
    var ans = Int.MaxValue
    for (coin <- coins) {
      if (amount - coin >= 0) {
        val subPro = coinChange1(coins, amount - coin)
        if (subPro != -1) {
          ans = ans.min(subPro + 1)
        }

      }
    }
    if (ans == Int.MaxValue)
      -1
    else
      ans
  }

  //带备忘录的递归算法

  def coinChange2(coins: Array[Int], amount: Int): Int = {
    val arr = Array.fill(amount + 1)(-2)

    def helper(n: Int): Int = {
      if (n == 0) return 0
      if (arr(n) != -2) return arr(n)
      var ans = Int.MaxValue
      for (coin <- coins) {
        if (n - coin >= 0) {
          val subPro = helper(n - coin)
          if (subPro != -1) {
            ans = ans.min(subPro + 1)
          }
        }
      }
      arr(n) = if (ans == Int.MaxValue) -1 else ans
      arr(n)
    }

    helper(amount)
  }

  //动态规划
  def coinChange3(coins: Array[Int], amount: Int): Int = {
    val dp = Array.fill(amount + 1)(amount + 1)
    dp(0) = 0
    for (i <- 1 to amount) {
      for (coin <- coins) {
        if (i - coin >= 0) {
          dp(i) = dp(i).min(1 + dp(i - coin))
        }
      }
    }
    if (dp(amount) == amount + 1)
      -1
    else
      dp(amount)
  }
}

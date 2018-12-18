package study.leetcode_middle.Mathematics

/**
  * Created by root on 2018-9-13.
  * 快乐数
  * 编写一个算法来判断一个数是不是“快乐数”。
  * *
  * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
  * 然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
  * *
  * 示例:
  * *
  * 输入: 19
  * 输出: true
  * 解释:
  * 12 + 92 = 82
  * 82 + 22 = 68
  * 62 + 82 = 100
  * 12 + 02 + 02 = 1
  * 其中的2是平方
  */
object IsHappy {

  /**
    * 解法：循环求平方和，即求出当前数组的平方和后，再以此平方和作为新的数继续求平方和，
    * 而循环终止条件是：得到的平方和为1，或得到的平方和在之前的循环中出现过，那以后会一直循环，
    * 不可能达到1。判断平方和是否为1很简单，每次检查就好了；而判断平方和是否出现过，
    * 则只需要维持一个Set，每次循环检查当前平方和是否在Set中，在则终止循环，不在则将此平方和放到Set中。
    *
    * @param n
    * @return
    */
  def isHappy(n: Int): Boolean = {
    var temp = n
    val set = collection.mutable.Set[Int]()
    while (temp != 1 && !set.contains(temp)) {
      set += temp
      var count = 0
      while (temp != 0) {
        val num = temp % 10
        count += num * num
        temp = temp / 10
      }
      temp = count
    }
    temp == 1
  }

  /**
    * 其实这道题也可以不用set来做，我们并不需要太多的额外空间，
    * 关于非快乐数有个特点，循环的数字中必定会有4，这里就不做证明了，
    * 我也不会证明，就是利用这个性质，就可以不用set了，参见代码如下：
    *
    * @param n
    * @return
    */
  def isHappy2(n: Int): Boolean = {
    var count = 0
    var temp = n
    while (count != 1 && count != 4) {
      count = 0
      while (temp != 0) {
        val num = temp % 10
        count += num * num
        temp = temp / 10
      }
      temp = count
    }
    count == 1
  }
}

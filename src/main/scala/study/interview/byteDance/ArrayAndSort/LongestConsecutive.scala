package study.interview.byteDance.ArrayAndSort

/**
  * 最长连续序列
  * 给定一个未排序的整数数组，找出最长连续序列的长度。
  *
  * 要求算法的时间复杂度为 O(n)。
  *
  * 示例:
  *
  * 输入: [100, 4, 200, 1, 3, 2]
  * 输出: 4
  * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
  */
object LongestConsecutive {
  def main(args: Array[String]): Unit = {
    val arr = Array(100, 4, 200, 1, 3, 2)
    println(longestConsecutive(arr))
  }

  /**
    * 使用set保存
    *
    * @param nums
    * @return
    */
  def longestConsecutive(nums: Array[Int]): Int = {
    var res = 0
    if (nums.nonEmpty) {
      val set = collection.mutable.Set[Int]() ++ nums
      var pre, last = 0
      for (n <- nums) {
        if (set.remove(n)) {
          pre = n - 1
          while (set.remove(pre)) pre -= 1
          last = n + 1
          while (set.remove(last)) last += 1
        }
        res = res.max(last - pre - 1)
      }
    }
    res
  }
}

package study.leetcode_advanced.ArrayAndString

import scala.collection.mutable

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

  /**
    * 这道题要求求最长连续序列，并给定了O(n)复杂度限制，我们的思路是，使用一个集合set存入所有的数字，
    * 然后遍历数组中的每个数字，如果其在集合中存在，那么将其移除，然后分别用两个变量pre和next算出其前一个数跟后一个数，
    * 然后在集合中循环查找，如果pre在集合中，那么将pre移除集合，然后pre再自减1，直至pre不在集合之中，
    * 对next采用同样的方法，那么next-pre-1就是当前数字的最长连续序列，更新res即可。
    *
    * @param nums
    * @return
    */
  def longestConsecutive(nums: Array[Int]): Int = {
    var res = 0
    val set = collection.mutable.HashSet[Int]()
    for (n <- nums) set += n
    for (n <- nums) {
      if (set.remove(n)) {
        var pre = n - 1
        var next = n + 1
        while (set.remove(pre)) pre -= 1
        while (set.remove(next)) next += 1
        res = res.max(next - pre - 1)
      }
    }
    res
  }

  /**
    * 超时
    *
    * @param nums
    * @return
    */
  def longestConsecutive2(nums: Array[Int]): Int = {
    var res = 0
    val set = nums.map(_ -> 0).toMap
    for (n <- nums) {
      if (set.getOrElse(n, 1) != 1) {
        set.updated(n, 1)
        var pre = n - 1
        var next = n + 1
        while (set.getOrElse(pre, 1) != 1) {
          pre -= 1
          set.updated(pre, 1)
        }
        while (set.getOrElse(next, 1) != 1) {
          next += 1
          set.updated(next, 1)
        }
        res = res.max(next - pre - 1)
      }
    }
    res
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(100, 4, 200, 1, 3, 2)
    val res = longestConsecutive(arr)
    println(res)
  }
}

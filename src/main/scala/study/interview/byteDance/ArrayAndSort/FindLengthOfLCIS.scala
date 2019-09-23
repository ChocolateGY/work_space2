package study.interview.byteDance.ArrayAndSort

/**
  * 最长连续递增序列
  * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
  *
  * 示例 1:
  *
  * 输入: [1,3,5,4,7]
  * 输出: 3
  * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
  * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
  * 示例 2:
  *
  * 输入: [2,2,2,2,2]
  * 输出: 1
  * 解释: 最长连续递增序列是 [2], 长度为1。
  * 注意：数组长度不会超过10000。
  */
object FindLengthOfLCIS {
  def main(args: Array[String]): Unit = {
    val nums = Array(1, 3, 5, 4, 7)
    println(findLengthOfLCIS(nums))
    val nums2 = Array(2, 2, 2, 2, 2)
    println(findLengthOfLCIS(nums2))
    val nums3 = Array(1,3,5,7)
    println(findLengthOfLCIS(nums3))
  }


  def findLengthOfLCIS(nums: Array[Int]): Int = {
    var count = 0
    if (nums.nonEmpty) {
      var ct = 1
      for (i <- 1 until nums.length) {
        if (nums(i) > nums(i - 1))
          ct += 1
        else
          ct = 1
        count = count.max(ct)
      }
      count = count.max(ct)
    }
    count
  }
}

package study.leetcode_advanced.ArrayAndString

/**
  * 第一个缺失的正数
  * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
  *
  * 示例 1:
  *
  * 输入: [1,2,0]
  * 输出: 3
  * 示例 2:
  *
  * 输入: [3,4,-1,1]
  * 输出: 2
  * 示例 3:
  *
  * 输入: [7,8,9,11,12]
  * 输出: 1
  * 说明:
  *
  * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
  */
object FirstMissingPositive {
  def firstMissingPositive(nums: Array[Int]): Int = {
    val size = nums.length
    for (i <- nums.indices) {
      //如果交换的数据还是大于0且<i+1，则放在合适的位置,且数据不相等，避免死循环
      //这个while是关键，其他都是没有难度的
      while (nums(i) != i + 1 && nums(i) > 0 && nums(i) <= nums.length && nums(i) != nums(nums(i) - 1)) {
        val temp = nums(i)
        nums(i) = nums(temp - 1)
        nums(temp - 1) = temp
      }
    }
    for (i <- nums.indices) {
      if (nums(i) != i + 1)
        return i + 1
    }
    size + 1
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(-1, 4, 2, 1, 9, 10)
    val res = firstMissingPositive(arr)
    println(res)
  }
}

package study.leetcode_primary.Array

import study.leetcode_primary.Utillity

/**
  * 移动零
  *
  * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
  * *
  * 示例:
  * *
  * 输入: [0,1,0,3,12]
  * 输出: [1,3,12,0,0]
  * 说明:
  * *
  * 必须在原数组上操作，不能拷贝额外的数组。
  * 尽量减少操作次数。
  * Created by root on 2018-6-1.
  */
object MoveZeroes {
  def moveZeroes(nums: Array[Int]): Unit = {
    for (i <- (0 until nums.length - 1).reverse if nums(i) == 0; j <- i until nums.length - 1) {
      val temp = nums(j)
      nums(j) = nums(j + 1)
      nums(j + 1) = temp
    }
  }

  def moveZeroes2(nums: Array[Int]): Unit = {
    var flag = true
    for (i <- 0 until nums.length - 1 if nums(i) == 0) {
      flag = true
      for (j <- i + 1 until nums.length if nums(j) != 0 && flag) {
        val temp = nums(i)
        nums(i) = nums(j)
        nums(j) = temp
        flag = false
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val nums = Array(0, 1, 0, 3, 12) // 1 0 0 3 12   1 3 0 0 12  1 3 12 0 0
    println(Utillity.time(moveZeroes2(nums)))
    println("haha")
    nums.foreach(print)
  }
}

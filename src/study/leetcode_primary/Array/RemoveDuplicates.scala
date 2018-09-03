package study.leetcode_primary.Array

/**
  * Created by root on 2018-5-30.
  *
  * 从排序数组中删除重复项
  *
  *  给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
  * *
  * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
  *
  */
object RemoveDuplicates {
  def removeDuplicates(nums: Array[Int]): Int = {
    var index = 1
    if (nums == null && nums.size == 0)
      0
    else {

      for (i <- 1 until nums.size) {
        if (nums(i) != nums(i - 1)) {
          nums(index) = nums(i)
          index += 1
        }
      }
      index
    }
  }

  def main(args: Array[String]): Unit = {

    val nums = Array(1, 1, 2)
    val index = removeDuplicates(nums)
    for (i <- 0 until index) {
      print(nums(i) + ",")
    }
  }
}

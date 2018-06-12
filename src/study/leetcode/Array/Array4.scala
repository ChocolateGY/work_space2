package study.leetcode.Array

/**
  * 存在重复
  *
  * 给定一个整数数组，判断是否存在重复元素。
  * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
  * Created by root on 2018-5-31.
  */
object Array4 {
  def containsDuplicate(nums: Array[Int]): Boolean = {
    val map = collection.mutable.Map[Int, Int]()
    for (i <- nums) {
      val t = map.getOrElse(i, 0)
      map += ((i, t + 1))
    }
    if (map.values.count(_ > 1) > 0)
      true
    else
      false
  }

  def containsDuplicate2(nums: Array[Int]): Boolean = {

    if (nums.toSet.size != nums.size)
      true
    else
      false
  }

  def main(args: Array[String]): Unit = {
    val nums = Array(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)
    println(containsDuplicate(nums))
  }
}

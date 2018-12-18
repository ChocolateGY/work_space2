package study.leetcode_middle.Mathematics

/**
  * 求众数
  * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
  *
  * 你可以假设数组是非空的，并且给定的数组总是存在众数。
  *
  * 示例 1:
  *
  * 输入: [3,2,3]
  * 输出: 3
  * 示例 2:
  *
  * 输入: [2,2,1,1,1,2,2]
  * 输出: 2
  */
object MajorityElement {
  def majorityElement(nums: Array[Int]): Int = {
    val map = collection.mutable.Map[Int, Int]()
    nums.foreach {
      n =>
        val sum = map.getOrElseUpdate(n, 0) + 1
        map.update(n, sum)
    }
    map.maxBy(_._2)._1
  }
}

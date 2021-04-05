package study.interview.prepare.BinarySearch

/**
  * 二分查找
  * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
  * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
  *
  *
  * 示例 1:
  *
  * 输入: nums = [-1,0,3,5,9,12], target = 9
  * 输出: 4
  * 解释: 9 出现在 nums 中并且下标为 4
  * 示例 2:
  *
  * 输入: nums = [-1,0,3,5,9,12], target = 2
  * 输出: -1
  * 解释: 2 不存在 nums 中因此返回 -1
  *
  *
  * 提示：
  *
  * 你可以假设 nums 中的所有元素是不重复的。
  * n 将在 [1, 10000]之间。
  * nums 的每个元素都将在 [-9999, 9999]之间。
  */
object BinarySerach {
  def main(args: Array[String]): Unit = {
    val arr = Array(-1, 0, 3, 5, 9, 12)
    val tar = search(arr, 9)
    println(tar)

  }

  //摸板一  用于查找可以通过访问数组中的单个索引来确定的元素或条件
  def search(nums: Array[Int], target: Int): Int = {
    if (nums == null || nums.length == 0)
      return -1
    var low = 0
    var high = nums.length - 1
    while (low <= high) {
      val mid = low + (high - low) / 2
      if (nums(mid) == target)
        return mid
      else if (nums(mid) > target)
        high = mid - 1
      else
        low = mid + 1
    }
    -1
  }

  //模板 #2 是二分查找的高级模板。它用于查找需要访问数组中当前索引及其直接右邻居索引的元素或条件。
  //https://leetcode-cn.com/explore/learn/card/binary-search/210/template-ii/839/
  def search2(nums: Array[Int], target: Int): Int = {
    var left = 0
    var right = nums.length
    while (left < right) {
      val mid = left + (right - left) / 2
      if (nums(mid) == target)
        return mid
      else if (nums(mid) < target)
        left = mid + 1
      else
        right = mid
    }
    if (left != nums.length && nums(left) == target)
      left
    else
      -1
  }


}

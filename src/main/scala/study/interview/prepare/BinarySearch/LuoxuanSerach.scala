package study.interview.prepare.BinarySearch

/**
  * 搜索旋转排序数组
  * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
  *
  * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
  *
  * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
  *
  * 你可以假设数组中不存在重复的元素。
  *
  * 你的算法时间复杂度必须是 O(log n) 级别。
  *
  * 示例 1:
  *
  * 输入: nums = [4,5,6,7,0,1,2], target = 0
  * 输出: 4
  * 示例 2:
  *
  * 输入: nums = [4,5,6,7,0,1,2], target = 3
  * 输出: -1
  */
object LuoxuanSerach {
  //标准输出
  def search(nums: Array[Int], target: Int): Int = {
    var low = 0
    var high = nums.length - 1
    while (low <= high) {
      val mid = low + (high - low) / 2
      if (target == nums(mid))
        return mid
      if (nums(mid) < nums(high)) {
        if (target > nums(mid) && target <= nums(high))
          low = mid + 1
        else
          high = mid - 1
      } else {
        if (target < nums(mid) && target >= nums(low))
          high = mid - 1
        else
          low = mid + 1
      }
    }
    -1
  }
}

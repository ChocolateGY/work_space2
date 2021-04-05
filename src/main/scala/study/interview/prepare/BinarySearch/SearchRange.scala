package study.interview.prepare.BinarySearch

/**
  * 在排序数组中查找元素的第一个和最后一个位置
  * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
  *
  * 你的算法时间复杂度必须是 O(log n) 级别。
  *
  * 如果数组中不存在目标值，返回 [-1, -1]。
  *
  * 示例 1:
  *
  * 输入: nums = [5,7,7,8,8,10], target = 8
  * 输出: [3,4]
  * 示例 2:
  *
  * 输入: nums = [5,7,7,8,8,10], target = 6
  * 输出: [-1,-1]
  */
object SearchRange {
  def searchRange(nums: Array[Int], target: Int): Array[Int] = {
    if(nums.isEmpty)
      return Array(-1,-1)
    //搜索左边
    var left = 0
    var right = nums.length
    var rangeLeft = -1
    while (left < right) {
      val mid = left + (right - left) / 2
      if (nums(mid) == target)
        right = mid
      else if (nums(mid) > target)
        right = mid
      else
        left = mid + 1
    }
    if (left != nums.length && nums(left) == target)
      rangeLeft = left

    //搜索右边
    left = 0
    right = nums.length
    var rangeRight = -1
    while (left < right) {
      val mid = left + (right - left) / 2
      if (nums(mid) == target)
        left = mid + 1
      else if (nums(mid) > target)
        right = mid
      else
        left = mid + 1
    }
    if(left != 0 && nums(left-1) == target)
      rangeRight = left -1
    Array(rangeLeft,rangeRight)
  }

  //使用标准模板也是可以的
  def searchRange2(nums: Array[Int], target: Int): Array[Int] = {
    if(nums.isEmpty)
      return Array(-1,-1)
    //搜索左边
    var left = 0
    var right = nums.length -1
    var rangeLeft = -1
    while (left <= right) {
      val mid = left + (right - left) / 2
      if (nums(mid) == target)
        right = mid - 1
      else if (nums(mid) > target)
        right = mid - 1
      else
        left = mid + 1
    }
    if (left != nums.length && nums(left) == target)
      rangeLeft = left

    //搜索右边
    left = 0
    right = nums.length -1
    var rangeRight = -1
    while (left <= right) {
      val mid = left + (right - left) / 2
      if (nums(mid) == target)
        left = mid + 1
      else if (nums(mid) > target)
        right = mid - 1
      else
        left = mid + 1
    }
    if(left != 0 && nums(left-1) == target)
      rangeRight = left -1
    Array(rangeLeft,rangeRight)
  }

}

package study.interview.prepare.BinarySearch

/**
  * 寻找旋转排序数组中的最小值
  * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
  *
  * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
  *
  * 请找出其中最小的元素。
  *
  * 你可以假设数组中不存在重复元素。
  *
  * 示例 1:
  *
  * 输入: [3,4,5,1,2]
  * 输出: 1
  * 示例 2:
  *
  * 输入: [4,5,6,7,0,1,2]
  * 输出: 0
  */
object FindMin {
  def findMin(nums: Array[Int]): Int = {
    if (nums.length == 1) {
      return nums.head
    }
    var left = 0
    var right = nums.length - 1
    // 1,2,3
    if (nums(left) < nums(right))
      return nums(left)
    //二分查找
    while (left <= right) {
      val mid = left + (right - left) / 2
      //4,5,6,1,2,3
      if (nums(mid + 1) < nums(mid))
        return nums(mid + 1)
      if (nums(mid - 1) > nums(mid))
        return nums(mid)
      //如果mid值比nums(0)大，那么说明最小值还在右边；否则最小值在mid左边
      if (nums(mid) > nums.head)
        left = mid + 1
      else
        right = mid - 1
    }
    -1
  }
}

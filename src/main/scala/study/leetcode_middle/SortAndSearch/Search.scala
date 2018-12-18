package study.leetcode_middle.SortAndSearch

/**
  * Created by root on 2018-9-3.
  * 搜索旋转排序数组
  * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
  * *
  * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
  * *
  * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
  * *
  * 你可以假设数组中不存在重复的元素。
  * *
  * 你的算法时间复杂度必须是 O(log n) 级别。
  * *
  * 示例 1:
  * *
  * 输入: nums = [4,5,6,7,0,1,2], target = 0
  * 输出: 4
  * 示例 2:
  * *
  * 输入: nums = [4,5,6,7,0,1,2], target = 3
  * 输出: -1
  */
object Search {
  /**
    * 这道题让在旋转数组中搜索一个给定值，若存在返回坐标，若不存在返回-1。我们还是考虑二分搜索法，
    * 但是这道题的难点在于我们不知道原数组在哪旋转了，我们还是用题目中给的例子来分析，对于数组[0 1 2 4 5 6 7] 共有下列七种旋转方法：
    *
    * 0　　1　　2　　 4　　5　　6　　7
    *
    * 7　　0　　1　　 2　　4　　5　　6
    *
    * 6　　7　　0　　 1　　2　　4　　5
    *
    * 5　　6　　7　　 0　　1　　2　　4
    *
    * 4　　5　　6　　7　　0　　1　　2
    *
    * 2　　4　　5　　6　　7　　0　　1
    *
    * 1　　2　　4　　5　　6　　7　　0
    *
    * 二分搜索法的关键在于获得了中间数后，判断下面要搜索左半段还是右半段，我们观察上面红色加粗的数字都是升序的，
    * 由此我们可以观察出规律，如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的，
    * 我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了，代码如下
    *
    * @param nums
    * @param target
    * @return
    *
    *  测试组： [3,1] 1
    *          [1] 0
    */
  def search(nums: Array[Int], target: Int): Int = {
    if (nums.nonEmpty) {
      var start = 0
      var end = nums.length - 1
      while (start <= end) {
        val middle = (start + end) >>> 1
        if (nums(middle) == target)
          return middle
        else if (nums(middle) >= nums(start)) { //必须有=
          if (target >= nums(start) && target < nums(middle)) {
            end = middle - 1
          } else
            start = middle + 1
        } else {
          if (target > nums(middle) && target <= nums(end)) {
            start = middle + 1
          } else {
            end = if (middle == 0) 0 else middle - 1 //这里需要做一步判断
          }
        }

      }
    }
    -1
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(1)
    val index = search(arr, 0)
    println(index)
  }
}

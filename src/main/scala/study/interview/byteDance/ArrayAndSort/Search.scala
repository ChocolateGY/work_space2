package study.interview.byteDance.ArrayAndSort

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
object Search {
  def main(args: Array[String]): Unit = {
    val arr = Array(4, 5, 6, 7, 0, 1, 2)
    println(search2(arr, 0))
    println(search2(arr, 3))
    val arr1 = Array(0, 1, 2, 3, 4, 5, 6, 7)
    println("1=====")
    println(binarySerach1(arr1, 0))
    println(binarySerach1(arr1, 1))
    println(binarySerach1(arr1, 2))
    println(binarySerach1(arr1, 3))
    println(binarySerach1(arr1, 4))
    println(binarySerach1(arr1, 5))
    println(binarySerach1(arr1, 6))
    println(binarySerach1(arr1, 7))
    println("2=====")
    println(binarySerach2(arr1, 0))
    println(binarySerach2(arr1, 1))
    println(binarySerach2(arr1, 2))
    println(binarySerach2(arr1, 3))
    println(binarySerach2(arr1, 4))
    println(binarySerach2(arr1, 5))
    println(binarySerach2(arr1, 6))
    println(binarySerach2(arr1, 7))
    println("3=====")
    println(binarySerach3(arr1, 0))
    println(binarySerach3(arr1, 1))
    println(binarySerach3(arr1, 2))
    println(binarySerach3(arr1, 3))
    println(binarySerach3(arr1, 4))
    println(binarySerach3(arr1, 5))
    println(binarySerach3(arr1, 6))
    println(binarySerach3(arr1, 7))
    println("4=====")
    println(binarySerach4(arr1, -2))
    println(binarySerach4(arr1, 0))
    println(binarySerach4(arr1, 1))
    println(binarySerach4(arr1, 2))
    println(binarySerach4(arr1, 3))
    println(binarySerach4(arr1, 4))
    println(binarySerach4(arr1, 5))
    println(binarySerach4(arr1, 6))
    println(binarySerach4(arr1, 7))
    println(binarySerach4(arr1, 9))
    println(Int.MaxValue + "==" + Int.MinValue)
    println((Int.MinValue + Int.MaxValue) / 2)
    println("下面两种写法也会越界，只不过数组角标是从0开始的，不考虑负数")
    println((Int.MinValue + Int.MaxValue) >>> 1)
    print(Int.MinValue + (Int.MaxValue - Int.MinValue) / 2)
  }

  /**
    * [0,1,2,4,5,6,7]
    * [1,2,4,5,6,7,0]
    * [2,4,5,6,7,0,1]
    * [4,5,6,7,0,1,2]
    * [5,6,7,0,1,2,4]
    * [6,7,0,1,2,4,5]
    * [7,0,1,2,4,5,6]
    *
    * @param nums
    * @param target
    * @return
    */
  def search(nums: Array[Int], target: Int): Int = {
    var low: Int = 0
    var high: Int = nums.length - 1
    while (low <= high) {
      val mid: Int = low + (high - low) / 2
      if (nums(mid) == target)
        return mid
      else if (nums(mid) < nums(high)) {
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

  /**
    * 二分查找的边界问题
    * low < high 的方式
    * 忘记吧，不实用
    *
    * @param nums
    * @param target
    * @return
    */
  def search2(nums: Array[Int], target: Int): Int = {
    var low = 0
    var high = nums.length - 1
    while (low < high) {
      val mid = low + (high - low) / 2
      if (target == nums(mid))
        return mid
      else if (nums(mid) < nums(high)) {
        if (target > nums(mid) && target <= nums(high))
          low = mid + 1
        else
          high = mid
      } else {
        if (target < nums(mid) && target >= nums(low))
          high = mid
        else
          low = mid + 1
      }
    }
    if (nums.nonEmpty && nums(low) == target)
      low
    else
      -1
  }

  /**
    *
    * 以下二分法的三种模板
    * 第一种，无脑 <= , +1 -1 , return mid 。-1
    * 第二种，< , low = mid +1 ,return low or high, 如果没有还需判断
    * 第三种，< , high = mid - 1, mid 额外 +1  ,return low or high, 如果没有还需判断
    *
    * 第二种和第三种的差别：主要是
    *
    * 所以，无脑选第一种完事儿
    *
    * java.util.Collections 中有 binarySerach
    */

  def binarySerach1(arr: Array[Int], n: Int): Int = {
    var low = 0
    var high = arr.length - 1
    while (low <= high) {
      val mid = low + (high - low) / 2
      if (arr(mid) == n)
        return mid
      else if (n > arr(mid))
        low = mid + 1
      else
        high = mid - 1
    }
    -1
  }

  def binarySerach2(arr: Array[Int], n: Int): Int = {
    var low = 0
    var high = arr.length - 1
    while (low < high) {
      val mid = low + (high - low) / 2
      if (arr(mid) == n)
        return mid
      else if (n > arr(mid))
        low = mid + 1
      else
        high = mid
    }
    high
  }

  def binarySerach3(arr: Array[Int], n: Int): Int = {
    var low = 0
    var high = arr.length - 1
    while (low < high) {
      val mid = 1 + low + (high - low) / 2
      if (arr(mid) == n)
        return mid
      else if (n > arr(mid))
        low = mid
      else
        high = mid - 1
    }
    high
  }

  //
  def binarySerach4(arr: Array[Int], n: Int): Int = {
    var low = 0
    var high = arr.length
    while (low < high) {
      val mid = low + (high - low) / 2
      if (arr(mid) < n)
        low = mid + 1
      else
        high = mid
    }
    low
  }
}

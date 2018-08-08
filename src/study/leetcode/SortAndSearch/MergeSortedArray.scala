package study.leetcode.SortAndSearch

import scala.collection.mutable.ArrayBuffer

/**
  *
  * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
  * *
  * Note:
  * *
  * The number of elements initialized in nums1 and nums2 are m and n respectively.
  * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
  *
  * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
  * *
  * 说明:
  * *
  * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
  * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
  *
  *
  * Example:
  * *
  * Input:
  * nums1 = [1,2,3,0,0,0], m = 3
  * nums2 = [2,5,6],       n = 3
  * *
  * Output: [1,2,2,3,5,6]
  *
  *
  * Created by root on 2018-6-27.
  */
object MergeSortedArray {
  /**
    * 从后往前填充 O(M+N)时间 和O(1)空间
    *
    * 先当两个数组都有元素的时候填充大的到末尾，如果有一个数组的数用完了，说明剩下的那个数组的所有数都小于当前填充的位置：
    * *
    * 如果是第一个数组用完了，说明剩下的(最小的那些)全是数组2，把数组2填充进去就好了
    * *
    * 如果是第二个数组用完了，说明剩下的全是数组1，不用填充了，他们已经在了
    *
    *
    * 时间超时，使用java的通过。平台问题
    */

  def merge(nums1: Array[Int], m: Int, nums2: Array[Int], n: Int): Unit = {
    var index1 = m - 1
    var index2 = n - 1
    var i = m + n - 1
    while (index1 >= 0 && index2 >= 0 && i >= 0) {
      if (nums1(index1) > nums2(index2)) {
        nums1(i) = nums1(index1)
        index1 -= 1
      } else {
        nums1(i) = nums2(index2)
        index2 -= 1
      }
      i -= 1
    }
    if (index2 >= 0) {
      while (index2 >= 0)
        nums1(i) = nums2(index2)
      i -= 1
      index2 -= 1
    }
  }

}

package study.interview.prepare.Practice

/**
  * 开始之前
  */
object Kaishizhiqian {
  /**
    * 只出现一次的数字
    * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
    *
    * 说明：
    *
    * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
    *
    * 示例 1:
    *
    * 输入: [2,2,1]
    * 输出: 1
    * 示例 2:
    *
    * 输入: [4,1,2,1,2]
    * 输出: 4
    *
    *
    * 1、map的方式
    * 2、异或
    */
  def singleNumber(nums: Array[Int]): Int = {
    nums.reduce(_ ^ _)
  }

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
    *
    *
    * https://leetcode-cn.com/problems/majority-element/solution/qiu-zhong-shu-by-leetcode-2/
    * 1、map
    * 2、排序
    */
  def majorityElement(nums: Array[Int]): Int = {
    val map = scala.collection.mutable.Map[Int, Int]()
    nums.foreach {
      n =>
        map += n -> (map.getOrElse(n, 0) + 1)
    }
    map.maxBy(_._2)._1
  }

  /**
    * 搜索二维矩阵 II
    * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
    *
    * 每行的元素从左到右升序排列。
    * 每列的元素从上到下升序排列。
    * 示例:
    *
    * 现有矩阵 matrix 如下：
    *
    * [
    * [1,   4,  7, 11, 15],
    * [2,   5,  8, 12, 19],
    * [3,   6,  9, 16, 22],
    * [10, 13, 14, 17, 24],
    * [18, 21, 23, 26, 30]
    * ]
    * 给定 target = 5，返回 true。
    *
    * 给定 target = 20，返回 false。
    *
    * 从左下角开始搜索，这样不会漏值。 O(n+m)
    */

  def searchMatrix(matrix: Array[Array[Int]], target: Int): Boolean = {
    if (matrix.isEmpty || matrix(0).isEmpty)
      return false
    var row = matrix.length - 1
    var col = 0
    while (row >= 0 && col < matrix(0).length) {
      if (matrix(row)(col) == target)
        return true
      else if (matrix(row)(col) > target)
        row -= 1
      else
        col += 1
    }
    false
  }

  /**
    * 合并两个有序数组
    * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
    *
    * 说明:
    *
    * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
    * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
    * 示例:
    *
    * 输入:
    * nums1 = [1,2,3,0,0,0], m = 3
    * nums2 = [2,5,6],       n = 3
    *
    * 输出: [1,2,2,3,5,6]
    *
    * 思路：从后往前遍历数组
    */
  def merge(nums1: Array[Int], m: Int, nums2: Array[Int], n: Int): Unit = {
    var index1 = m - 1
    var index2 = n - 1
    var len = m + n - 1
    while (index1 >= 0 && index2 >= 0) {
      if (nums1(index1) >= nums2(index2)) {
        nums1(len) = nums1(index1)
        index1 -= 1
      } else {
        nums1(len) = nums2(index2)
        index2 -= 1
      }
      len -= 1
    }
    while (index2 >= 0) {
      nums1(len) = nums2(index2)
      index2 -= 1
      len -= 1
    }
  }

}

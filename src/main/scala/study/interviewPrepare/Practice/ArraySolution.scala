package study.interviewPrepare.Practice

import scala.util.Random

object ArraySolution {
  def main(args: Array[String]): Unit = {
    val arr = Array(2, 1, 5, 0, 4, 6)
    println(increasingTriplet(arr))
  }

  /**
    * 乘积最大子序列
    * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
    *
    * 示例 1:
    *
    * 输入: [2,3,-2,4]
    * 输出: 6
    * 解释: 子数组 [2,3] 有最大乘积 6。
    * 示例 2:
    *
    * 输入: [-2,0,-1]
    * 输出: 0
    * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
    */
  //https://leetcode-cn.com/problems/maximum-product-subarray/solution/hua-jie-suan-fa-152-cheng-ji-zui-da-zi-xu-lie-by-g/
  //动态规划
  def maxProduct(nums: Array[Int]): Int = {
    var max = Int.MinValue
    var imax, imin = 1
    for (i <- nums.indices) {
      if (nums(i) < 0) {
        val temp = imax
        imax = imin
        imin = temp
      }
      imax = nums(i).max(imax * nums(i))
      imin = nums(i).min(imin * nums(i))
      max = max.max(imax)
    }
    max
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
    */
  def majorityElement(nums: Array[Int]): Int = {
    val map = scala.collection.mutable.Map[Int, Int]()
    nums.foreach {
      x =>
        map += x -> (map.getOrElse(x, 0) + 1)
    }
    map.maxBy(_._2)._1
  }

  /**
    * 旋转数组
    * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
    *
    * 示例 1:
    *
    * 输入: [1,2,3,4,5,6,7] 和 k = 3
    * 输出: [5,6,7,1,2,3,4]
    * 解释:
    * 向右旋转 1 步: [7,1,2,3,4,5,6]
    * 向右旋转 2 步: [6,7,1,2,3,4,5]
    * 向右旋转 3 步: [5,6,7,1,2,3,4]
    * 示例 2:
    *
    * 输入: [-1,-100,3,99] 和 k = 2
    * 输出: [3,99,-1,-100]
    * 解释:
    * 向右旋转 1 步: [99,-1,-100,3]
    * 向右旋转 2 步: [3,99,-1,-100]
    * 说明:
    *
    * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
    * 要求使用空间复杂度为 O(1) 的 原地 算法。
    *
    * 暴力、克隆、优化克隆使用环代替、使用反转（骚操作）
    * https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode/
    */
  def rotate(nums: Array[Int], k: Int): Unit = {
    if (nums.isEmpty || k % nums.length == 0) return
    //索引
    var i = 0
    //防止有环
    var start = 0
    //保存被替换掉的元素
    var temp = nums(0)
    for (c <- nums.indices) {
      //找位置
      i = (k + i) % nums.length
      //保存临时元素
      val cur = nums(i)
      //把位置 赋值
      nums(i) = temp
      //如果有环，则索引+1，把索引值保存，如果没环，则直接把临时值放入被替换到的元素
      if (i == start) {
        start += 1
        i += 1
        temp = nums(i)
      } else
        temp = cur
    }
  }

  /**
    * 存在重复元素
    * 给定一个整数数组，判断是否存在重复元素。
    *
    * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
    *
    * 示例 1:
    *
    * 输入: [1,2,3,1]
    * 输出: true
    * 示例 2:
    *
    * 输入: [1,2,3,4]
    * 输出: false
    * 示例 3:
    *
    * 输入: [1,1,1,3,3,4,3,2,4,2]
    * 输出: true
    *
    * 1、暴力 O(n^2)
    * 2、排序 O(n * logn)
    * 3、map O(n) 空间 O(n)
    **/
  def containsDuplicate(nums: Array[Int]): Boolean = {
    nums.toSet.size != nums.length
  }

  /**
    * 移动零
    * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    *
    * 示例:
    *
    * 输入: [0,1,0,3,12]
    * 输出: [1,3,12,0,0]
    * 说明:
    *
    * 必须在原数组上操作，不能拷贝额外的数组。
    * 尽量减少操作次数。
    *
    * 双指针 https://leetcode-cn.com/problems/move-zeroes/
    */
  def moveZeroes(nums: Array[Int]): Unit = {
    var indexZ = 0
    for (j <- nums.indices) {
      if (nums(j) != 0) {
        nums(indexZ) = nums(j)
        indexZ += 1
      }
    }
    for (i <- indexZ until nums.length) {
      nums(i) = 0
    }
  }

  /**
    * 打乱数组
    * 打乱一个没有重复元素的数组。
    *
    * 示例:
    *
    * // 以数字集合 1, 2 和 3 初始化数组。
    * int[] nums = {1,2,3};
    * Solution solution = new Solution(nums);
    *
    * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
    * solution.shuffle();
    *
    * // 重设数组到它的初始状态[1,2,3]。
    * solution.reset();
    *
    * // 随机返回数组[1,2,3]打乱后的结果。
    * solution.shuffle();
    *
    */
  class Solution(_nums: Array[Int]) {
    /** Resets the array to its original configuration and return it. */
    var original = _nums.clone()
    var arr = _nums

    def reset(): Array[Int] = {
      arr = original
      original = original.clone()
      original
    }

    /** Returns a random shuffling of the array. */
    def shuffle(): Array[Int] = {
      for (i <- arr.indices) {
        val random = Random.nextInt(arr.length - i) + i
        val temp = arr(i)
        arr(i) = arr(random)
        arr(random) = temp
      }
      arr
    }
  }

  /**
    * 两个数组的交集 II
    * 给定两个数组，编写一个函数来计算它们的交集。
    *
    * 示例 1:
    *
    * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
    * 输出: [2,2]
    * 示例 2:
    *
    * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    * 输出: [4,9]
    * 说明：
    *
    * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
    * 我们可以不考虑输出结果的顺序。
    * 进阶:
    *
    * 如果给定的数组已经排好序呢？你将如何优化你的算法？
    * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
    * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
    *
    */
  def intersect(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
    val map = scala.collection.mutable.Map[Int, Int]()
    nums1.foreach {
      x =>
        map += x -> (map.getOrElse(x, 0) + 1)
    }
    nums2.filter {
      x =>
        val times = map.getOrElse(x, 0)
        if (times == 0)
          false
        else {
          map += x -> (times - 1)
          true
        }
    }
  }

  /**
    * 递增的三元子序列
    * 给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。
    *
    * 数学表达式如下:
    *
    * 如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
    * 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
    * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
    *
    * 示例 1:
    *
    * 输入: [1,2,3,4,5]
    * 输出: true
    * 示例 2:
    *
    * 输入: [5,4,3,2,1]
    * 输出: false
    *
    * 子序列，不是连续的子串
    *
    * 3个连续递增子序列
    * 有3个槽位，a,b,c
    * 满足条件 a < b < c，即可
    * 需要将合适的元素填入这3个槽位
    */
  def increasingTriplet(nums: Array[Int]): Boolean = {
    var one = Int.MaxValue
    var two = Int.MaxValue
    for (n <- nums) {
      if (n <= one)
        one = n
      else if (n <= two)
        two = n
      else
        return true
    }
    false
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
    */
  def searchMatrix(matrix: Array[Array[Int]], target: Int): Boolean = {
    if (matrix.isEmpty || matrix(0).isEmpty) return false
    var row = matrix.length - 1
    var col = 0
    while (row >= 0 && col < matrix(0).length) {
      if (target < matrix(row)(col))
        row -= 1
      else if (target > matrix(row)(col)) {
        col += 1
      } else
        return true
    }
    false
  }

  /**
    * 除自身以外数组的乘积
    * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
    *
    * 示例:
    *
    * 输入: [1,2,3,4]
    * 输出: [24,12,8,6]
    * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
    *
    * 进阶：
    * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
    *
    * 正确解法
    * 乘积 = 当前数左边的乘积 * 当前数右边的乘积
    * 但是平台没通过
    *
    */
  def productExceptSelf(nums: Array[Int]): Array[Int] = {
    val res = new Array[Int](nums.length)
    var k = 1
    //左边的乘积
    for (i <- res.indices) {
      res(i) = k
      k *= nums(i)
    }
    k = 1
    //右边的乘积，顺便再乘左边的乘积
    for (i <- res.indices.reverse) {
      res(i) *= k
      k *= nums(i)
    }
    res
  }
  //另一种形式，也没通过
  def productExceptSelf2(nums: Array[Int]): Array[Int] = {
    var totalProduct = nums.product
    var zeroLength = nums.filter(x=>x==0).length
    zeroLength match{
      case 0 => nums.map(x=>totalProduct/x)
      case 1 => var value = nums.reduce((x,y)=>if(x==0) y else if(y==0) x else x*y); nums.map(x => if(x!=0) 0 else value)
      case _ => nums.map(x => 0)
    }
  }
}

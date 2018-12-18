package study.leetcode_middle.SortAndSearch

/**
  * Created by root on 2018-8-30.
  * 颜色分类
  * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
  * *
  * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
  * *
  * 注意:
  * 不能使用代码库中的排序函数来解决这道题。
  * *
  * 示例:
  * *
  * 输入: [2,0,2,1,1,0]
  * 输出: [0,0,1,1,2,2]
  * 进阶：
  * *
  * 一个直观的解决方案是使用计数排序的两趟扫描算法。
  * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
  * 你能想出一个仅使用常数空间的一趟扫描算法吗？
  */
object SortColors {
  def sortColors(nums: Array[Int]): Unit = {
    for (i <- nums.indices) {
      for (j <- i + 1 until nums.length) {
        if (nums(i) > nums(j)) {
          val temp = nums(i)
          nums(i) = nums(j)
          nums(j) = temp
        }
      }
    }
  }

  def sortColors2(nums: Array[Int]): Unit = {
    val arr = new Array[Int](nums.length)
    var i = 0
    var j = nums.length - 1
    for (num <- nums) {
      if (num == 0) {
        arr(i) = 0
        i += 1
      }
      if (num == 2) {
        arr(j) = 2
        j -= 1
      }
    }
    for (x <- i to j)
      arr(x) = 1
  }

  /**
    * 三路块排
    *
    * @param nums
    */
  def sortColors3(nums: Array[Int]): Unit = {
    var i, left = 0
    var right = nums.length - 1
    while (i <= right) {
      if (nums(i) < 1) {
        swap(nums, i, left)
        left += 1
        i += 1
      } else if (nums(i) > 1) {
        swap(nums, i, right)
        right -= 1
      } else
        i += 1
    }
  }

  def swap(nums: Array[Int], i: Int, j: Int): Unit = {
    val temp = nums(i)
    nums(i) = nums(j)
    nums(j) = temp
  }


  def main(args: Array[String]): Unit = {
    val arr = Array(2, 0, 2, 1, 1, 0)
    sortColors3(arr)
    arr.foreach(x => print(x + ","))
  }
}

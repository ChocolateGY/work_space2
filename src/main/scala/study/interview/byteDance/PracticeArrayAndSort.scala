package study.interview.byteDance


object PracticeArrayAndSort {
  def main(args: Array[String]): Unit = {
    //    val res = threeSum(Array(1, 1, 1))
    //    res.foreach(x => x.foreach(println))
    println((1 to 10).product)

    println(findLengthOfLCIS(Array(1, 3, 5, 4, 7)))
  }

  //三数之和
  /**
    * 总结：
    * 1、排序数组。
    * 2、锁定指针i，与指针j、k相加，
    * 如果 == target，记录
    * 如果 < target j++
    * 如果 > target k--
    * 注意每次移动指针式，去重
    *
    */
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    val arr = nums.sorted(Ordering.Int)
    val res = scala.collection.mutable.ArrayBuffer[List[Int]]()
    var i = 0
    while (i < arr.length - 2) {
      var j = i + 1
      var k = arr.length - 1
      while (j < k) {
        if (arr(i) + arr(j) + arr(k) == 0) {
          res += List(arr(i), arr(j), arr(k))
          j += 1
          k -= 1
          while (arr(j - 1) == arr(j) && j < k) j += 1
          while (arr(k + 1) == arr(k) && j < k) k -= 1
        } else if (arr(i) + arr(j) + arr(k) < 0) {
          j += 1
          while (arr(j - 1) == arr(j) && j < k) j += 1
        } else {
          k -= 1
          while (arr(k + 1) == arr(k) && j < k) k -= 1
        }
      }

      i += 1
      while (i < arr.length - 2 && arr(i) == arr(i - 1)) i += 1
    }
    res.toList
  }

  /**
    * 岛屿的最大面积
    * 总结：使用备份表来标记这个点是否查看过。使用递归遍历每个点的可能性。 俗称深度优先遍历
    *
    */

  def maxAreaOfIsland(grid: Array[Array[Int]]): Int = {
    var max = 0
    var num = 0
    val tab = Array.fill(grid.length, grid(0).length)(0)
    for (i <- grid.indices; j <- grid(0).indices) {
      num = 0
      count(i, j)
      max = max.max(num)
    }

    def count(i: Int, j: Int): Unit = {
      if (i >= 0 && i < grid.length && j >= 0 && j < grid(0).length) {
        if (tab(i)(j) == 0) {
          tab(i)(j) = 1
          if (grid(i)(j) == 1) {
            num += 1
            count(i, j + 1)
            count(i, j - 1)
            count(i + 1, j)
            count(i - 1, j)
          }
        }
      }
    }

    max
  }

  /**
    * 搜索旋转排序数组
    *
    * 总结，画出所有可能性的排列，便可看出，一分为二后，至少有一半是有序的，
    * 判断target是否在有序部分。是或否直接可以划分区域。
    */
  def search(nums: Array[Int], target: Int): Int = {
    var low = 0
    var high = nums.length - 1
    while (low <= high) {
      val mid = low + (high - low) / 2
      if (target == nums(mid)) return mid
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
    * 判断最长递增序列
    *
    * @param nums
    * @return
    */
  def findLengthOfLCIS(nums: Array[Int]): Int = {
    var count = 0
    if (nums.nonEmpty) {
      var n = 1
      for (i <- 1 until nums.length) {
        if (nums(i) > nums(i - 1)) {
          n += 1
          count = count.max(n)
        } else
          n = 1
      }
      count = count.max(n)
    }
    count
  }

  /**
    * 215、数组中的第k个最大的数
    *
    */
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    nums.sorted(Ordering.Int.reverse)(k - 1)
  }

  /**
    * 使用优先队列
    */
  def findKthLargest2(nums: Array[Int], k: Int): Int = {
    val heap = scala.collection.mutable.PriorityQueue[Int]()(Ordering.Int.reverse)
    for (n <- nums) {
      heap += n
      if (heap.length > k)
        heap.dequeue()
    }
    heap.dequeue()
  }

  def findKthLargest3(nums: Array[Int], k: Int): Int = {

    def quickSelect(left: Int, right: Int, kSmallest: Int): Int = {
      if (left == right)
        return nums(left)
      import scala.util.Random
      val random = new Random()
      var pivotIndex = left + random.nextInt(right - left)
      pivotIndex = partition(left, right, pivotIndex)

      if (kSmallest == pivotIndex) {
        return nums(pivotIndex)
      } else if (kSmallest < pivotIndex) {
        return quickSelect(left, pivotIndex - 1, kSmallest)
      } else
        return quickSelect(pivotIndex + 1, right, kSmallest)
    }

    def partition(left: Int, right: Int, pivotIndex: Int): Int = {
      val pivot = nums(pivotIndex)
      swap(pivotIndex, right)
      var storeLeft = left

      for (i <- left to right) {
        if (nums(i) < pivot) {
          swap(storeLeft, i)
          storeLeft += 1
        }
      }

      swap(storeLeft, right)
      storeLeft
    }

    def swap(a: Int, b: Int): Unit = {
      val temp = nums(a)
      nums(a) = nums(b)
      nums(b) = temp

    }
    val len = nums.length
    quickSelect(0, len - 1, len - k)
  }


  /**
    * 最长连续序列
    */
  def longestConsecutive(nums: Array[Int]): Int = {
    var res = 0
    if (nums.nonEmpty) {
      val set = scala.collection.mutable.Set[Int](nums: _*)
      var pre, last = 0
      for (n <- nums) {
        if (set.remove(n)) {
          pre = n - 1
          while (set.remove(pre)) pre -= 1
          last = n + 1
          while (set.remove(last)) last += 1
        }
        res = res.max(last - pre - 1)
      }
    }
    res
  }

  /**
    * 第k个排序
    *
    * 先将数字放到ListBuffer
    *
    */
  def getPermutation(n: Int, k: Int): String = {
    //所有数字的集合
    val list = scala.collection.mutable.ListBuffer[Int](1 to n: _*)
    val str = new StringBuilder()
    //为了对应下标，第k_ 个顺序的。
    var k_ = k - 1
    //循环n次，拿到每个位置的数字
    for (i <- 1 to n) {
      //(n-i)的阶乘，比如i = 1 ，表示第一位数字，每个数的可能性（123 132）
      val factorial = (1 to (n - i)).product
      val index = k_ / factorial
      str.append(list(index))
      list.remove(index)
      k_ %= factorial
    }
    str.toString
  }

  /**
    * 朋友圈
    *
    * 与岛最大面积稍微不同，这里只用一维来表示每个人是否看过。
    * 深度优先
    */
  def findCircleNum(M: Array[Array[Int]]): Int = {
    //记录每个人是否看过
    val arr = Array.fill(M.length)(0)
    var res = 0
    for (i <- M.indices) {
      //如果碰到这个人没看过，那么就多了一个朋友圈
      if (arr(i) == 0) {
        res += 1
        recursive(i)
      }
    }

    def recursive(i: Int): Unit = {
      arr(i) = 1
      for (j <- M.indices) {
        if (arr(j) == 0 && M(i)(j) == 1) {
          recursive(j)
        }
      }
    }

    res
  }

  /**
    * 合并区间
    *
    */
  def merge(intervals: Array[Array[Int]]): Array[Array[Int]] = {
    import scala.collection.mutable._
    val res = ListBuffer[Array[Int]]()
    if (intervals.nonEmpty) {
      val intervalsSort = intervals.sortBy(_.head)
      var num = Int.MinValue
      var tempArr = ArrayBuffer[Int]()
      for (arr <- intervalsSort) {
        if (num < arr(0)) {
          if (tempArr.nonEmpty) {
            tempArr += num
            res += tempArr.toArray
            tempArr.clear()
          }
          tempArr += arr(0)
          num = arr(1)
        } else
          num = num.max(arr(1))
      }
      tempArr += num
      res += tempArr.toArray
    }
    res.toArray
  }

  /**
    * 接雨水
    */
  def trap(height: Array[Int]): Int = {
    val len = height.length
    var sum = 0
    var max1, max2 = 0
    var p1 = 1
    var p2 = len - 2
    for (i <- 1 to (len - 2)) {
      if (height(p1 - 1) < height(p2 + 1)) {
        max1 = max1.max(height(p1 - 1))
        if (max1 > height(p1))
          sum += (max1 - height(p1))
        p1 += 1
      } else {
        max2 = max2.max(height(p2 + 1))
        if (max2 > height(p2))
          sum += (max2 - height(p2))
        p2 -= 1
      }
    }
    sum
  }
}

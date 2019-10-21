package study.interview.byteDance.ArrayAndSort

import java.util.{Comparator, PriorityQueue}

import scala.util.Random

/**
  * 数组中的第K个最大元素
  * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
  *
  * 示例 1:
  *
  * 输入: [3,2,1,5,6,4] 和 k = 2
  * 输出: 5
  * 示例 2:
  *
  * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
  * 输出: 4
  * 说明:
  *
  * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
  *
  * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode/
  */
object FindKthLargest {


  def main(args: Array[String]): Unit = {
    val arr = Array(2, 1)
    println(findKthLargest4(arr, 2))
    val arr2 = Array(3, 2, 1, 5, 6, 4)
    println(findKthLargest4(arr2, 2))

  }

  /**
    * 自己实现
    * O(NlogN)
    */
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    //    val stack = collection.mutable.Stack[Int]()
    nums.sorted(Ordering.Int.reverse)(k - 1)
  }

  /**
    * 使用堆（优先队列）
    *
    * 思路是创建一个大顶堆，将所有数组中的元素加入堆中，并保持堆的大小小于等于 k。
    *
    * 这样，堆中就保留了前 k 个最大的元素。这样，堆顶的元素就是正确答案。
    *
    * 像大小为 k 的堆中添加元素的时间复杂度为 {O}(\log k)O(logk)，我们将重复该操作 N 次，
    *
    * 故总时间复杂度为 {O}(N \log k)O(Nlogk)。
    *
    *
    */
  def findKthLargest2(nums: Array[Int], k: Int): Int = {
    //创建一个优先队列
    val heap = new PriorityQueue[Int](new Comparator[Int] {
      override def compare(o1: Int, o2: Int): Int = o1 - o2
    })
    //挨个放入，数量保持在<=k
    for (n <- nums) {
      heap.add(n)
      if (heap.size() > k)
        heap.poll
    }
    //获取第k个最大的
    heap.poll
  }

  /**
    * 方法二：快速选择
    * 快速选择算法 的平均时间复杂度为 {O}(N)O(N)。就像快速排序那样，本算法也是 Tony Hoare 发明的，因此也被称为 Hoare选择算法。
    *
    * 本方法大致上与快速排序相同。简便起见，注意到第 k 个最大元素也就是第 N - k 个最小元素，因此可以用第 k 小算法来解决本问题。
    *
    *
    * 首先，我们选择一个枢轴，并在线性时间内定义其在排序数组中的位置。这可以通过 划分算法 的帮助来完成。
    *
    * 为了实现划分，沿着数组移动，将每个元素与枢轴进行比较，并将小于枢轴的所有元素移动到枢轴的左侧。
    *
    * 这样，在输出的数组中，枢轴达到其合适位置。所有小于枢轴的元素都在其左侧，所有大于或等于的元素都在其右侧。
    *
    * 这样，数组就被分成了两部分。如果是快速排序算法，会在这里递归地对两部分进行快速排序，时间复杂度为 {O}(N \log N)O(NlogN)。
    *
    * 而在这里，由于知道要找的第 N - k 小的元素在哪部分中，我们不需要对两部分都做处理，这样就将平均时间复杂度下降到 {O}(N)O(N)。
    *
    * 最终的算法十分直接了当 :
    *
    * 随机选择一个枢轴。
    *
    * 使用划分算法将枢轴放在数组中的合适位置 pos。将小于枢轴的元素移到左边，大于等于枢轴的元素移到右边。
    *
    * 比较 pos 和 N - k 以决定在哪边继续递归处理。
    *
    * ! 注意，本算法也适用于有重复的数组
    *
    * 官方解答
    * 这里是查找第k最小
    *
    */

  def findKthLargest3(nums: Array[Int], k: Int): Int = {

    def quickSelect(left: Int, right: Int, kSmallest: Int): Int = {
      //如果列表只有一个元素 就直接返回
      if (left == right)
        return nums(left)

      //选一个随机的枢纽
      val random = new Random()
      var pivotIndex = left + random.nextInt(right - left)
      //划分算法
      pivotIndex = partition(left, right, pivotIndex)

      //如果枢纽索引就是第k最小的，直接返回
      if (kSmallest == pivotIndex) {
        return nums(pivotIndex)
      } else if (kSmallest < pivotIndex) { //比枢纽小，从左边继续
        return quickSelect(left, pivotIndex - 1, kSmallest)
      } else //比枢纽大，从右边继续
        return quickSelect(pivotIndex + 1, right, kSmallest)
    }

    // 将枢纽小的移动到枢纽左边，剩下比枢纽大的都在枢纽右边
    def partition(left: Int, right: Int, pivotIndex: Int): Int = {
      //注意这里这个取值是由必须要的
      val pivot = nums(pivotIndex)
      //1、将枢纽移到最右
      swap(pivotIndex, right)
      var strore = left //存储指针

      //2、将小于枢纽的数以到左边
      for (i <- left to right) {
        //这里pivot可以换成nums(right)
        if (nums(i) < pivot) {
          swap(strore, i)
          strore += 1
        }
      }
      //3、将枢纽和存储指针交换
      swap(strore, right)
      //返回存储指针
      strore
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
    * 根据快速查找
    * 自己实现
    * 这里是查找第k最大，对比 k -1 即可
    */
  def findKthLargest4(nums: Array[Int], k: Int): Int = {
    def swap(a: Int, b: Int) = {
      val temp = nums(a)
      nums(a) = nums(b)
      nums(b) = temp
    }

    //    val ksmallest = nums.length - k

    def quickSort(left: Int, right: Int): Int = {
      if (left == right) return nums(left)
      //1、开始划分

      //随机枢纽
      val random = new Random()
      var pivot = left + random.nextInt(right - left)
      //将枢纽换到最右
      val pivotValue = nums(pivot)
      swap(pivot, right)
      //小于枢纽的移动到左边
      var store = left
      for (i <- left to right) {
        if (nums(i) > pivotValue) {
          swap(store, i)
          store += 1
        }
      }
      //把枢纽移动到对应位置
      pivot = store
      swap(pivot, right)

      //这里使用k-1
      if (k - 1 == pivot)
        nums(pivot)
      else if (k - 1 < pivot) {
        quickSort(left, pivot - 1)
      } else
        quickSort(pivot + 1, right)
    }

    quickSort(0, nums.length - 1)
  }
}

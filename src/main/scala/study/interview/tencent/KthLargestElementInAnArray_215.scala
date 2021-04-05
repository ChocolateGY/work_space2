package study.interview.tencent

/**
  * 215. 数组中的第K个最大元素
  * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
  *
  * 示例 1:
  *
  * 输入: [3,2,1,5,6,4] 和 k = 2
  * 输出: 5
  * 示例 2:
  *
  * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
  * 输出: 4
  * 说明:
  *
  * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object KthLargestElementInAnArray_215 {

  //https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode/
  //快速查找
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    def swap(a: Int, b: Int): Unit = {
      val temp = nums(a)
      nums(a) = nums(b)
      nums(b) = temp
    }
//将大于pivot值放左边，
    def quickFind(left: Int, right: Int): Int = {
      if (left == right) return nums(left)
      var store = left
      val pivot = nums(right)
      for (i <- left to right) {
        if (nums(i) > pivot) {
          swap(i, store)
          store += 1
        }
      }
      swap(store, right)
      if (store == k - 1) {
        pivot
      } else if (store > k - 1)
        quickFind(left, store-1)
      else
        quickFind(store+1, right)
    }

    quickFind(0, nums.length - 1)
  }

  //使用堆
  def findKthLargest2(nums: Array[Int], k: Int): Int = {
    def max_heapify(n: Int) = {
      for (i <- Range.inclusive((n - 1) / 2, 0, -1)) {
        var child = 2 * i + 1
        if (child != n && nums(child) < nums(child + 1))
          child += 1
        if (nums(i) < nums(child))
          swap(i, child)
      }
    }

    def swap(a: Int, b: Int): Unit = {
      val temp = nums(a)
      nums(a) = nums(b)
      nums(b) = temp
    }

    //这里会出现 < 0 的情况，导致heapify出错
    val last = if (nums.length - 1 - k > 0) nums.length - 1 - k else 0
    for (i <- Range(nums.length - 1, last, -1)) {
      max_heapify(i)
      swap(0, i)
    }
    nums(nums.length - 1 - k)
  }

}

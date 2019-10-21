package study.interview.byteDance

import study.interview.byteDance.ListAndTree.ListNode

object OtherQuestion {
  def main(args: Array[String]): Unit = {

  }

  /**
    * 905、按奇偶排序数组
    * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
    *
    * 简单说就是把偶数放前面，奇数放后面，没有排序
    *
    * https://leetcode-cn.com/problems/sort-array-by-parity/solution/an-qi-ou-pai-xu-shu-zu-by-leetcode/
    * 步骤总结：
    * 使用两个指针，一前一后，i判断是否为奇，j判断是否为偶
    */
  def sortArrayByParity(A: Array[Int]): Array[Int] = {
    var i = 0
    var j = A.length - 1
    while (i < j) {
      if (A(i) % 2 == 1 && A(j) % 2 == 0) {
        val temp = A(i)
        A(i) = A(j)
        A(j) = temp
        i += 1
        j -= 1
      }
      if (A(i) % 2 == 0) i += 1
      if (A(j) % 2 == 1) j -= 1
    }
    A
  }

  /**
    * 6、删除排序数组中的重复项
    *
    * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
    *
    * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
    *
    * 示例 1:
    *
    * 给定数组 nums = [1,1,2],
    *
    * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
    *
    * 你不需要考虑数组中超出新长度后面的元素。
    * 示例 2:
    *
    * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
    *
    * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
    *
    * 你不需要考虑数组中超出新长度后面的元素。
    * 说明:
    *
    * 为什么返回数值是整数，但输出的答案是数组呢?
    *
    * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
    *
    * 你可以想象内部操作如下:
    *
    * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
    * int len = removeDuplicates(nums);
    *
    * // 在函数里修改输入数组对于调用者是可见的。
    * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
    * for (int i = 0; i < len; i++) {
    *     print(nums[i]);
    * }
    *
    * 来源：力扣（LeetCode）
    * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
    *
    *
    * 方法：双指针法
    * 算法
    *
    * 数组完成排序后，我们可以放置两个指针 i 和 j，其中 i 是慢指针，而 j 是快指针。
    *
    * 只要 nums[i]=nums[j]，我们就增加 jj 以跳过重复项。
    *
    * 当我们遇到 nums[j] != nums[i] ，跳过重复项的运行已经结束，
    *
    * 因此我们必须把它（nums[j]）的值复制到 nums[i + 1]。
    *
    * 然后递增 i，接着我们将再次重复相同的过程，直到 j 到达数组的末尾为止。
    *
    * 作者：LeetCode
    * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution/shan-chu-pai-xu-shu-zu-zhong-de-zhong-fu-xiang-by-/
    * 来源：力扣（LeetCode）
    * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    */
  def removeDuplicates(nums: Array[Int]): Int = {
    if (nums == null || nums.nonEmpty) return 0
    var i = 0
    for (j <- 1 until nums.length) {
      if (nums(i) != nums(j)) {
        i += 1
        nums(i) = nums(j)
      }
    }
    i + 1
  }

  /**
    * 7. 整数反转
    * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
    */
  def reverse(x: Int): Int = {
    val flag = if (x > 0) 1 else -1

    var result: Long = 0
    var num = x.abs
    while (num > 0) {
      result = result * 10 + num % 10
      num = num / 10
    }

    result = result * flag
    if (result > Int.MaxValue || result < Int.MinValue)
      0
    else
      result.toInt
  }

  /**
    * 143. 重排链表
    * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
    * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
    *
    * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
    *
    * 示例 1:
    *
    * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
    * 示例 2:
    *
    * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
    *
    * 来源：力扣（LeetCode）
    * 链接：https://leetcode-cn.com/problems/reorder-list
    * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    */
  def reorderList(head: ListNode): Unit = {


  }


  /**
    * 153、寻找旋转排序数组中的最小值
    *
    * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
    *
    * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
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
    *
    * 来源：力扣（LeetCode）
    * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
    * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    *
    * 解答：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/solution/xun-zhao-xuan-zhuan-pai-lie-shu-zu-zhong-de-zui-xi/
    *
    *
    */
  /**
    * 初级方法
    */
  def findMin(nums: Array[Int]): Int = {
    var i = 1
    val len = nums.length
    while (i < len) {
      if (nums(i) < nums(i - 1)) {
        return nums(i)
      }
      i += 1
    }
    nums.head
  }

  /**
    * 官方高级方法
    */
  def findMin2(nums: Array[Int]): Int = {
    if (nums.length == 1)
      return nums(0)

    var left = 0
    var right = nums.length - 1
    if (nums.head < nums.last)
      return nums.head
    while (left <= right) {
      val mid = left + (right - left) / 2
      if (nums(mid + 1) < nums(mid))
        return nums(mid + 1)
      if (nums(mid - 1) > nums(mid))
        return nums(mid)
      if (nums(mid) > nums.head)
        left = mid + 1
      else
        right = mid - 1
    }
    -1

  }

}

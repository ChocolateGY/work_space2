package study.interview.tencent

/**
  * 169. 多数元素
  * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
  *
  * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
  *
  * 示例 1:
  *
  * 输入: [3,2,3]
  * 输出: 3
  * 示例 2:
  *
  * 输入: [2,2,1,1,1,2,2]
  * 输出: 2
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/majority-element
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object MajorityElement_169 {
  //Boyer-Moore 投票算法。1、遍历数组。2、如果count=0 则赋值给res，并且count+1 。如果res=n，则count+1
  //否则count-1.
  //https://leetcode-cn.com/problems/majority-element/solution/qiu-zhong-shu-by-leetcode-2/
  def majorityElement(nums: Array[Int]): Int = {
    var res = 0
    var count = 0
    for (n <- nums) {
      if (count == 0) {
        res = n
        count += 1
      } else if (res == n)
        count += 1
      else count -= 1
    }
    res
  }
}

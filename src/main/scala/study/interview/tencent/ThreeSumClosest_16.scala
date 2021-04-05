package study.interview.tencent

/**
  * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
  *
  * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
  *
  * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/3sum-closest
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object ThreeSumClosest_16 {
  def threeSumClosest(nums: Array[Int], target: Int): Int = {
    val arr = nums.sorted
    var i = 0
    var res = arr(0) + arr(1) + arr(2)
    while (i < nums.length - 2) {
      var j = i + 1
      var k = arr.length - 1
      while (j < k) {
        val sum = arr(i) + arr(j) + arr(k)
        if (Math.abs(target - sum) < Math.abs(target - res))
          res = sum
        if (sum < target)
          j += 1
        else if (sum > target)
          k -= 1
        else
          return sum
      }
      i += 1
    }
    res
  }

}

package study.interview.tencent

/**
  *
  * 15. 三数之和
  * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
  *
  * 注意：答案中不可以包含重复的三元组。
  *
  * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
  *
  * 满足要求的三元组集合为：
  * [
  * [-1, 0, 1],
  * [-1, -1, 2]
  * ]
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/3sum
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object ThreeSum_15 {
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    val arr = nums.sorted
    val res = collection.mutable.ArrayBuffer[List[Int]]()
    var i = 0
    while (i < arr.length - 2) {
      var j = i + 1
      var k = arr.length - 1
      while (j < k) {
        val sum = arr(i) + arr(j) + arr(k)
        if (sum == 0) {
          res += List(arr(i), arr(j), arr(k))
          j += 1
          k -= 1
          while (j < k && arr(j - 1) == arr(j)) j += 1
          while (j < k && arr(k + 1) == arr(k)) k -= 1
        } else if (sum < 0) {
          j += 1
          while (j < k && arr(j - 1) == arr(j)) j += 1
        } else {
          k -= 1
          while (j < k && arr(k + 1) == arr(k)) k -= 1
        }
      }
      i += 1
      while (i < arr.length - 2 && arr(i - 1) == arr(i)) i += 1
    }
    res.toList
  }
}

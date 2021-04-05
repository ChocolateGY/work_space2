package study.interview.tencent

/**
  * 53. 最大子序和
  * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
  *
  * 示例:
  *
  * 输入: [-2,1,-3,4,-1,2,1,-5,4],
  * 输出: 6
  * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
  * 进阶:
  *
  * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/maximum-subarray
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object MaximumSubarray_53 {
  /**
    * 这道题用动态规划的思路并不难解决，比较难的是后文提出的用分治法求解，但由于其不是最优解法，所以先不列出来
    * 动态规划的是首先对数组进行遍历，当前最大连续子序列和为 sum，结果为 ans
    * 如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
    * 如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
    * 每次比较 sum 和 ans的大小，将最大值置为ans，遍历结束返回结果
    * 时间复杂度：O(n)O(n)
    *
    * 作者：guanpengchn
    * 链接：https://leetcode-cn.com/problems/maximum-subarray/solution/hua-jie-suan-fa-53-zui-da-zi-xu-he-by-guanpengchn/
    * 来源：力扣（LeetCode）
    * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    *
    */
    //1、定义结果max，和临时sum。2、遍历数组，判断当前n和sum+n谁大，赋值给sum。判断sum和max，赋值给max。
  def maxSubArray(nums: Array[Int]): Int = {
    var max = Int.MinValue
    var sum = 0
    for (n <- nums) {
      sum = math.max(sum+n,n)
      max = max.max(sum)
    }
    max
  }
}

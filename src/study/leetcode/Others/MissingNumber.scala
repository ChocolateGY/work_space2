package study.leetcode.Others

/**
  * Created by root on 2018-8-7.
  *
  * 缺失数字
  * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
  * *
  * 示例 1:
  * *
  * 输入: [3,0,1]
  * 输出: 2
  * 示例 2:
  * *
  * 输入: [9,6,4,2,3,5,7,0,1]
  * 输出: 8
  * 说明:
  * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
  */
object MissingNumber {

  /**
    * 总和=缺失数组+确实数字
    *
    *
    */
  def missingNumber(nums: Array[Int]): Int = {
    val sum = nums.sum
    var res = 0
    for (i <- 0 to nums.length) {
      res += i
    }
    res - sum
  }

  /**
    * 异或
    * a^a=0 a^b^b=a
    **/
  def missingNumber2(nums: Array[Int]): Int = {
    var res = 0
    for (i <- nums.indices) {
      res ^= (i ^ nums(i))
    }
    res ^= nums.length
    res
  }

  /**
    * 二分查找
    * BinarySearch，排序后，找最后一个满足i==nums[i]
    */
  def missingNumber3(nums: Array[Int]): Int = {
    /*    val arr = nums.sorted
        var r = arr.length
        var l = 0
        if (arr(l) != l)
          return l //特判0，满足前闭后开
        while (r - l > 1) {
          val mid = l + ((r - l) >> 1) //比mid=(l+r)/2 更好，防止l+r 越界
          if (mid == arr(mid))
            l = mid
          else
            r = mid
        }
        l + 1
        */


    val arr = nums.sorted
    var h = arr.length
    var l = 0
    if (arr(l) != l)
      return l
    while (h > l - 1) {
      var mid = (l + h) / 2
      if (arr(mid) != mid)
        h = mid
      else
        l = mid
    }
    l + 1
  }

  def main(args: Array[String]): Unit = {
    println(missingNumber3(Array(0)))
  }
}

package study.leetcode_middle.ArrayAndString

/**
  * Created by root on 2018-8-8.
  * 三数之和
  * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
  * *
  * 注意：答案中不可以包含重复的三元组。
  * *
  * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
  * *
  * 满足要求的三元组集合为：
  * [
  * [-1, 0, 1],
  * [-1, -1, 2]
  * ]
  */
object ThreeSum {

  /**
    * 超时，可能是因为平台原因
    */

  def threeSum(nums: Array[Int]): List[List[Int]] = {
    val list = collection.mutable.ListBuffer[List[Int]]()
    if (nums != null && nums.length > 2) {
      var i = 0
      val arr = nums.sorted
      while (i < nums.length - 2) {
        var j = i + 1
        var k = nums.length - 1
        while (j < k) {
          if (arr(i) + arr(j) + arr(k) == 0) {
            list += List(arr(i), arr(j), arr(k))
            k -= 1
            j += 1
            while (j < k && arr(j) == arr(j - 1))
              j += 1
            while (j < k && arr(k) == arr(k + 1))
              k -= 1
          } else if (arr(i) + arr(j) + arr(k) > 0) {
            k -= 1
            while (j < k && arr(k) == arr(k + 1))
              k -= 1
          } else {
            j += 1
            while (j < k && arr(j) == arr(j - 1))
              j += 1
          }

        }
        i += 1
        while (i < arr.length - 2 && arr(i) == arr(i - 1))
          i += 1
      }
    }
    list.toList
  }

  /**
    * 超时
    */
  def threeSum2(nums: Array[Int]): List[List[Int]] = {
    val list = collection.mutable.ListBuffer[List[Int]]()
    if (nums != null && nums.length > 2) {
      val arr = nums.sorted
      var i = 0
      while (i < arr.length - 2) {
        var j = i + 1
        var k = arr.length - 1
        while (j < k) {
          if (arr(j) + arr(k) == -arr(i)) {
            list += List(arr(i), arr(j), arr(k))
            k -= 1
            j += 1
            while (j < k && arr(j) == arr(j - 1))
              j += 1
            while (j < k && arr(k) == arr(k + 1))
              k -= 1
          } else if (arr(j) + arr(k) > -arr(i)) {
            k -= 1
            while (j < k && arr(k) == arr(k + 1))
              k -= 1
          } else {
            j += 1
            while (j < k && arr(j) == arr(j - 1))
              j += 1
          }
        }
        i += 1
        while (i < arr.length - 2 && arr(i) == arr(i - 1))
          i += 1
      }
    }
    list.toList
  }

  /**
    * 这样子会有重复元素算进去。所以得先排序并跳过相同的元素
    */
  def threeSum3(nums: Array[Int]): List[List[Int]] = {

    val set = collection.mutable.Set[List[Int]]()
    for (i <- nums.indices) {
      for (j <- i + 1 until nums.size) {
        for (k <- j + 1 until nums.size) {
          if (nums(i) + nums(j) + nums(k) == 0)
            set += List(nums(i), nums(j), nums(k)).sorted
        }
      }
    }
    set.toList
  }

  def main(args: Array[String]): Unit = {
    threeSum(Array(-1, 0, 1, 2, -1, -4)).foreach{
      x=>println(x.toString)
    }
  }
}

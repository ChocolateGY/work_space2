package study.interview.byteDance.ArrayAndSort


/**
  * 三数之和
  * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
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
  */
object ThreeSum {
  def main(args: Array[String]): Unit = {
    val arr = Array(-1, 0, 1, 2, -1, -4)
    val res = threeSum2(arr)
    res.foreach {
      x =>
        x.foreach(y => print(y + ","))
        println()
    }

  }

  /**
    * 暴力
    * 超时
    *
    * @param nums
    * @return
    */
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    import scala.collection.mutable.ListBuffer

    val res = ListBuffer[List[Int]]()
    for (i <- nums.indices) {
      for (j <- i + 1 until nums.length) {
        for (k <- j + 1 until nums.length) {
          if (nums(i) + nums(j) + nums(k) == 0)
            res += List(nums(i), nums(j), nums(k))
        }
      }
    }
    res.toList
  }

  /**
    * 先排序，然后通过记录索引完成
    * 平台问题，还是超时，java代码不会。
    * https://www.cnblogs.com/liuliu5151/p/9124596.html
    *
    * @param nums
    * @return
    */
  def threeSum2(nums: Array[Int]): List[List[Int]] = {
    import scala.collection.mutable.ListBuffer
    val res = ListBuffer[List[Int]]()
    val arr = nums.sorted(Ordering.Int)
    var i = 0
    while (i < arr.length - 2) {

      var j = i + 1
      var k = arr.length - 1
      while (j < k) {
        if (arr(i) + arr(j) + arr(k) == 0) {
          res += List(arr(i), arr(j), arr(k))
          j += 1
          k -= 1
          while (j < k && arr(j) == arr(j - 1)) j += 1
          while (j < k && arr(k) == arr(k + 1)) k -= 1
        } else if (arr(i) + arr(j) + arr(k) < 0) {
          j += 1
          while (j < k && arr(j) == arr(j - 1)) j += 1
        } else {
          k -= 1
          while (j < k && arr(k) == arr(k + 1)) k -= 1
        }

      }
      i += 1
      while (i < arr.length - 2 && arr(i) == arr(i - 1)) i += 1
    }
    res.toList
  }


}

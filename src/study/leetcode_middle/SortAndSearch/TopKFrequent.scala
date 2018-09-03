package study.leetcode_middle.SortAndSearch

/**
  * Created by root on 2018-8-31.
  *
  * 前K个高频元素
  * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
  * *
  * 示例 1:
  * *
  * 输入: nums = [1,1,1,2,2,3], k = 2
  * 输出: [1,2]
  * 示例 2:
  * *
  * 输入: nums = [1], k = 1
  * 输出: [1]
  * 说明：
  * *
  * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
  * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
  */
object TopKFrequent {
  /**
    * 编译超时，网上的解答和此思路类似
    *
    * @param nums
    * @param k
    * @return
    */
  def topKFrequent(nums: Array[Int], k: Int): List[Int] = {
    val map = collection.mutable.Map[Int, Int]()
    nums.foreach {
      n =>
        val count = map.getOrElse(n, 0)
        map += n -> (count + 1)
    }
    val list = collection.mutable.ArrayBuffer[Int]()
    for (i <- 1 to k) {
      val key = map.maxBy(_._2)._1
      list += key
      map -= key
    }
    list.toList
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(1, 1, 1, 2, 2, 3)
    val arr2 = Array(1)
    topKFrequent(arr2, 1).foreach(x => print(x + ","))

  }
}

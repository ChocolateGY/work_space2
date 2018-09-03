package study.leetcode_middle.BacktrackingMethod

/**
  * Created by root on 2018-8-30.
  * 子集
  * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
  * *
  * 说明：解集不能包含重复的子集。
  * *
  * 示例:
  * *
  * 输入: nums = [1,2,3]
  * 输出:
  * [
  * [3],
  * [1],
  * [2],
  * [1,2,3],
  * [1,3],
  * [2,3],
  * [1,2],
  * []
  * ]
  */
object Subsets {
  def subsets(nums: Array[Int]): List[List[Int]] = {
    val res = collection.mutable.ArrayBuffer[List[Int]]()
    res += List[Int]()
    for(num<-nums){
      val size = res.size
      for(i<-0 until size){
        val temp = res(i).toBuffer
        temp += num
        res += temp.toList
      }
    }
    res.toList
  }

}

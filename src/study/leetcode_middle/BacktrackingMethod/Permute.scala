package study.leetcode_middle.BacktrackingMethod

/**
  * Created by root on 2018-8-29.
  *
  * 全排列
  *
  * 给定一个没有重复数字的序列，返回其所有可能的全排列。
  * *
  * 示例:
  * *
  * 输入: [1,2,3]
  * 输出:
  * [
  * [1,2,3],
  * [1,3,2],
  * [2,1,3],
  * [2,3,1],
  * [3,1,2],
  * [3,2,1]
  * ]
  */
object Permute {

  /**
    * java 方法通过
    * https://blog.csdn.net/jacky_chenjp/article/details/66477538
    * scala 报错
    */
  val res = collection.mutable.ArrayBuffer[List[Int]]()

  def permute(nums: Array[Int]): List[List[Int]] = {
    if (nums == null || nums.length == 0) {
      res.toList
    } else {
      exchange(nums, 0, nums.length)
      res.toList
    }


  }

  def exchange(nums: Array[Int], i: Int, len: Int): Unit = {
    if (i == len - 1) {
      res += nums.toList
    }
    for (j <- i until len) {
      swap(nums, i, j)
      exchange(nums, i + 1, len)
      swap(nums, i, j)
    }
  }

  def swap(nums: Array[Int], i: Int, j: Int): Unit = {
    val temp = nums(i)
    nums(i) = nums(j)
    nums(j) = temp
  }

  /**
    * 主要还是难在如何处理好下层回溯到上层，该题中本质实际上就是相当于三层嵌套循环，分别从外到内决定输出子数组中的第1到最后1位，并且标记重复字符。
    * *
    * 处理流程实际上就是
    * *
    * 从第一层开始遍历，i1=0，”1”
    * 进入到第二层，i2=1，”12”（第二层中第1个未使用的字符1，相较于第一层而言）
    * 进入到第三层，i3=2，”123”（第三层唯一未使用的字符3，当于前两层而言+）
    * 回退到第二层，i2=2，”13“（第二层中第2个未使用的字符2，相较于第一层而言）
    * 进入到第三层，i3=1，”132“（第三层唯一未使用的字符2，相当于前两层而言）
    * 回退到第二层，回退到第一层，i1=2，”2“
    * 。。。此后同上
    *
    * @param nums
    * @return
    */

  def permute2(nums: Array[Int]): List[List[Int]] = {
    val list = collection.mutable.ArrayBuffer[List[Int]]()
    bracktrack(list, collection.mutable.ArrayBuffer[Int](), nums)
    list.toList
  }

  def bracktrack(list: collection.mutable.ArrayBuffer[List[Int]], temp: collection.mutable.ArrayBuffer[Int], nums: Array[Int]): Unit = {
    if (temp.size == nums.length) {
      list += temp.toList
    } else {
      for (i <- nums if !temp.contains(i)) {
        temp += i
        bracktrack(list, temp, nums)
        temp -= temp.last
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(1, 2, 3)
    val list = permute2(arr)
  }
}

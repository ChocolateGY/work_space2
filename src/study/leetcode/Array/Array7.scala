package study.leetcode.Array

/**
  * 加一
  * 给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。
  * *
  * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
  * *
  * 你可以假设除了整数 0 之外，这个整数不会以零开头。
  * *
  * 示例 1:
  * *
  * 输入: [1,2,3]
  * 输出: [1,2,4]
  * 解释: 输入数组表示数字 123。
  * 示例 2:
  * *
  * 输入: [4,3,2,1]
  * 输出: [4,3,2,2]
  * 解释: 输入数组表示数字 4321。
  *
  * Created by root on 2018-6-1.
  */
object Array7 {


  def plusOne(digits: Array[Int]): Array[Int] = {
    val arr = new collection.mutable.ArrayBuffer[Int]()
    var flag = true
    for (i <- digits.indices.reverse if flag) {
      digits(i) = (digits(i) + 1) % 10
      if (digits(i) == 0)
        flag = true
      else
        flag = false
    }
    if (flag) {
      arr += 1
    }
    for (i <- digits)
      arr += i
    arr.toArray
  }

  def main(args: Array[String]): Unit = {
    val nums1 = Array[Int](9, 9, 9, 9)
//    plusOne(nums1).foreach(println)
    val a = 10 to(0,-1)
    a.foreach(println)
  }
}

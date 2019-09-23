package study.interview.byteDance.String

/**
  * 字符串的排列
  * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
  *
  * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
  *
  * 示例1:
  *
  * 输入: s1 = "ab" s2 = "eidbaooo"
  * 输出: True
  * 解释: s2 包含 s1 的排列之一 ("ba").
  *
  *
  * 示例2:
  *
  * 输入: s1= "ab" s2 = "eidboaoo"
  * 输出: False
  *
  *
  * 注意：
  *
  * 输入的字符串只包含小写字母
  * 两个字符串的长度都在 [1, 10,000] 之间
  *
  *
  * solution
  *
  * https://www.cnblogs.com/MrSaver/p/9638279.html
  */
object CheckInclusion {
  def main(args: Array[String]): Unit = {
    //    val arr = Array(1,2,3)
    //    val it1=  arr.permutations
    //    val it2 = arr.combinations(2)
    //    for(i<-it1)
    //      println(i.mkString(","))
    //    for(i<-it2)
    //      println(i.mkString(","))
    val s1 = "ab"
    val s2 = "eidbaooo"
    val s3 = "ab"
    val s4 = "eidboaoo"
    println(checkInclusion(s1, s2))
    println(checkInclusion(s3, s4))

    /**
      * 判断两个数组是否相等
      */
    val arr1 = Array('1', '2')
    val arr2 = Array('1', '2')
    println(arr1.equals(arr2))

    println(checkInclusion2(s1, s2))
  }

  /**
    * 超时
    */
  def checkInclusion(s1: String, s2: String): Boolean = {
    val it = s1.permutations
    var flag = false
    for (i <- it if s2.contains(i))
      flag = true
    flag
  }

  /**
    * 滑动窗口原理
    *
    * @param s1
    * @param s2
    * @return
    */
  def checkInclusion2(s1: String, s2: String): Boolean = {
    val l1 = s1.size
    val arr1 = new Array[Int](26)
    val arr2 = new Array[Int](26)
    s1.foreach(s => arr1(s - 'a') += 1)
    for (i <- s2.indices) {
      if (i >= l1)
        arr2(s2(i-l1) - 'a') -= 1
      arr2(s2(i) - 'a') += 1
      var flag = true
      for (n <- arr1.indices if arr1(n) != arr2(n))
        flag = false
      if(flag)
        return true
    }
    false
  }
}

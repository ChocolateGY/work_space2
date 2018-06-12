package study.leetcode.String

/**
  * 有效的字母异位词
  * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
  * *
  * 示例 1:
  * *
  * 输入: s = "anagram", t = "nagaram"
  * 输出: true
  * 示例 2:
  * *
  * 输入: s = "rat", t = "car"
  * 输出: false
  * 说明:
  * 你可以假设字符串只包含小写字母。
  * *
  * 进阶:
  * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
  *
  *
  * Created by root on 2018-6-4.
  */
object String4 {
  /**
    *
    * 输入：
    * "ac"
    * "bb"
    * 输出：
    * true
    * 预期：
    * false
    *
    * @param s
    * @param t
    * @return
    *
    * 利用hashcode相加得出是否相等。
    */
  def isAnagram(s: String, t: String): Boolean = {
    var unicodeS = 0
    var unicodeT = 0
    for (i <- s) {
      unicodeS += i.hashCode()
    }
    for (j <- t) {
      unicodeT += j.hashCode
    }
    unicodeS == unicodeT
  }

  /**
    * 用了hash的方法
    *
    * @param s
    * @param t
    * @return
    */
  def isAnagram2(s: String, t: String): Boolean = {
    val mapS = collection.mutable.Map[Char, Int]()
    val mapT = collection.mutable.Map[Char, Int]()
    for (i <- s) {
      val temp = mapS.getOrElse(i, 0) + 1
      mapS += (i -> temp)
    }
    for (i <- t) {
      val temp = mapT.getOrElse(i, 0) + 1
      mapT += (i -> temp)
    }
    mapS == mapT
  }

  /**
    * 使用数组的方法
    *
    * @param s
    * @param t
    * @return
    */
  def isAnagram3(s: String, t: String): Boolean = {
    val arrS = new Array[Int](25)
    val arrT = new Array[Int](25)
    for (i <- s) {
      val temp = i - 'a'
      arrS(temp) += 1
    }
    for (i <- t) {
      val temp = i - 'a'
      arrT(temp) += 1
    }
    arrS.sameElements(arrT) //判断两个序列是否顺序和对应位置上的元素都一样
  }


  def main(args: Array[String]): Unit = {
    val s = "anagram"
    val t = "nagaram"
    println(isAnagram3(s, t))
    //    println("a".hashCode)
    //    println("n".hashCode)
    println('a' - 'a')
    println('n' - 'a')
  }

}

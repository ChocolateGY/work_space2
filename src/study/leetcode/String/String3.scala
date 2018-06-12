package study.leetcode.String

import scala.collection.mutable.ArrayBuffer

/**
  *
  * 字符串中的第一个唯一字符
  * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
  * *
  * 案例:
  * *
  * s = "leetcode"
  * 返回 0.
  * *
  * s = "loveleetcode",
  * 返回 2.
  * *
  *
  * 注意事项：您可以假定该字符串只包含小写字母。
  *
  * Created by root on 2018-6-3.
  */
object String3 {
  /**
    * 超时
    *
    * @param s
    * @return
    */
  def firstUniqChar(s: String): Int = {
    var flag = true
    var index = 0
    for (i <- s.indices if flag) {
      var x = 0
      for (j <- s.indices) {
        if (s(i) == s(j) && i != j)
          x += 1
      }
      if (x == 0) {
        index = i
        flag = false
      }
    }
    if (flag)
      -1
    else
      index
  }

  /**
    * 通过。。。
    * @param s
    * @return
    */
  def firstUniqChar2(s: String): Int = {
    val map = collection.mutable.Map[Char, Int]()
    for (ch <- s) {
      val temp = map.getOrElse(ch, 0) + 1
      map += (ch -> temp)
    }
    var index = -1
    for (m <- s.indices if index == -1) {
      if (map(s(m)) == 1)
        index = m
    }
    index
  }
}

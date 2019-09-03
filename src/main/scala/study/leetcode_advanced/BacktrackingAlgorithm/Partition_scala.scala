package study.leetcode_advanced.BacktrackingAlgorithm

import scala.collection.mutable.ListBuffer

object Partition_scala {
  def partition(s: String): List[List[String]] = {
    if (s != null && s.nonEmpty) {
      val res = ListBuffer[List[String]]()
      backtracking(res, s, ListBuffer[String]())
      println()
      res.map(_.toList).toList
    }
    else
      List[List[String]]()
  }

  def backtracking(res: ListBuffer[List[String]], s: String, combine: ListBuffer[String]): Unit = {
    if (s.isEmpty) {
      res +=  combine.toList
      return
    }

    for (i <- s.indices) {
      if (isPalindrome(s.substring(0, i + 1))) {
        combine += s.substring(0, i + 1)
        backtracking(res, s.substring(i + 1), combine)
        combine.remove(combine.length - 1)
      }
    }
  }

  def isPalindrome(str: String): Boolean = {
    var start = 0
    var end = str.length - 1
    while (start < end) {
      if (str(start) != str(end))
        return false
      start += 1
      end -= 1
    }
    true
  }

  def main(args: Array[String]): Unit = {
    val s = "aab"
    partition(s).foreach{
      x=>
        x.foreach{
          y=>
            print(y+" ")
        }
        println()
    }
  }
}

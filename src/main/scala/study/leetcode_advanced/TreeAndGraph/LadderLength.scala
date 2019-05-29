package study.leetcode_advanced.TreeAndGraph

import study.DataStructuresAndAlgorithmAnalysisInJava.Graph.WordLadders

import collection.JavaConversions._
import collection.JavaConversions.bufferAsJavaList
import scala.collection.mutable._

/**
  * 单词接龙
  * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
  *
  * 每次转换只能改变一个字母。
  * 转换过程中的中间单词必须是字典中的单词。
  * 说明:
  *
  * 如果不存在这样的转换序列，返回 0。
  * 所有单词具有相同的长度。
  * 所有单词只由小写字母组成。
  * 字典中不存在重复的单词。
  * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
  * 示例 1:
  *
  * 输入:
  * beginWord = "hit",
  * endWord = "cog",
  * wordList = ["hot","dot","dog","lot","log","cog"]
  *
  * 输出: 5
  *
  * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
  * 返回它的长度 5。
  * 示例 2:
  *
  * 输入:
  * beginWord = "hit"
  * endWord = "cog"
  * wordList = ["hot","dot","dog","lot","log"]
  *
  * 输出: 0
  *
  * 解释: endWord "cog" 不在字典中，所以无法进行转换。
  */
object LadderLength {
  def ladderLength(beginWord: String, endWord: String, wordList: List[String]): Int = {
    if (wordList == null || wordList.isEmpty) return 0
    val start = Set(beginWord)
    val end = Set(endWord)
    val dic = wordList ++: Set[String]()
    if (dic.contains(endWord))
      bfs(start, end, dic, 2)
    else
      0
  }

  def bfs(st: Set[String], ed: Set[String], dic: Set[String], l: Int): Int = {
    if (st.isEmpty) return 0
    if (st.size > ed.size)
      return bfs(ed, st, dic, l)

    dic --= st

    val next = Set[String]()
    for (s <- st) {
      val arr = s.toCharArray
      for (i <- arr.indices) {
        val temp = arr(i)
        for (c <- 'a' to 'z') {
          if (temp != c) {
            arr(i) = c
            val nstr = new String(arr)
            if (dic.contains(nstr))
              if (ed.contains(nstr))
                return l
              else
                next += nstr
          }
        }
        arr(i) = temp
      }
    }
    bfs(next, ed, dic, l + 1)

  }

  def main(args: Array[String]): Unit = {
    val list = List("hit", "hot", "dot", "dog", "lot", "log", "cog")
    val map = collection.mutable.Map[String, collection.mutable.ListBuffer[String]]()
    for (i <- 0 until list.length - 1; j <- i + 1 until list.length) {
      var count = 0
      for (k <- list(i).indices)
        if (list(i)(k) != list(j)(k))
          count += 1
      if (count == 1) {
        map += (list(i) -> (map.getOrElse(list(i), ListBuffer[String]()) += list(j)))
        map += (list(j) -> (map.getOrElse(list(j), ListBuffer[String]()) += list(i)))
      }
    }
    val jmap = map.mapValues(bufferAsJavaList)

    /**
      * val para :java.util.Map[String,java.util.List[String]] = collection.mutable.Map[String,Buffer[String]]()
      * 测试：JavaConversions不支持嵌套集合的转换。
      */
    val time1 = System.currentTimeMillis()
    val res = WordLadders.findChain(mapAsJavaMap(jmap), "hit", "cog")
    val time2 = System.currentTimeMillis()
    println("time2-1:" + (time2 - time1))
    res.foreach(println)

    val time3 = System.currentTimeMillis()
    val res2 = LadderLength_java.ladderLength3("hit", "cog", list)
    val time4 = System.currentTimeMillis()
    println("time4-3:" + (time4 - time3))

    println(ladderLength("hit", "cog", list))
//    for (c <- 'a' to 'z')
//      print(c)
//    println(Array('a','b').toString)
  }
}

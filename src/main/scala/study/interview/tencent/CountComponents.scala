package study.interview.tencent

/**
  * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
  *
  * Example 1:
  *
  * 0          3
  * |          |
  * 1 --- 2    4
  * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
  *
  * Example 2:
  *
  * 0           4
  * |           |
  * 1 --- 2 --- 3
  * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
  *
  * Note:
  * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
  * ————————————————
  * 版权声明：本文为CSDN博主「sundawei2016」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
  * 原文链接：https://blog.csdn.net/sundawei2016/article/details/71268453
  */
object CountComponents {

  /**
    * 题目要我们找到一个graph 里 互相连接的component 的个数
    *
    * 1、使用 adjacent list 来表示一个图
    * 2、遍历每个 节点 从1到N
    *
    * 3、DFS (深度优先搜索)， 用一个boolean array 来记录访问的节点
    *
    */

  import scala.collection.mutable.ArrayBuffer

  def countComponents(n: Int, edges: Array[Array[Int]]): Int = {
    val adjacencyList = Array.fill[ArrayBuffer[Int]](n)(ArrayBuffer[Int]())
    for (edge <- edges) {
      adjacencyList(edge(0)) += edge(1)
      adjacencyList(edge(1)) += edge(0)
    }
    val visited = Array.fill[Boolean](n)(false)
    var count = 0
    for (i <- 0 until n) {
      if (!visited(i)) {
        count += 1
        dfs(i, visited, adjacencyList)
      }
    }

    count


  }

  def dfs(i: Int, visited: Array[Boolean], adjacencyList: Array[ArrayBuffer[Int]]): Unit = {
    visited(i) = true
    for (v <- adjacencyList(i)) {
      if (!visited(v))
        dfs(v, visited, adjacencyList)
    }
  }

  def main(args: Array[String]): Unit = {
    val arr1 = Array(Array(0, 1), Array(1, 2), Array(3, 4))
    val arr2 = Array(Array(0, 1), Array(1, 2), Array(2, 3), Array(3, 4))
    println(countComponents(5, arr1))
    println(countComponents(5, arr2))
  }
}

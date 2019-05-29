package study.leetcode_advanced.LinkedList

import scala.collection.mutable.ArrayBuffer

/**
  * 链表排序
  * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
  *
  * 示例 1:
  *
  * 输入: 4->2->1->3
  * 输出: 1->2->3->4
  * 示例 2:
  *
  * 输入: -1->5->3->4->0
  * 输出: -1->0->3->4->5
  */
object SortList {

  import scala.collection.mutable.ArrayBuffer

  def sortList(head: ListNode): ListNode = {
    if (head == null) return null
    val arr = ArrayBuffer[Int]()
    var cur = head
    while (cur != null) {
      arr += cur.`val`
      cur = cur.next
    }
    val res = quickSAort(arr)
    val resNode = new ListNode(res(0))
    var newCur = resNode
    for (i <- 1 until res.length) {
      val node = new ListNode(res(i))
      newCur.next = node
      newCur = newCur.next
    }
    resNode
  }

  def quickSAort(a: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    if (a.length < 2)
      a
    else quickSAort(a.filter(_ < a.head)) ++ a.filter(_ == a.head) ++ quickSAort(a.filter(_ > a.head))
  }

  def main(args: Array[String]): Unit = {
    val a = quickSAort(ArrayBuffer(6, 5, 4, 3, 2, 1))
    a.foreach(print)
    val res = sortList(new ListNode(1))
    println(res.`val`)
  }
}

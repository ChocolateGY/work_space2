package study.interview.byteDance.ListAndTree

/**
  * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
  * 排序链表
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


/**
  * Definition for singly-linked list.
  * class ListNode(var _x: Int = 0) {
  * var next: ListNode = null
  * var x: Int = _x
  * }
  */
object SortList {

  import scala.collection.mutable.ArrayBuffer

  def main(args: Array[String]): Unit = {
    val l1 = new ListNode(5)
    val l2 = new ListNode(4)
    val l3 = new ListNode(6)
    val l4 = new ListNode(2)
    val l5 = new ListNode(1)
    l1.next = l2
    l2.next = l3
    l3.next = l4
    l4.next = l5
    var res = sortList(l1)
    while (res != null) {
      println(s"res.x = ${res.x}")
      res = res.next
    }

  }

  def sortList(head: ListNode): ListNode = {
    val arr = ArrayBuffer[Int]()
    var curNode = head
    while (curNode != null) {
      arr += curNode.x
      curNode = curNode.next
    }
    val resArr = quickSort(arr)
    val resHead = new ListNode(0)
    var resCur = resHead
    resArr.foreach {
      x =>
        val node = new ListNode(x)
        resCur.next = node
        resCur = node
    }
    resHead.next
  }

  /**
    * scala 的快速排序
    *
    */
  def quickSort(arr: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    if (arr.length < 2)
      arr
    else
      quickSort(arr.filter(_ < arr.head)) ++ arr.filter(_ == arr.head) ++ quickSort(arr.filter(_ > arr.head))
  }

}

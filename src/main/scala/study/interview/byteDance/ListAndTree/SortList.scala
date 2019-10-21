package study.interview.byteDance.ListAndTree

import study.interview.byteDance.PracticeListAndTree.sortList

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

  /**
    * 正确解法，归并排序，这个是自上而下的
    *
    *
    */
  def sortList2(head: ListNode): ListNode = {
    //安全判断
    if (head == null || head.next == null) {
      return head
    }
    //创建结果node
    val res = new ListNode(0)
    //创建快、慢指针
    var slow = head
    var fast = head.next
    //找到中点
    while (fast != null && fast.next != null) {
      slow = slow.next
      fast = fast.next.next
    }
    //分左右两列，把左列的末尾断开
    val temp = slow.next
    slow.next = null
    //递归循环
    var left = sortList(head)
    var right = sortList(temp)

    //创建临时节点
    var cur = res
    //本地归并
    while (left != null && right != null) {
      if (left.x < right.x) {
        cur.next = left
        left = left.next
      } else {
        cur.next = right
        right = right.next
      }
      cur = cur.next
    }
    if (left != null) cur.next = left
    if (right != null) cur.next = right
    //返回结果
    res.next
  }
}

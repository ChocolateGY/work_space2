package study.RecursivePrinciple

import study.interview.prepare.List.ListNode

/**
  * 206. 反转链表
  * 反转一个单链表。
  *
  * 示例:
  *
  * 输入: 1->2->3->4->5->NULL
  * 输出: 5->4->3->2->1->NULL
  * 进阶:
  * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
  */
object ReverseList_206 {
  //递归
  def reverseList(head: ListNode): ListNode = {
    if (head == null || head.next == null)
      head
    else {
      val last = reverseList(head.next)
      head.next.next = head
      head.next = null
      last
    }
  }

  //迭代
  def reverseList2(head: ListNode): ListNode = {
    if (head != null) {
      var cur = head
      var pre = head
      var next = cur.next //next 依赖 cur
      while (next != null) {
        next = cur.next
        cur.next = pre
        pre = cur
        cur = next
      }
      head.next = null
      pre
    } else
      head
  }

  def main(args: Array[String]): Unit = {

    val l1 = new ListNode(1)
    val l2 = new ListNode(2)
    val l3 = new ListNode(3)
    val l4 = new ListNode(4)
    val l5 = new ListNode(5)
    l1.next = l2
    l2.next = l3
    l3.next = l4
    l4.next = l5
    var res = reverseList2(l1)
    while (res != null) {
      println(res.x)
      res = res.next
    }
  }
}

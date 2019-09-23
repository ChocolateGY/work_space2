import study.interview.byteDance.ListAndTree.ListNode

/**
  * 合并两个有序链表
  * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
  *
  * 示例：
  *
  * 输入：1->2->4, 1->3->4
  * 输出：1->1->2->3->4->4
  */
object MergeTwoLists {
  def main(args: Array[String]): Unit = {
    val l11 = new ListNode(1)
    val l12 = new ListNode(2)
    val l13 = new ListNode(4)
    l11.next = l12
    l12.next = l13

    val l21 = new ListNode(1)
    val l22 = new ListNode(3)
    val l23 = new ListNode(4)
    l21.next = l22
    l22.next = l23

    var res = mergeTwoLists2(l11, l21)
    while (res != null) {
      println(res.x)
      res = res.next
    }
  }

  def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {
    var l1list = l1
    var l2list = l2
    var head: ListNode = null
    var cur: ListNode = null
    while (l1list != null && l2list != null) {
      if (l1list.x < l2list.x) {
        if (cur == null) {
          cur = l1list
          head = cur
        }
        else {
          cur.next = l1list
          cur = cur.next
        }
        l1list = l1list.next
      } else {
        if (cur == null) {
          cur = l2list
          head = cur
        }
        else {
          cur.next = l2list
          cur = cur.next
        }
        l2list = l2list.next
      }

    }
    if (l1list == null && l2list != null) cur.next = l2list
    if (l1list != null && l2list == null) cur.next = l1list
    head
  }

  /**
    * 优化 mergeTwoLists 处理 l1、l2 为空的情况。
    * 头节点可以随意new 一个，最后输出 head.next
    *
    * @param l1
    * @param l2
    * @return
    */
  def mergeTwoLists2(l1: ListNode, l2: ListNode): ListNode = {
    var l1list = l1
    var l2list = l2
    val head: ListNode = new ListNode(0)
    var cur: ListNode = head

    while (l1list != null && l2list != null) {
      if (l1list.x < l2list.x) {
        cur.next = l1list
        l1list = l1list.next
      } else {
        cur.next = l2list
        l2list = l2list.next
      }
      cur = cur.next

    }
    if (l1list == null && l2list != null) cur.next = l2list
    if (l1list != null && l2list == null) cur.next = l1list
    head.next
  }
}

/**
  * Definition for singly-linked list.
  * class ListNode(var _x: Int = 0) {
  * var next: ListNode = null
  * var x: Int = _x
  * }
  */

package study.interview.byteDance

import study.interview.byteDance.ListAndTree.ListNode

object PracticeListAndTree {
  def main(args: Array[String]): Unit = {

    val n1 = new ListNode(4)
    val n2 = new ListNode(1)
    val n3 = new ListNode(3)
    val n4 = new ListNode(2)
    n1.next = n2
    n2.next = n3
    n3.next = n4
    var res = sortList(n1)
    while (res != null) {
      println(res.x)
      res = res.next
    }
  }

  /**
    * 合并两个有序列表
    *
    * easy to me
    *
    */
  def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {
    var t1 = l1
    var t2 = l2
    var frist: ListNode = new ListNode(0)
    val temp = frist
    while (t1 != null && t2 != null) {
      if (t1.x < t2.x) {
        frist.next = t1
        t1 = t1.next
      } else {
        frist.next = t2
        t2 = t2.next
      }
      frist = frist.next
    }
    if (t1 != null) frist.next = t1
    if (t2 != null) frist.next = t2
    temp.next
  }

  /**
    * 反转链表
    *
    * easy to me
    */
  def reverseList(head: ListNode): ListNode = {
    def recursive(node: ListNode): ListNode = {
      if (node.next != null) {
        val last = node.next
        val head = recursive(node.next)
        last.next = node
        node.next = null
        head
      } else
        node
    }

    if (head == null)
      null
    else
      recursive(head)
  }

  /**
    * 两数相加
    */
  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    val head = l1
    var cur = l1
    var t1 = l1
    var t2 = l2
    var carry = 0
    while (t1 != null && t2 != null) {
      cur = t1
      val num = t1.x + t2.x + carry
      carry = num / 10
      cur.x = num % 10
      t1 = t1.next
      t2 = t2.next
    }
    while (t1 != null) {
      cur.next = t1
      cur = t1
      val num = t1.x + carry
      carry = num / 10
      cur.x = num % 10
      t1 = t1.next
    }
    while (t2 != null) {
      cur.next = t2
      cur = t2
      val num = t2.x + carry
      carry = num / 10
      cur.x = num % 10
      t2 = t2.next
    }
    if (carry != 0)
      cur.next = new ListNode(carry)
    head
  }

  /**
    * 链表排序
    * * 递归  归并排序
    * * 步骤：1、用快慢指针分离。2、左右有序链表合并
    */

  def sortList(head: ListNode): ListNode = {
    //初始判断
    if (head == null || head.next == null) {
      return head
    }
    val res = new ListNode(0)
    var slow = head
    var fast = head.next
    //这里判断fast
    while (fast != null && fast.next != null) {
      slow = slow.next
      fast = fast.next.next
    }
    val temp = slow.next
    //这里注意断开
    slow.next = null
    var left = sortList(head)
    var right = sortList(temp)

    var cur = res
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
    res.next
  }
}

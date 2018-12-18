package study.leetcode_primary.List

/**
  *
  * 反转一个单链表。
  * *
  * 示例:
  * *
  * 输入: 1->2->3->4->5->NULL
  * 输出: 5->4->3->2->1->NULL
  * 进阶:
  * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
  *
  *
  *
  * Created by root on 2018-6-9.
  */
object List3 {


  /**
    * 递归方法
    *
    * @param head
    * @return
    */
  def reverseList(head: ListNode): ListNode = {
    reverse(null, head)
  }

  def reverse(pre: ListNode, cur: ListNode): ListNode = {
    if (cur == null)
      null
    else {
      if (cur.next == null) {
        cur.next = pre
        cur
      } else {
        var next = cur.next
        cur.next = pre
        reverse(cur, next)
      }
    }
  }

  def reverse2(pre: ListNode, cur: ListNode): ListNode = {
    if (cur == null) {
      cur
    } else if (cur.next == null) {
      cur.next = pre
      cur
    } else {
      val next = cur.next
      cur.next = pre
      reverse2(cur, next)
    }
  }

  /**
    * 非递归实现
    *
    * @param head
    * @return
    */
  def reverseList2(head: ListNode): ListNode = {
    if (head == null)
      return head
    var head_ = head
    var cur = head.next
    val pre = head
    while (cur != null) {
      pre.next = cur.next
      cur.next = head_
      head_ = cur
      cur = pre.next
    }
    head_
  }


  def main(args: Array[String]): Unit = {
    val head = new ListNode(0)
    var cur = head
    for (i <- 1 to 2) {
      val list = new ListNode(i)
      cur.next = list
      cur = list
    }
    var reversHead = reverseList(head)
    while (reversHead != null) {
      println(reversHead.x)
      reversHead = reversHead.next
    }
  }
}

package study.leetcode.List

import scala.collection.mutable

/**
  *
  * 回文链表
  * 请判断一个链表是否为回文链表。
  * *
  * 示例 1:
  * *
  * 输入: 1->2
  * 输出: false
  * 示例 2:
  * *
  * 输入: 1->2->2->1
  * 输出: true
  * 进阶：
  * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
  *
  *
  * Created by root on 2018-6-11.
  */
object List5 {


  def isPalindrome(head: ListNode): Boolean = {
    if (head == null || head.next == null)
      true
    else {
      var flag = true
      var slow = head
      var fast = head
      while (fast.next != null && fast.next.next != null) {
        slow = slow.next
        fast = fast.next.next
      }
      var reverseHead = reverseList(slow.next)
      var head_ = head
      while (reverseHead != null) {
        if (head_.x != reverseHead.x)
          flag = false
        head_ = head_.next
        reverseHead = reverseHead.next
      }
      flag
    }
  }

  def reverseList(head: ListNode): ListNode = {
    if (head == null) {
      head
    } else {
      var cur: ListNode = head.next
      var head_ = head
      val pre = head
      while (cur != null) {
        pre.next = cur.next
        cur.next = head_
        head_ = cur
        cur = pre.next
      }
      head_
    }
  }

  def main(args: Array[String]): Unit = {
    val head = new ListNode(0)
    var cur = head
    for (i <- 1 to 2) {
      val list = new ListNode(i)
      cur.next = list
      cur = list
    }
    var reversHead = isPalindrome(head)
    //    while (reversHead != null) {
    //      println(reversHead.x)
    //      reversHead = reversHead.next
    //    }
    println(reversHead)
  }
}

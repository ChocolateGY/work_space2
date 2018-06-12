package study.leetcode.List

/**
  *
  * 删除链表的倒数第N个节点
  * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
  * *
  * 示例：
  * *
  * 给定一个链表: 1->2->3->4->5, 和 n = 2.
  * *
  * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
  * 说明：
  * *
  * 给定的 n 保证是有效的。
  * *
  * 进阶：
  * *
  * 你能尝试使用一趟扫描实现吗？
  *
  *
  * Created by root on 2018-6-6.
  */
object List2 {
  def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
    var node: ListNode = head
    val arr = collection.mutable.ArrayBuffer[ListNode]()
    while (node != null) {
      arr += node
      node = node.next
    }
    if (arr.length > 1) {
      if (n < arr.length) {
        arr(arr.length - 1 - n).next = arr(arr.length - n).next
        head
      } else
        head.next
    } else
      null
  }
}

class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}
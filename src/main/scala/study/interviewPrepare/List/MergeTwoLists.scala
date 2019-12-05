package study.interviewPrepare.List

/**
  * 合并两个有序链表
  *
  * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
  *
  * 示例：
  *
  * 输入：1->2->4, 1->3->4
  * 输出：1->1->2->3->4->4
  */
object MergeTwoLists {

  //没啥问题
  def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {
    val head = new ListNode(0)
    var cur = head
    var l1t = l1
    var l2t = l2
    while (l1t != null && l2t != null) {
      if (l1t.x < l2t.x) {
        cur.next = l1t
        l1t = l1t.next
      } else {
        cur.next = l2t
        l2t = l2t.next
      }
      cur = cur.next
    }
    if (l1t != null) cur.next = l1t
    if (l2t != null) cur.next = l2t
    head.next
  }
}

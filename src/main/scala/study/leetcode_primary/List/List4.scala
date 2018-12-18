package study.leetcode_primary.List

/**
  *
  * 合并两个有序链表
  * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
  * *
  * 示例：
  * *
  * 输入：1->2->4, 1->3->4
  * 输出：1->1->2->3->4->4
  *
  *
  * Created by root on 2018-6-11.
  */
object List4 {

  /**
    * 非递归
    *
    * @param l1
    * @param l2
    * @return
    */
  def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {
    var head1 = l1
    var head2 = l2
    var node: ListNode = new ListNode(0)
    val result = node
    while (head1 != null && head2 != null) {
      if (head1.x > head2.x) {
        node.next = head2
        head2 = head2.next
      } else {
        node.next = head1
        head1 = head1.next
      }
      node = node.next
    }
    if (head1 == null && head2 != null) {
      node.next = head2
    }
    if (head2 == null && head1 != null) {
      node.next = head1
    }
    result.next
  }


  /**
    * 递归
    *
    * @param l1
    * @param l2
    * @return
    */
  def mergeTwoLists2(l1: ListNode, l2: ListNode): ListNode = {
    var temp1 = l1
    var temp2 = l2
    getResult(temp1, temp2)
  }

  def getResult(t1: ListNode, t2: ListNode): _root_.study.leetcode_primary.List.ListNode = {
    var t0 = new ListNode(0)
    var head = t0
    if (t1 == null)
      return t2
    if (t2 == null)
      return t1
    if (t1.x > t2.x) {
      t0.next = t2
      t0 = t0.next
      t0.next = getResult(t1, t2.next)
    } else {
      t0.next = t1
      t0 = t0.next
      t0.next = getResult(t1.next, t2)
    }
    return head.next
  }

  def mergeTwoLists3(l1: ListNode, l2: ListNode): ListNode = {
    val result = new ListNode(0)
    var t = result
    if (l1 == null)
      l2
    else if (l2 == null)
      l1
    else if (l1.x > l2.x) {
      t.next = l2
      t = t.next
      t.next = mergeTwoLists3(l1, l2.next)
      result.next
    } else {
      t.next = l1
      t = t.next
      t.next = mergeTwoLists3(l1.next, l2)
      result.next
    }
  }
}

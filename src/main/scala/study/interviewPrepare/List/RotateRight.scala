package study.interviewPrepare.List

/**
  * 旋转链表
  *
  * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
  *
  * 示例 1:
  *
  * 输入: 1->2->3->4->5->NULL, k = 2
  * 输出: 4->5->1->2->3->NULL
  * 解释:
  * 向右旋转 1 步: 5->1->2->3->4->NULL
  * 向右旋转 2 步: 4->5->1->2->3->NULL
  * 示例 2:
  *
  * 输入: 0->1->2->NULL, k = 4
  * 输出: 2->0->1->NULL
  * 解释:
  * 向右旋转 1 步: 2->0->1->NULL
  * 向右旋转 2 步: 1->2->0->NULL
  * 向右旋转 3 步: 0->1->2->NULL
  * 向右旋转 4 步: 2->0->1->NULL
  */
object RotateRight {

  //总结：遍历链表，统计长度，然后连成环，计算右移的位置，断开链表。维护两个指针。
  def rotateRight(head: ListNode, k: Int): ListNode = {
    if (head == null || k == 0)
      return head
    var cur = head
    var pre = head

    var count = 1
    while (cur.next != null) {
      pre = cur
      cur = cur.next
      count += 1
    }
    var num = count - k % count
    cur.next = head
    while (num >= 0) {
      num -= 1
      pre = cur
      cur = cur.next
    }
    pre.next = null
    cur
  }

}

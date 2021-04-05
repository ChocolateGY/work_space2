package study.interview.tencent

/**
  * 21. 合并两个有序链表
  * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
  *
  * 示例：
  *
  * 输入：1->2->4, 1->3->4
  * 输出：1->1->2->3->4->4
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object MergeTwoSortedLists_21 {
  def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {
    var t1 = l1
    var t2 = l2
    val res = new ListNode(0)
    var cur = res
    while (t1 != null && t2 != null) {
      if (t1.x < t2.x) {
        cur.next = t1
        cur = cur.next
        t1 = t1.next
      } else {
        cur.next = t2
        cur = cur.next
        t2 = t2.next
      }
    }
    if (t1 != null) cur.next = t1
    if (t2 != null) cur.next = t2
    res.next
  }

}

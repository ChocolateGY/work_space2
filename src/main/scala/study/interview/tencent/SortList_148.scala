package study.interview.tencent

/**
  * 148. 排序链表
  * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
  *
  * 示例 1:
  *
  * 输入: 4->2->1->3
  * 输出: 1->2->3->4
  * 示例 2:
  *
  * 输入: -1->5->3->4->0
  * 输出: -1->0->3->4->5
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/sort-list
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object SortList_148 {
  def sortList(head: ListNode): ListNode = {
    if (head == null || head.next == null) return head
    var slow = head
    var fast = head
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next
      fast = fast.next
    }
    var temp = slow.next
    slow.next = null
    var l1 = sortList(head)
    var l2 = sortList(temp)
    val res = new ListNode(0)
    var cur = res
    while (l1 != null && l2 != null) {
      if (l1.x < l2.x) {
        cur.next = l1
        l1 = l1.next
      } else {
        cur.next = l2
        l2 = l2.next
      }
      cur = cur.next
    }
    if (l1 != null) cur.next = l1
    if (l2 != null) cur.next = l2
    res.next
  }
}

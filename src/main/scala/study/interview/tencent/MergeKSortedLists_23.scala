package study.interview.tencent

/**
  * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
  *
  * 示例:
  *
  * 输入:
  * [
  *   1->4->5,
  *   1->3->4,
  *   2->6
  * ]
  * 输出: 1->1->2->3->4->4->5->6
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object MergeKSortedLists_23 {
  def mergeKLists(lists: Array[ListNode]): ListNode = {
    def merge(l1: ListNode, l2: ListNode): ListNode = {
      if (l1 == null) return l2
      if (l2 == null) return l1
      val res = new ListNode(0)
      var cur = res
      var t1 = l1
      var t2 = l2
      while (t1 != null && t2 != null) {
        if (t1.x < t2.x) {
          cur.next = t1
          t1 = t1.next
        } else {
          cur.next = t2
          t2 = t2.next
        }
        cur = cur.next
      }
      if (t1 != null) cur.next = t1
      if (t2 != null) cur.next = t2
      res.next
    }

    def dividSort(left: Int, right: Int): ListNode = {
      if (left >= right)
        return lists(left)
      val mid = left + (right - left) / 2
      val list1 = dividSort(left, mid)
      val list2 = dividSort(mid + 1, right)
      merge(list1, list2)
    }

    if (lists == null || lists.isEmpty)
      return null
    dividSort(0, lists.length - 1)

  }
}

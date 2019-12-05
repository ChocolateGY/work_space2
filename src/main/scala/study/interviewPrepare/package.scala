package study

import study.interviewPrepare.List.ListNode

/**
  * 练习板
  */
package object interviewPrepare {
  /**
    * 递归  归并排序
    * 步骤：1、用快慢指针分离。2、左右有序链表合并
    *
    * @param head
    * @return
    */
  def sortList(head: ListNode): ListNode = {
    if (head == null || head.next == null)
      return head
    var res = new ListNode(0)
    var slow = head
    var fast = head.next
    while (fast != null && fast.next != null) {
      slow = slow.next
      fast = fast.next.next
    }
    var temp = slow.next
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

  /**
    * 合并K个元素的有序链表  堆排序
    * 步骤：将数组不断分成2个，并开始合并
    * @param lists
    * @return
    */
  def mergeKLists(lists: Array[ListNode]): ListNode = {
    if (lists == null || lists.isEmpty) {
      return null
    }

    def dividSort(left: Int, right: Int): ListNode = {
      if (left >= right)
        return lists(left)
      val mid = left + (right - left) / 2
      val list1 = dividSort(left, mid)
      val list2 = dividSort(mid + 1, right)
      merge(list1, list2)
    }

    def merge(l1: ListNode, l2: ListNode): ListNode = {
      if (l1 == null) return l2
      if (l2 == null) return l1
      val res = new ListNode(0)
      var cur = res
      var list1 = l1
      var list2 = l2
      while (list1 != null && list2 != null) {
        if (list1.x < list2.x) {
          cur.next = list1
          list1 = list1.next
        } else {
          cur.next = list2
          list2 = list2.next
        }
        cur = cur.next
      }
      if (list1 != null) cur.next = list1
      if (list2 != null) cur.next = list2
      res.next
    }

    dividSort(0,lists.length-1)
  }

}

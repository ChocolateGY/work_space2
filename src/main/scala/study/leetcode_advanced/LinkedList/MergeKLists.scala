package study.leetcode_advanced.LinkedList

import study.leetcode_middle.List.ListNode

/**
  * 合并K个元素的有序链表
  * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
  *
  * 示例:
  *
  * 输入:
  * [
  * 1->4->5,
  * 1->3->4,
  * 2->6
  * ]
  * 输出: 1->1->2->3->4->4->5->6
  */
object MergeKLists {
  /**
    * 分治的方法
    * 耗时最少
    */
  def mergeKLists(lists: Array[ListNode]): ListNode = lists match {

    case list if list == null || list.isEmpty => null
    case list => sort(list, 0, list.length - 1)
  }

  def sort(nodes: Array[ListNode], low: Int, high: Int): ListNode = {
    if (low >= high)
      return nodes(low)

    val mid = (high - low) / 2 + low
    val l1 = sort(nodes, low, mid)
    val l2 = sort(nodes, mid + 1, high)
    merge(l1, l2)
  }

  def merge(node1: ListNode, node2: ListNode): ListNode = {
    if (node1 == null) return node2
    if (node1 == null) return node1
    val dummy = new ListNode(0)
    dummy.next = node1
    var cur = dummy
    var n1 = node1
    var n2 = node2
    while (n1 != null && n2 != null) {
      if (n1.`val` < n2.`val`)
        n1 = n1.next
      else {
        val next = n2.next
        n2.next = cur.next
        cur.next = n2
        n2 = next
      }
      cur = cur.next
    }
    if (n2 != null)
      cur.next = n2
    dummy.next
  }

  def main(args: Array[String]): Unit = {
    //    [[1,4,5],[1,3,4],[2,6]]
    val l1 = new ListNode(1)
    val l2 = new ListNode(4)
    val l3 = new ListNode(5)
    val l4 = new ListNode(1)
    val l5 = new ListNode(3)
    val l6 = new ListNode(4)
    val l7 = new ListNode(2)
    val l8 = new ListNode(6)
    l1.next = l2
    l2.next = l3
    l4.next = l5
    l5.next = l6
    l7.next = l8

    var res = mergeKLists(Array(l1, l4, l7))
    while (res.next != null) {
      print(res.`val` + " ")
      res = res.next
    }
  }
}

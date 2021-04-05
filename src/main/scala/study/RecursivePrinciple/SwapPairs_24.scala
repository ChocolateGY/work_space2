package study.RecursivePrinciple

import study.interview.prepare.List.ListNode

/**
  * 24. 两两交换链表中的节点
  * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
  *
  * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
  *
  *  
  *
  * 示例:
  *
  * 给定 1->2->3->4, 你应该返回 2->1->4->3.
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object SwapPairs_24 {
  def swapPairs(head: ListNode): ListNode = {
    if (head == null || head.next == null)
      head
    else {
      val last = swapPairs(head.next.next)
      val t1 = head
      val t2 = head.next
      t1.next = last
      t2.next = t1
      t2
    }
  }

}

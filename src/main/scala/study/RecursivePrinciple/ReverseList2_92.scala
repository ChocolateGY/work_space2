package study.RecursivePrinciple

import study.interview.prepare.List.ListNode

/**
  * 92. 反转链表 II
  * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
  * *
  * 说明:
  * 1 ≤ m ≤ n ≤ 链表长度。
  * *
  * 示例:
  * *
  * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
  * 输出: 1->4->3->2->5->NULL
  * *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object ReverseList2_92 {

  //递归
  def reverseBetween(head: ListNode, m: Int, n: Int): ListNode = {
    var suc: ListNode = null

    def reverseN(node: ListNode, i: Int): ListNode = {
      if (i == 1) {
        suc = node.next
        node
      } else {
        val last = reverseN(node.next, i - 1)
        node.next.next = node
        node.next = suc
        last
      }
    }

    if (m == 1) {
      return reverseN(head, n)
    }
    head.next = reverseBetween(head.next, m - 1, n - 1)
    head
  }

  //迭代
  def reverseBetween2(head: ListNode, m: Int, n: Int): ListNode = {
    if (head == null) return head
    var i = 1
    var cur = head
    var pre: ListNode = null
    while (i < m) {
      pre = cur
      cur = cur.next
      i += 1
    }
    val last1 = pre //第一段结尾
    val last2 = cur //反转段结尾
    var next = cur.next //next 依赖 cur
    while (i <= n) {
      next = cur.next
      cur.next = pre
      pre = cur
      cur = next
      i += 1
    }
    //反转尾指向结果
    last2.next = cur
    if (last1 != null) {
      last1.next = pre
      head
    } else
      pre
  }


  def main(args: Array[String]): Unit = {

    val l1 = new ListNode(1)
    val l2 = new ListNode(2)
    val l3 = new ListNode(3)
    val l4 = new ListNode(4)
    val l5 = new ListNode(5)
    l1.next = l2
    l2.next = l3
    l3.next = l4
    l4.next = l5
    var res = reverseBetween2(l1, 2, 4)
    while (res != null) {
      println(res.x)
      res = res.next
    }
  }
}

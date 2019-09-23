package study.interview.byteDance.ListAndTree

/**
  * 反转链表
  * 反转一个单链表。
  *
  * 示例:
  *
  * 输入: 1->2->3->4->5->NULL
  * 输出: 5->4->3->2->1->NULL
  * 进阶:
  * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
  */
object ReverseList {
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

    var res = reverseList2(l1)
    while (res != null) {
      println(res.x)
      res = res.next
    }
  }

  def reverseList(head: ListNode): ListNode = {
    if (head != null && head.next != null) {
      val temp = head.next
      head.next = null
      recursive2(head, temp)
    }
    else
      head
  }

  /**
    * 自己实现
    *
    * @param curNode
    * @param nextNode
    * @return
    */
  def recursive(curNode: ListNode, nextNode: ListNode): ListNode = {
    if (nextNode == null) {
      curNode
    } else {
      val tempNode = nextNode.next
      nextNode.next = curNode
      recursive(nextNode, tempNode)
    }
  }

  /**
    * 仿照答案改变
    * 时间相同。
    *
    * @param curNode
    * @param nextNode
    * @return
    */
  def recursive2(curNode: ListNode, nextNode: ListNode): ListNode = {
    val tempNode = nextNode.next
    nextNode.next = curNode
    if (tempNode == null) {
      nextNode //这里有变化
    } else {
      recursive2(nextNode, tempNode)
    }
  }

  /**
    * 迭代方法
    * 自己实现
    */
  def reverseList2(head: ListNode): ListNode = {
    if (head != null && head.next != null) {
      var curNode = head
      var nextNode = head.next
      head.next = null
      while (nextNode != null) {
        var temp = nextNode.next
        nextNode.next = curNode
        curNode = nextNode
        nextNode = temp
      }
      curNode
    }
    else
      head
  }

  /**
    * leetcode最快答案
    */

  def reverseListS(head: ListNode): ListNode = {

    if (head == null || head.next == null) {
      return head

    }
    var tmp = head.next
    head.next = null
    reverseS(tmp, head)
  }

  def reverseS(cur: ListNode, prev: ListNode = null): ListNode = {
    var tmp = cur.next
    cur.next = prev
    if (tmp == null) cur else reverseS(tmp, cur)
  }
}

package study.interview.prepare.List

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
  *
  */
object ReverseList {
  //递归
  def reverseList(head: ListNode): ListNode = {
    if (head != null && head.next != null) {
      val pre = head.next
      val res = reverseList(pre)
      pre.next = head
      head.next = null
      res
    } else
      head
  }

  //迭代  ，使用head来储存next节点。
  def reverseList2(head: ListNode): ListNode = {
    if (head != null) {
      var cur = head
      while (head.next != null) {
        val pre = head.next
        head.next = pre.next
        pre.next = cur
        cur = pre
      }
      cur
    } else
      head
  }

  //迭代  ，需要创建两个引用，next、pre
  def reverseList3(head: ListNode): ListNode = {
    if (head != null) {
      //全部初始化head
      var cur = head
      var pre = head
      var next = head.next
      while (next != null) {
        next = cur.next
        cur.next = pre
        pre = cur
        cur = next
      }
      //把头节点指向null
      head.next = null
      pre //注意最后返回pre
    } else
      head
  }

  def main(args: Array[String]): Unit = {
    val a1 = new ListNode(1)
    val a2 = new ListNode(2)
    val a3 = new ListNode(3)
    val a4 = new ListNode(4)
    val a5 = new ListNode(5)
    a1.next = a2
    a2.next = a3
    a3.next = a4
    a4.next = a5

    var res = reverseList3(a1)
    while (res != null) {
      println(res.x)
      res = res.next
    }
  }
}

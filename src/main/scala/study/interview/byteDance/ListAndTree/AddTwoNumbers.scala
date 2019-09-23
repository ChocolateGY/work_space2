package study.interview.byteDance.ListAndTree


/**
  * 两数相加
  * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
  *
  * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
  *
  * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
  *
  * 示例：
  *
  * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
  * 输出：7 -> 0 -> 8
  * 原因：342 + 465 = 807
  */

/**
  * Definition for singly-linked list.
  * class ListNode(var _x: Int = 0) {
  * var next: ListNode = null
  * var x: Int = _x
  * }
  */
object AddTwoNumbers {
  def main(args: Array[String]): Unit = {
    val l11 = new ListNode(2)
    val l12 = new ListNode(4)
    val l13 = new ListNode(3)
    l11.next = l12
    l12.next = l13

    val l21 = new ListNode(5)
    val l22 = new ListNode(6)
    val l23 = new ListNode(4)
    l21.next = l22
    l22.next = l23


    val a1 = new ListNode(1)
    val a2 = new ListNode(8)
    a1.next = a2
    val b1 = new ListNode(0)

    val c1 = new ListNode(5)
    val d1 = new ListNode(5)
    //    var resNode = addTwoNumbers(l11, l21)
    var resNode = addTwoNumbers(c1, d1)
    while (resNode != null) {
      println(resNode.x)
      resNode = resNode.next
    }

  }

  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    recursive(l1, l2, 0)
  }

  //  def recursive(l1: ListNode, l2: ListNode, nextNum: Int): (ListNode) = {
  //    val node = new ListNode()
  //    val num = l1.x + l2.x + nextNum
  //    node.x = num % 10
  //    val q = num / 10
  //    if (l1.next != null && l2.next != null) {
  //      val lastNode = recursive(l1.next, l2.next, q)
  //      node.next = lastNode
  //    } else if (l1.next != null && l2.next == null) {
  //      if (q != 0) {
  //        l1.next.x = l1.next.x + q
  //      }
  //      node.next = l1.next
  //    } else if (l1.next == null && l2.next != null) {
  //      if (q != 0) {
  //        l2.next.x = l2.next.x + q
  //      }
  //      node.next = l2.next
  //    } else {
  //      if (q != 0) {
  //        val lastNode = new ListNode(q)
  //        node.next = lastNode
  //      }
  //    }
  //    node
  //  }

  def recursive(l1: ListNode, l2: ListNode, nextNum: Int): (ListNode) = {
    val node = new ListNode()
    val num1 = if (l1 == null) 0 else l1.x
    val num2 = if (l2 == null) 0 else l2.x
    val num = num1 + num2 + nextNum
    node.x = num % 10
    val q = num / 10

    if (l1 == null && l2 == null) {
      if (num == 0)
        return null
    } else {
      if (l1 != null && l2 != null) {
        val lastNode = recursive(l1.next, l2.next, q)
        node.next = lastNode
      } else if (l1 != null && l2 == null) {
        val lastNode = recursive(l1.next, null, q)
        node.next = lastNode
      } else {
        val lastNode = recursive(null, l2.next, q)
        node.next = lastNode
      }
    }
    node
  }
}

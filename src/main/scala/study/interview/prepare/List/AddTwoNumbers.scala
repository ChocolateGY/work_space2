package study.interview.prepare.List

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
object AddTwoNumbers {
  //递归
  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    def recursive(l1: ListNode, l2: ListNode, num: Int): ListNode = {
      if (l1 == null && l2 == null) {
        if (num == 0)
          null
        else
          new ListNode(num)
      } else {
        val head = new ListNode()
        if (l1 != null && l2 != null) {
          val sum = l1.x + l2.x + num
          head.x = sum % 10
          val node = recursive(l1.next, l2.next, sum / 10)
          head.next = node
        } else if (l1 != null) {
          val sum = l1.x + num
          head.x = sum % 10
          val node = recursive(l1.next, null, sum / 10)
          head.next = node
        } else if (l2 != null) {
          val sum = l2.x + num
          head.x = sum % 10
          val node = recursive(null, l2.next, sum / 10)
          head.next = node
        }
        head
      }
    }

    recursive(l1, l2, 0)
  }

  //迭代
  //用其中一条链来存放数据
  def addTwoNumbers2(l1: ListNode, l2: ListNode): ListNode = {
    var t1 = l1
    var t2 = l2
    //需要一个引用跟踪当前节点
    var cur = t1
    var num = 0
    while (t1 != null && t2 != null) {
      cur = t1
      val sum = t1.x + t2.x + num
      num = sum / 10
      t1.x = sum % 10
      t1 = t1.next
      t2 = t2.next
    }
    while (t1 != null) {
      cur = t1
      val sum = t1.x + num
      num = sum / 10
      t1.x = sum % 10
      t1 = t1.next
    }
    cur.next = t2
    while (t2 != null) {
      cur = t2
      val sum = t2.x + num
      num = sum / 10
      t2.x = sum % 10
      t2 = t2.next
    }
    while (num != 0) {
      val node = new ListNode(num % 10)
      num /= 10
      cur.next = node
      cur = node
    }
    l1
  }
}

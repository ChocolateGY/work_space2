package study.interview.tencent

/**
  * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
  *
  * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
  *
  * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
  *
  * 示例：
  *
  * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
  * 输出：7 -> 0 -> 8
  * 原因：342 + 465 = 807
  *
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/add-two-numbers
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object AddTwoNumbers_2 {
  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    var cur = l1
    var num = 0
    var list1 = l1
    var list2 = l2
    while (list1 != null && list2 != null) {
      cur = list1
      val sum = list1.x + list2.x + num
      list1.x = sum % 10
      num = sum / 10
      list1 = list1.next
      list2 = list2.next
    }
    while (list1 != null) {
      cur = list1
      val sum = list1.x + num
      list1.x = sum % 10
      num = sum / 10
      list1 = list1.next
    }
    cur.next = list2
    while (list2 != null) {
      cur = list2
      val sum = list2.x + num
      list2.x = sum % 10
      num = sum / 10
      list2 = list2.next
    }
    if (num != 0) {
      val node = new ListNode(num)
      cur.next = node
    }
    l1
  }
}

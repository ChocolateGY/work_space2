package study.interviewPrepare.List

/**
  * 删除链表的倒数第N个节点
  *
  * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
  *
  * 示例：
  *
  * 给定一个链表: 1->2->3->4->5, 和 n = 2.
  *
  * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
  * 说明：
  *
  * 给定的 n 保证是有效的。
  *
  * 进阶：
  *
  * 你能尝试使用一趟扫描实现吗？
  */
object RemoveNthFromEnd {
  /**
    * 第一个指针从列表的开头向前移动 n+1n+1 步，而第二个指针将从列表的开头出发。
    *
    * 现在，这两个指针被 nn 个结点分开。我们通过同时移动两个指针向前来保持这个恒定的间隔，
    *
    * 直到第一个指针到达最后一个结点。此时第二个指针将指向从最后一个结点数起的第 nn 个结点。
    *
    * 我们重新链接第二个指针所引用的结点的 next 指针指向该结点的下下个结点。
    *
    * 作者：LeetCode
    * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-by-l/
    * 来源：力扣（LeetCode）
    * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    *
    * @param head
    * @param n
    * @return
    */
  def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
    val res = new ListNode(0)
    res.next = head
    var pre = res
    var node = res
    for (i <- 1 to n+1) {
      pre = pre.next
    }
    while (pre != null) {
      pre = pre.next
      node = node.next
    }
    node.next = node.next.next
    res.next
  }
//相同，只是临界值改变
  def removeNthFromEnd2(head: ListNode, n: Int): ListNode = {
    val res = new ListNode(0)
    res.next = head
    var pre = res
    var node = res
    for (i <- 1 to n) {
      pre = pre.next
    }
    while (pre.next != null) {
      pre = pre.next
      node = node.next
    }
    node.next = node.next.next
    res.next
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
    var head = removeNthFromEnd(a1,2)
    while(head!=null){
      println(head.x)
      head = head.next
    }
  }
}

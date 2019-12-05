package study.interviewPrepare.List

/**
  * 移除链表元素
  * 删除链表中等于给定值 val 的所有节点。
  *
  * 示例:
  *
  * 输入: 1->2->6->3->4->5->6, val = 6
  * 输出: 1->2->3->4->5
  *
  *
  */
//https://leetcode-cn.com/problems/remove-linked-list-elements/solution/203yi-chu-lian-biao-yuan-su-by-lewis-dxstabdzew/
object RemoveElements {
  //方法1 删除头结点时另作考虑
  def removeElements(head: ListNode, `val`: Int): ListNode = {
    var res = head
    while (res != null && res.x == `val`)
      res = res.next
    if (res != null) {
      var node = res.next
      var post = res
      while (node != null) {
        if (node.x == `val`) {
          node = node.next
          post.next = node
        } else {
          post = post.next
          node = node.next
        }
      }
    }
    res
  }

  //创建临时头节点
  def removeElements2(head: ListNode, `val`: Int): ListNode = {
    val res = new ListNode(0)
    res.next = head
    var node = res
    while (node.next != null) {
      if (node.next.x == `val`) {
        node.next = node.next.next
      } else {
        node = node.next
      }
    }
    res.next
  }

  //递归
  def removeElements3(head: ListNode, `val`: Int): ListNode = {
    if (head == null)
      return null
    head.next = removeElements3(head.next, `val`)
    if (head.x == `val`)
      head.next
    else
      head
  }
}

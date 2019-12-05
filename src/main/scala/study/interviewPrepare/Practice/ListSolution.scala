package study.interviewPrepare.Practice

import study.interviewPrepare.List.ListNode

object ListSolution {

  /**
    * 环形链表
    * 给定一个链表，判断链表中是否有环。
    *
    * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
    *
    * 示例 1：
    *
    * 输入：head = [3,2,0,-4], pos = 1
    * 输出：true
    * 解释：链表中有一个环，其尾部连接到第二个节点。
    *
    * 示例 2：
    *
    * 输入：head = [1,2], pos = 0
    * 输出：true
    * 解释：链表中有一个环，其尾部连接到第一个节点。
    *
    *
    * 示例 3：
    *
    * 输入：head = [1], pos = -1
    * 输出：false
    * 解释：链表中没有环。
    *
    * 进阶：
    *
    * 你能用 O(1)（即，常量）内存解决此问题吗？
    *
    */
  def hasCycle(head: ListNode): Boolean = {
    if (head == null || head.next == null)
      return false
    var slow = head
    var fast = head
    while (fast != null && fast.next != null) {
      slow = slow.next
      fast = fast.next.next
      if (slow == fast)
        return true
    }
    false

  }

  /**
    * 排序链表
    * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
    *
    * 示例 1:
    *
    * 输入: 4->2->1->3
    * 输出: 1->2->3->4
    * 示例 2:
    *
    * 输入: -1->5->3->4->0
    * 输出: -1->0->3->4->5
    */
  def sortList(head: ListNode): ListNode = {
    if (head == null || head.next == null)
      return head
    var slow = head
    var fast = head.next //这里必须要这样
    while (fast != null && fast.next != null) {
      slow = slow.next
      fast = fast.next.next
    }
    val head2 = slow.next
    slow.next = null
    var l1 = sortList(head)
    var l2 = sortList(head2)
    val res = new ListNode(0)
    var cur = res
    while (l1 != null && l2 != null) {
      if (l1.x < l2.x) {
        cur.next = l1
        l1 = l1.next
      } else {
        cur.next = l2
        l2 = l2.next
      }
      cur = cur.next
    }
    if (l1 != null) cur.next = l1
    if (l2 != null) cur.next = l2
    res.next
  }

  /**
    * 相交链表
    * 编写一个程序，找到两个单链表相交的起始节点。
    * 注意：
    *
    * 如果两个链表没有交点，返回 null.
    * 在返回结果后，两个链表仍须保持原有的结构。
    * 可假定整个链表结构中没有循环。
    * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
    */
  def getIntersectionNode(headA: ListNode, headB: ListNode): ListNode = {
    var l1 = headA
    var l2 = headB
    while (l1 != l2) {
      l1 = if (l1 == null) headB else l1.next
      l2 = if (l2 == null) headA else l2.next
    }
    l1
  }

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
    * 思路：通过递归拿到最后一个元素，然后依次改变next
    */
  def reverseList1(head: ListNode): ListNode = {
    def recursive(cur: ListNode, next: ListNode): ListNode = {
      val last = if (next.next == null) next else recursive(next, next.next)
      next.next = cur
      cur.next = null
      last
    }

    if (head == null || head.next == null)
      return head
    recursive(head, head.next)
  }

  //迭代方法  思路：用head.next 指针保存下下一个节点
  def reverseList2(head: ListNode): ListNode = {
    if (head == null)
      return head
    var cur = head
    while (head.next != null) {
      val next = head.next
      head.next = next.next
      next.next = cur
      cur = next
    }
    cur
  }

  /**
    * 回文链表
    * 请判断一个链表是否为回文链表。
    *
    * 示例 1:
    *
    * 输入: 1->2
    * 输出: false
    * 示例 2:
    *
    * 输入: 1->2->2->1
    * 输出: true
    * 进阶：
    * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
    */
  def isPalindrome(head: ListNode): Boolean = {
    if (head == null || head.next == null)
      return true
    var slow = head
    var fast = head //这里没有next
    //找到中间点
    while (fast != null && fast.next != null) {
      slow = slow.next
      fast = fast.next.next
    }
    //开始反转
    var pre, next: ListNode = null
    var cur = head
    while (cur != slow) {
      next = cur.next
      cur.next = pre
      pre = cur
      cur = next
    }
    //判断奇偶
    if (fast != null) slow = slow.next

    //判断是否相同
    while (pre != null) {
      if (pre.x != slow.x)
        return false
      pre = pre.next
      slow = slow.next
    }
    true
  }

  /**
    * 奇偶链表
    * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
    *
    * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
    *
    * 示例 1:
    *
    * 输入: 1->2->3->4->5->NULL
    * 输出: 1->3->5->2->4->NULL
    * 示例 2:
    *
    * 输入: 2->1->3->5->6->4->7->NULL
    * 输出: 2->3->6->7->1->5->4->NULL
    * 说明:
    *
    * 应当保持奇数节点和偶数节点的相对顺序。
    * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
    *
    */
  def oddEvenList(head: ListNode): ListNode = {
    if (head == null || head.next == null)
      return head
    val head1 = head
    val head2 = head.next
    var l1 = head1
    var l2 = head2
    while (l2 != null && l2.next != null) {
      l1.next = l2.next
      l1 = l1.next
      l2.next = l1.next
      l2 = l2.next
    }
    l1.next = head2
    head1
  }
}

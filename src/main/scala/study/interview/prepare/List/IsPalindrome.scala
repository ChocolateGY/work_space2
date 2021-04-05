package study.interview.prepare.List

/**
  * 回文链表
  *
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
object IsPalindrome {

  //https://leetcode-cn.com/problems/palindrome-linked-list/solution/javashi-xian-kuai-man-zhi-zhen-fan-zhuan-qian-ban-/
  //总结：快慢指针，前半段一边循环一边反转
  def isPalindrome(head: ListNode): Boolean = {
    if (head == null || head.next == null)
      return true
    var slow = head
    var fast = head.next
    var pre, prepre: ListNode = null
    while (fast != null && fast.next != null) {
      //移动指针
      pre = slow
      slow = slow.next
      fast = fast.next.next
      //反转
      pre.next = prepre
      prepre = pre
    }
    var p2 = slow.next
    slow.next = pre

    var p1 = if (fast == null) slow.next else slow
    while (p1 != null) {
      if (p1.x != p2.x)
        return false

      p1 = p1.next
      p2 = p2.next
    }
    true
  }


  /**
    * 这个更顺畅  ，与上面的差别是在判断奇偶的时候
    *
    * @param head
    * @return
    */
  def isPalindrome2(head: ListNode): Boolean = {
    if (head == null || head.next == null)
      return true
    var slow, fast = head
    while (fast != null && fast.next != null) {
      slow = slow.next
      fast = fast.next.next
    }
    var pre, next: ListNode = null
    var cur = head
    while (cur != slow) {
      next = cur.next
      cur.next = pre
      pre = cur
      cur = next
    }
    //如果是奇数，则空出中间点
    if (fast != null)
      slow = slow.next

    while (pre != null) {
      if (pre.x != slow.x)
        return false

      pre = pre.next
      slow = slow.next
    }
    true
  }
}

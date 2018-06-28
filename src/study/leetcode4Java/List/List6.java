package study.leetcode4Java.List;

import study.leetcode.List.ListNode;

/**
 * 环形链表
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 进阶：
 * 你能否不使用额外空间解决此题？
 * Created by root on 2018-6-13.
 */
public class List6 {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next() != null) {
            slow = slow.next();
            fast = fast.next().next();
            if (slow == fast)
                return true;
        }
        return false;
    }
}

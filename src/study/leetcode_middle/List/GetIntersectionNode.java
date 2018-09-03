package study.leetcode_middle.List;

/**
 * Created by root on 2018-8-22.
 * <p>
 * 相交链表
 * <p>
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 例如，下面的两个链表：
 * <p>
 * A:          a1 → a2
 * ↘
 * c1 → c2 → c3
 * ↗
 * B:     b1 → b2 → b3
 * 在节点 c1 开始相交。
 * <p>
 * <p>
 * <p>
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class GetIntersectionNode {

    //报错。测试未通过
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode node1 = headA;
        int n1 = 0;
        int n2 = 0;

        while (node1 != null) {
            n1++;
            node1 = node1.next();
        }
        ListNode nodeB = headB;

        while (nodeB != null) {
            n2++;
            nodeB = nodeB.next();
        }
        while (n1 > n2) {
            headA = headA.next();
            n1--;
        }
        while (n2 > n1) {
            headB = headB.next();
        }
        while (headA != null) {
            if (headA == headB)
                return headA;
            headA = headA.next();
            headB = headB.next();
        }
        return null;
    }

    //通过
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode p1 = headA;
        ListNode p2 = headB;
        int l1 = 0;
        int l2 = 0;
        while (p1.next() != null || p2.next() != null) {
            if (p1 == p2) return p1;
            if (p1.next() != null)
                p1 = p1.next();
            else
                l2++;
            if (p2.next() != null)
                p2 = p2.next();
            else
                l1++;
        }
        if (p1 != p2) return null;
        p1 = headA;
        p2 = headB;
        while (l1-- != 0)
            p1 = p1.next();
        while (l2-- != 0)
            p2 = p2.next();
        while (p1 != p2) {
            p1 = p1.next();
            p2 = p2.next();
        }
        return p1;

    }
}

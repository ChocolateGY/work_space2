package study.interview.list;

/**
 * 160 交链表
 * <p>
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 方法一: 暴力法
 * 对链表A中的每一个结点 a_ia
 * i
 * ​
 * ，遍历整个链表 B 并检查链表 B 中是否存在结点和 a_ia
 * i
 * ​
 * 相同。
 * <p>
 * 复杂度分析
 * <p>
 * 时间复杂度 : (mn)(mn)。
 * 空间复杂度 : O(1)O(1)。
 * <p>
 * <p>
 * <p>
 * 方法二: 哈希表法
 * 遍历链表 A 并将每个结点的地址/引用存储在哈希表中。然后检查链表 B 中的每一个结点 b_ib
 * i
 * ​
 * 是否在哈希表中。若在，则 b_ib
 * i
 * ​
 * 为相交结点。
 * <p>
 * 复杂度分析
 * <p>
 * 时间复杂度 : O(m+n)O(m+n)。
 * 空间复杂度 : O(m)O(m) 或 O(n)O(n)。
 * <p>
 * <p>
 * <p>
 * 方法三：双指针法
 * 创建两个指针 pApA 和 pBpB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
 * 当 pApA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B); 类似的，当 pBpB 到达链表的尾部时，将它重定位到链表 A 的头结点。
 * 若在某一时刻 pApA 和 pBpB 相遇，则 pApA/pBpB 为相交结点。
 * 想弄清楚为什么这样可行, 可以考虑以下两个链表: A={1,3,5,7,9,11} 和 B={2,4,9,11}，相交于结点 9。 由于 B.length (=4) < A.length (=6)，pBpB 比 pApA 少经过 22 个结点，会先到达尾部。将 pBpB 重定向到 A 的头结点，pApA 重定向到 B 的头结点后，pBpB 要比 pApA 多走 2 个结点。因此，它们会同时到达交点。
 * 如果两个链表存在相交，它们末尾的结点必然相同。因此当 pApA/pBpB 到达链表结尾时，记录下链表 A/B 对应的元素。若最后元素不相同，则两个链表不相交。
 * 复杂度分析
 * <p>
 * 时间复杂度 : O(m+n)O(m+n)。
 * 空间复杂度 : O(1)O(1)。
 * <p>
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode/
 * <p>
 * 方法三的另一个说明
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/tu-jie-xiang-jiao-lian-biao-by-user7208t/
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }

}

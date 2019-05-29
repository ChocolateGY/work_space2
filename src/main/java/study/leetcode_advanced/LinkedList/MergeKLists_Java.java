package study.leetcode_advanced.LinkedList;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class MergeKLists_Java {
    public ListNode mergeKLists(ListNode[] lists) {
        if (!valid(lists))
            return null;

        ListNode head = null;

        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(1, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                heap.add(lists[i]);
        }

        ListNode min = heap.poll();
        head = min;
        min = min.next;

        if (min != null)
            heap.add(min);

        ListNode last = head;

        while (!heap.isEmpty()) {
            ListNode minNode = heap.poll();
            last.next = minNode;
            last = minNode;
            minNode = minNode.next;
            if (minNode != null)
                heap.add(minNode);
        }
        return head;
    }


    public boolean valid(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return false;
        int count = 0;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                count++;

        }
        if (count == 0)
            return false;
        return true;
    }


    /**
     * 分治的方法
     * 耗时最少
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return sort(lists, 0, lists.length - 1);
    }

    public ListNode sort(ListNode[] lists, int low, int high) {
        if (low >= high)
            return lists[low];
        int mid = (high - low) / 2 + low;
        ListNode l1 = sort(lists, low, mid);
        ListNode l2 = sort(lists, mid + 1, high);
        return merge(l1, l2);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(0);
        dummy.next = l1;
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l1 = l1.next;
            } else {
                ListNode next = l2.next;
                l2.next = cur.next;
                cur.next = l2;
                l2 = next;
            }
            cur = cur.next;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }

    /**
     * 方法3
     *
     * @param lists
     * @return
     */

    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        int k = lists.length;
        while (k > 1) {
            for (int i = 0; i < k / 2; i++)
                lists[i] = mergeTwoLists(lists[i], lists[i + (k + 1) / 2]);
            k = (k + 1) / 2;
        }
        return lists[0];
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode root = new ListNode(0);
        ListNode node = root;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        if (list1 == null)
            node.next = list2;
        if (list2 == null)
            node.next = list1;
        return root.next;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
    }
}

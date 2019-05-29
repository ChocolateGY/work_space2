package study.leetcode_advanced.LinkedList;

import java.util.HashMap;

/**
 * 复制带随机指针的链表
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 要求返回这个链表的深拷贝。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 * <p>
 * 解释：
 * 节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
 * 节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 你必须返回给定头的拷贝作为对克隆列表的引用。
 */
public class CopyRandomList {
    private HashMap<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val, cur.next, cur.random));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 时间最短
     * https://blog.csdn.net/whdAlive/article/details/80523375
     * 思路：
     * <p>
     * 在原链表中插入“影子节点”
     * 根据原节点random 更新影子节点的random
     * 分离原链表和“影子链表”，该链表即为深度拷贝的链表
     *
     * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        Node cur = head;
        //在原链表中插入“影子节点”
        while (cur != null) {
            Node node = new Node(cur.val, null, null);
            node.next = cur.next;
            cur.next = node;
            cur = cur.next.next;
        }

        //根据原节点random 更新影子节点的random
        Node first = head;
        Node second = head.next;
        while (first != null) {
            second = first.next;
            if (first.random != null) second.random = first.random.next;
            first = second.next;

        }
        //分离原链表和“影子链表”，该链表即为深度拷贝的链表
        Node ans = head.next;
        first = head;
        second = head.next;
        while (first != null && first.next != null) {
            second = first.next;
            first.next = second.next;
            first = second;
        }
        return ans;
    }

    /**
     * 时间第二
     * 逻辑和上面一样
     * @param head
     * @return
     */
    public Node copyRandomList3(Node head) {
        if (head == null) {
            return null;
        }
        Node node = head;

        while (node != null) {
            Node clone = new Node(node.val, node.next, node.random);
            clone.next = node.next;
            node.next = clone;
            node = clone.next;
        }

        node = head;
        while (node != null) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }

        node = head;
        Node retNode = head.next;
        while (node.next != null) {
            Node nextNode = node.next;
            node.next = nextNode.next;
            node = nextNode;
        }

        return retNode;
    }
}

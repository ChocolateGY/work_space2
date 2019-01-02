package study.DataStructure;

/**
 * 链表
 * 只保留一个对链表的头节点的引用。
 * Created by root on 2019-1-2.
 */
public class LinkedList {
    private Link list;


    public void insert(int data) {
        Link node = new Link(data);
        if (list == null)
            list = node;
        else {
            node.setNext(list);
            list = node;
        }
    }

    public void display() {
        Link current = list;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.display();
    }
}

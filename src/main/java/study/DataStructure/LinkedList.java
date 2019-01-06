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

    /**
     * 查找结点
     *
     * @param num
     * @return
     */

    public Link find(int num) {
        Link cur = list;
        while (cur != null) {
            if (cur.getData() == num)
                return cur;
            else
                cur = cur.getNext();
        }
        return null;
    }

    /**
     * 插入数据到指定位置
     *
     * @param num
     * @param pos
     */
    public void insert(int num, int pos) {
        if (pos == 0) {
            insert(num);
        } else {
            Link cur = list;
            Link node = new Link(num);
            for (int i = 0; i < pos - 1; i++) {
                cur = cur.getNext();
            }
            node.setNext(cur.getNext());
            cur.setNext(node);
        }
    }

    /**
     * 删除指定节点
     * @param num
     */
    public void delete (int num){
        Link cur = list;
        Link before = list;
        while(cur!=null){
            if(cur.getData() == num)
                break;
            else{
                before = cur;
                cur = cur.getNext();
            }
        }
        if(cur!=null){
            if(cur==list)
                list = list.getNext();
            else
                before.setNext(cur.getNext());
        }
    }
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.display();
        Link res = list.find(4);
        System.out.println(res.getData());
        list.insert(5, 1);
        list.display();
        System.out.println();
        list.delete(5);
        list.display();
    }
}

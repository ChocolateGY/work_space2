package study.DataStructure;

/**
 * 链接点
 * 分两个域：数据域和引用域（相当于指针）
 */
public class Link {

    //数据域
    private int data;
    //引用域
    private Link next;

    public Link(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    public static void main(String[] args) {
        Link link1 = new Link(10);
        Link link2 = new Link(11);
        Link link3 = new Link(12);
        Link link4 = new Link(13);
        link1.setNext(link2);
        link2.setNext(link3);
        link3.setNext(link4);
        System.out.println(link1.getData());
        System.out.println(link1.getNext().getData());
        System.out.println(link1.getNext().getNext().getData());
    }
}

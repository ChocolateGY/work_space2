package study.DataStructure;

class MyQueue {
    //存储数据
    private int[] arr;
    //创建数组大小
    private int size;
    //有效元素大小
    private int elem;
    //队头
    private int font;
    //队尾
    private int end;

    public MyQueue() {
        size = 50;
        init();
    }

    public MyQueue(int size) {
        this.size = size;
        init();
    }

    private void init() {
        arr = new int[size];
        end = -1;
        elem = 0;
    }

    //插入
    public void insert(int num) {
        arr[++end] = num;
        elem++;
    }

    //删除
    public int remove() {
        elem--;
        return arr[font++];
    }

    //是否为空
    public boolean isEmpty() {
        return elem == 0;
    }

    //是否已满
    public boolean isFull() {
        return end == size - 1;
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }
}


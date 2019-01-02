package study.DataStructure;

/**
 * 循环队列
 *
 */
public class LoopQueue {
    //数据
    private int[] arr;
    //数组大小
    private int size;
    //头指针
    private int font;
    //尾指针
    private int end;
    //有效数据大小
    private int elem;

    public LoopQueue() {
        size = 50;
        init();
    }

    public LoopQueue(int size) {
        this.size = size;
        init();
    }

    private void init() {
        arr = new int[size];
        end = -1;
        font = -1;
        elem = 0;
    }

    /**
     * 插入
     * 当尾指针达到尾部时，重新指向-1
     * @param num
     */
    public void insert(int num) {
        if (end == size - 1)
            end = -1;
        arr[++end] = num;
        elem++;
    }

    /**
     * 移除
     * 当头指针指向队尾时，重新指向-1
     * @return
     */
    public int remove() {
        if (font == size - 1)
            font = -1;
        elem--;
        return arr[++font];
    }

    public boolean isEmpty() {
        return elem == 0;
    }

    public boolean isFull() {
        return elem == size;
    }

    public static void main(String[] args) {
        LoopQueue qu = new LoopQueue(5);
        qu.insert(1);
        qu.insert(2);
        qu.insert(3);
        qu.insert(4);
        qu.insert(5);
        qu.insert(5);
        while(!qu.isEmpty()){
            System.out.println(qu.remove());
        }
        System.out.println(qu.isEmpty());
    }
}

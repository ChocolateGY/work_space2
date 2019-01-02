package study.DataStructure;

/**
 * 优先队列
 * 放入按照大小顺序
 */
public class PriorityQueue {
    private int[] arr;
    private int elem;
    private int size;

    public PriorityQueue() {
        size = 50;
        init();
    }

    public PriorityQueue(int size) {
        this.size = size;
        init();
    }

    private void init() {
        arr = new int[size];
    }

    public void insert(int num) {
        int i;
        for (i = 0; i < elem; i++) {
            if (arr[i] > num)
                break;
        }
        for (int j = elem; j > i; j--) {
            arr[j] = arr[j - 1];
        }
        arr[i] = num;
        elem++;
    }

    public int remove() {
        return arr[--elem];
    }

    public boolean isEmpty() {
        return elem == 0;
    }


    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        queue.insert(4);
        queue.insert(1);
        queue.insert(3);
        queue.insert(2);
        while (!queue.isEmpty()){
            System.out.println(queue.remove());
        }
    }

}

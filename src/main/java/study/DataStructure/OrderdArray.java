package study.DataStructure;

/**
 * 有序数组
 */
public class OrderdArray {
    private int[] arr;
    private int size;
    private int elem;

    public OrderdArray() {
        size = 50;
        init();
    }

    public OrderdArray(int size) {
        this.size = size;
        init();
    }

    private void init() {
        arr = new int[size];
        elem = 0;
    }

    /**
     * 从前往后查找比该元素大的位置。
     * 从该位置开始的数据后移一位
     * @param num
     */
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

    public void display() {
        for (int i = 0; i < elem; i++) {
            System.out.println(arr[i]);
        }
        System.out.println();
    }

    public int find(int num) {
        for (int i = 0; i < elem; i++) {
            if (arr[i] == num)
                return i;
        }
        return -1;
    }

    /**
     * 有序数组可以使用二分查找
     * @param num
     * @return
     */
    public int binaryFind(int num) {
        int low = 0;
        int high = elem;

        while (low <= high) {
            int mid = (low + high) >> 1;
            //防止溢出
            //int mid = low+(high-low)/2;
            if (arr[mid] > num) {
                high = mid - 1;
            } else if (arr[mid] < num) {
                low = mid + 1;
            } else if (arr[mid] == num)
                return mid;

        }
        return -1;
    }

    /**
     * 删除
     * 找到该元素位置，然后该位置之后的数据全部左移一位
     * @param num
     */
    public void remove(int num) {
        int index = find(num);
        if (index == -1)
            System.out.println("not have this num");
        else {
            for (int i = index; i < elem; i++) {
                arr[i] = arr[i + 1];
            }
            elem--;
        }
    }

    public void chang(int old, int newV) {
        int index = find(old);
        if (index == -1)
            System.out.println("not have this num");
        else
            arr[index] = newV;
    }

    public static void main(String[] args) {
        OrderdArray arr = new OrderdArray(10);
        arr.insert(1);
        arr.insert(4);
        arr.insert(3);
        arr.insert(2);
        arr.insert(5);
        arr.insert(8);
        arr.insert(9);
        arr.display();
//        System.out.println(arr.find(4));
        System.out.println(arr.find(5));
        System.out.println(arr.binaryFind(5));
//        arr.remove(4);
//        arr.display();
//        arr.chang(3, 6);
//        arr.display();

    }
}

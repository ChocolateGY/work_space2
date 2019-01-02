package study.DataStructure;

/**
 * 数组
 */
public class MyArray {
    //数据
    private int[] arr;
    //数组大小
    private int size;
    //有效数据大小
    private int elem;

    public MyArray() {
        size = 50;
        init();
    }

    public MyArray(int size) {
        this.size = size;
        init();
    }
//初始化
    private void init() {
        arr = new int[size];
        elem = 0;
    }

    /**
     * 插入
     * @param num
     */
    public void insert(int num) {
        arr[elem++] = num;
    }

    /**
     * 显示
     */
    public void display() {
        for (int i = 0; i < elem; i++) {
            System.out.println(arr[i]);
        }
        System.out.println();
    }

    /**
     * 根据元素寻找索引
     * @param num
     * @return
     */
    public int find(int num) {
        for (int i = 0; i < elem; i++) {
            if (arr[i] == num)
                return i;
        }
        return -1;
    }

    /**
     * 删除
     * 首先找到该元素的索引位置，再将该索引后的所有数据左移
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

    /**
     * 修改
     * 首先找到元素的索引，然后更改新值
     * @param old
     * @param newV
     */
    public void chang(int old, int newV) {
        int index = find(old);
        if (index == -1)
            System.out.println("not have this num");
        else
            arr[index] = newV;
    }

    public static void main(String[] args) {
        MyArray arr = new MyArray(10);
        arr.insert(1);
        arr.insert(2);
        arr.insert(3);
        arr.insert(4);
        arr.display();
        System.out.println(arr.find(4));
        System.out.println(arr.find(5));
        arr.remove(4);
        arr.display();
        arr.chang(3,6);
        arr.display();

    }
}

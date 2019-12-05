package study.DataStructuresAndAlgorithmAnalysisInJava.Sort;

import org.bouncycastle.util.Arrays;

public class java_sort8_Algorithms4 {
    public static void main(String[] args) {
        int[] arr = {4, 5, 7, 8, 1, 2, 3, 6};
//        selectSort(arr);
//        insertSort(arr);
//        shellSort(arr);

        //这三个有问题
//        merge(arr, 0, (arr.length - 1) / 2, arr.length - 1);
//        topMergeSort(arr);
//        bottomMergeSort(arr);

//        qsort(arr);
//        quickSort(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void exch(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //选择排序
    public static void selectSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min])
                    min = j;
            }
            exch(arr, i, min);
        }
    }

    //插入排序
    public static void insertSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                exch(arr, j, j - 1);
            }
        }
    }

    //希尔排序

    /**
     * 基于插入排序，大规模的插入排序很慢，因为只能交换相邻节点。
     * 希尔排序为了加快插入排序，交换不相邻的元素来对数组进行局部排序。
     * 思想就是使数组中任意间隔为h的元素都是有序的。
     */
    public static void shellSort(int[] arr) {
        int n = arr.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1; // 1, 4, 13 ,40
        while (h >= 1) {
            //将数组变为h有序
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && arr[j] < arr[j - 1]; j -= h) {
                    exch(arr, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    /**
     * 归并排序
     */
    //原地归并方法，需要额外数组
    public static void merge(int[] arr, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        int[] aux = Arrays.clone(arr);
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                arr[k] = aux[j++];
            else if (j > hi)
                arr[k] = aux[i++];
            else if (arr[j] < arr[i])
                arr[k] = aux[j++];
            else
                arr[k] = aux[i++];
        }
    }

    //自顶向下归并
    public static void topMergeSort(int[] a) {
        topSort(a, 0, a.length - 1);
    }

    private static void topSort(int[] arr, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        topSort(arr, lo, mid);
        topSort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    //自底向上归并
    public static void bottomMergeSort(int[] a) {
        int n = a.length;
        for (int sz = 1; sz < n; sz = sz + sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }

    /**
     * 快速排序
     */
    public static void qsort(int[] a) {
        qsort(a, 0, a.length - 1);
    }

    public static void qsort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        qsort(a, lo, j - 1);
        qsort(a, j + 1, hi);
    }

    public static int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        int v = a[lo];
        while (true) {
            while (a[++i] < v) {
                if (i == hi)
                    break;
            }
            while (v < a[--j]) {
                if (j == lo)
                    break;
            }
            if (i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    //三向切分
    public static void quickSort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        int v = a[lo];
        while (i <= gt) {
            if (a[i] < v)
                exch(a, lt++, i++);
            else if (a[i] > v)
                exch(a, i, gt--);
            else
                i++;
        }
        quickSort(a, lo, lt - 1);
        quickSort(a, gt + 1, hi);
    }
}

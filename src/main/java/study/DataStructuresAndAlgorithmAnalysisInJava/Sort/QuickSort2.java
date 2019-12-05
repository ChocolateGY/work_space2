package study.DataStructuresAndAlgorithmAnalysisInJava.Sort;

/**
 * 快速排序
 *
 * 这里修正后，可以使用
 */
public class QuickSort2 {
    public static void main(String[] args) {
        int[] arr = {4, 5, 7, 8, 1, 2, 3, 6};
        quicksort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * QuickSort algorithm.
     */
    public static void quicksort(int[] a) {
        quicksort(a, 0, a.length - 1);
    }

    /**
     * Return median of left,cneter,and right.
     * Order these and hide the pivot
     */
    private static int median3(int[] a, int left, int right) {
        int center = (left + right) / 2;
        //过程可以理解为一个小的冒泡排序
        if (a[center] < a[left])
            swapReferences(a, left, center);
        if (a[right] < a[left])
            swapReferences(a, right, left);
        if (a[right] < a[center])
            swapReferences(a, right, center);

        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    private static int CUTOFF = 10;

    private static void quicksort(int[] a, int left, int right) {

//        if (left + CUTOFF <= right) {
        if (left < right) {
            int pivot = median3(a, left, right);

            //Begin partitioning
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i] < pivot) {
                }
                //这里加判断条件
                while (j > left && a[--j] > pivot) {
                }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }
            //这里加判断条件
            if (i < right) swapReferences(a, i, right - 1);
            quicksort(a, left, i - 1);
            quicksort(a, i + 1, right);
        }
//        } else
//            insertionSort(a, left, right);
    }

    private static void insertionSort(int[] a, int left, int right) {
        int j;
        for (int i = left + 1; i < right; i++) {
            int temp = a[i];
            for (j = i; j >= left && temp < a[j - 1]; j--) {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
    }

    private static void swapReferences(int[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

}

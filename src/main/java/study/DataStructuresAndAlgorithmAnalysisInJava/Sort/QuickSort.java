package study.DataStructuresAndAlgorithmAnalysisInJava.Sort;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * QuickSort algorithm.
     *
     * @param a   an array of comparable items.
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void quicksort(T[] a) {
        quicksort(a, 0, a.length - 1);
    }

    /**
     * Return median of left,cneter,and right.
     * Order these and hide the pivot
     */
    private static <T extends Comparable<? super T>> T median3(T[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);
        if (a[right].compareTo(a[center]) < 0)
            swapReferences(a, right, center);
        if (a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);

        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    private static int CUTOFF = 10;

    private static <T extends Comparable<? super T>> void quicksort(T[] a, int left, int right) {

        if (left + CUTOFF <= right) {
            T pivot = median3(a, left, right);

            //Begin partitioning
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }
            swapReferences(a, i, right - 1);
            quicksort(a, left, i - 1);
            quicksort(a, i + 1, right);
        } else
            insertionSort(a, left, right);
    }

    private static <T extends Comparable<? super T>> void insertionSort(T[] a, int left, int right) {
        int j;
        for (int i = left + 1; i < right; i++) {
            T temp = a[i];
            for (j = i; j >= left && temp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
    }

    private static <T extends Comparable<? super T>> void swapReferences(T[] a, int index1, int index2) {
        T temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

}

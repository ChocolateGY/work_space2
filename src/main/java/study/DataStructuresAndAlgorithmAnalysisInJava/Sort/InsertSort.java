package study.DataStructuresAndAlgorithmAnalysisInJava.Sort;

public class InsertSort {
    /**
     * Simple insertion sort.
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {
        int j;
        for (int p = 1; p < a.length; p++) {
            AnyType tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];

            a[j] = tmp;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{4, 2, 1, 3, 5, 7};
        insertionSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        int ar1r[] = { 12,3,4,};
    }
}

package study.DataStructuresAndAlgorithmAnalysisInJava.Sort;

public class MergeSort {
    /**
     *  归并排序 MergeSort algorithm
     * @param a an array of Comparable items
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
        T[] tmpArray = (T[]) new Comparable[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    /**
     * Internal methods that makes recursive calls.
     * @param a an array of Comparable items
     * @param tmpArray an array to place the merged result.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    /**
     * Internal method that merges two sorted halves of a subarray.
     * @param a an array of Comparable items.
     * @param tmpArray an array to place the merged result.
     * @param leftPos the left-most index of the subarray.
     * @param rightPos the index of the start of the second half.
     * @param rightEnd the right-most index of the subarray.
     */
    private static <T extends Comparable<? super T>> void merge(T[] a, T[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        //Main Loop
        while (leftPos <= leftEnd && rightPos <= rightEnd)
            if (a[leftPos].compareTo(a[rightPos]) <= 0)
                tmpArray[tmpPos++] = a[leftPos++];
            else
                tmpArray[tmpPos++] = a[rightPos++];
        while (leftPos <= leftEnd)
            tmpArray[tmpPos++] = a[leftPos++];

        while (rightPos <= rightEnd)
            tmpArray[tmpPos++] = a[rightPos++];

        //Copy tmpArray back
        for (int i = 0; i < numElements; i++, rightEnd--)
            a[rightEnd] = tmpArray[rightEnd];
    }
}

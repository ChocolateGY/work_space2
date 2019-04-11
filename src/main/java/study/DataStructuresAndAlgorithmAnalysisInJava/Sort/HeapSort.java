package study.DataStructuresAndAlgorithmAnalysisInJava.Sort;

public class HeapSort {
    /**
     * Internal method for heapsort
     *
     * @param i
     * @return
     */
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Internal method for heapsort that is used in deleteMax and buildHeadp.
     *
     * @param a
     * @param i
     * @param n
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void percDown(T[] a, int i, int n) {
        int child;
        T temp;
        for (temp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
                child++;
            if (temp.compareTo(a[child]) < 0)
                a[i] = a[child];
            else
                break;
        }
        a[i] = temp;
    }

    /**
     * Standard heapsort
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void heapsort(T[] a) {
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            percDown(a, i, a.length);
        }
        for (int i = a.length - 1; i > 0; i--) {
            swapReference(a, 0, i);
            percDown(a, 0, i);
        }
    }

    private static <T extends Comparable<? super T>> void swapReference(T[] a, int i, int n) {
        T temp = a[i];
        a[i] = a[n];
        a[n] = temp;
    }

    public static void main(String[] args) {
        Integer arr[] = {6, 5, 4, 3, 2, 1};
        heapsort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}

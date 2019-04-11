import org.junit.Test;
import study.DataStructuresAndAlgorithmAnalysisInJava.Sort.MergeSort;
import study.DataStructuresAndAlgorithmAnalysisInJava.Sort.RadixSortA;

public class SortTest {
    @Test
    public void testMergeSort() {
        Integer[] arr = {6, 5, 4, 3, 2, 1};
        MergeSort.mergeSort(arr);
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }
    @Test
    public void testRadixSort() {
        String [] arr = {"aguan","bguan","ayu"};
        RadixSortA.rdadixSortA(arr,3);
        for (String a : arr) {
            System.out.print(a + " ");
        }
    }
}

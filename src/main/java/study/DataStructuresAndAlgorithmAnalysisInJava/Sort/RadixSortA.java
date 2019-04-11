package study.DataStructuresAndAlgorithmAnalysisInJava.Sort;

import java.util.ArrayList;

public class RadixSortA {
    /**
     * Radix sort an array of Strings.
     * Assume all are all ASCII
     * Assume all have same length
     *
     * @param arr
     * @param stringlen
     */
    public static void rdadixSortA(String[] arr, int stringlen) {
        final int BUCKETS = 256;
        ArrayList<String>[] buckets = new ArrayList[BUCKETS];

        for (int i = 0; i < BUCKETS; i++)
            buckets[i] = new ArrayList<>();

        for (int pos = stringlen - 1; pos >= 0; pos--) {
            for (String s : arr)
                buckets[s.charAt(pos)].add(s);

            int idx = 0;
            for (ArrayList<String> thisBucket : buckets) {
                for (String s : thisBucket)
                    arr[idx++] = s;

                thisBucket.clear();
            }
        }
    }

    /**
     * Counting radix sort an arry of Strings
     * Assume all are all ASCII
     * Assume al have same length
     * @param arr
     * @param stringlen
     */
    public static void countingRadixSort(String[] arr, int stringlen) {
        final int BUCKETS = 256;
        int N = arr.length;
        String[] buffer = new String[N];
        String[] in = arr;
        String[] out = buffer;
        for (int pos = stringlen - 1; pos >= 0; pos--) {
            int[] count = new int[BUCKETS + 1];
            for (int i = 0; i < N; i++)
                count[in[i].charAt(pos) + 1]++;

            for (int b = 1; b <= BUCKETS; b++)
                count[b] += count[b - 1];

            for (int i = 0; i < N; i++)
                out[count[in[i].charAt(pos)]++] = in[i];

            //swap in and out roles
            String[] tmp = in;
            in = out;
            out = tmp;

        }

        // if odd number of passes,in is buffer, out is arr; so copy back
        if (stringlen % 2 == 1)
            for (int i = 0; i < arr.length; i++)
                out[i] = in[i];
    }
}

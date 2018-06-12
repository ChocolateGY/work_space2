package study.leetcode4Java.Array;

/**
 * Created by root on 2018-5-30.
 */
public class Array1 {
    public int removeDuplicates(int[] A) {
        if(A == null || A.length==0)
            return 0;
        int index = 1;
        for(int i=1;i<A.length;i++)
        {
            if(A[i]!=A[i-1])
            {
                A[index]=A[i];
                index++;
            }
        }
        return index;
    }
}

package study.leetcode_primary.Others;

/**
 * Created by root on 2018-8-6.
 */
public class HanmingWeight_java {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        long num = n & 0xffffffffl;
        for(int i = 0 ; i < 32 ; i ++) {
            res += (num%2);
            num /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new HanmingWeight_java().hammingWeight(new Long(2147483648l).intValue()));
    }
}

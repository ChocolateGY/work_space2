package study.leetcode4Java.String;

/**
 * Created by root on 2018-6-3.
 */
public class String1 {
    public String reverseString(String s) {
        Integer n = s.length();
        char[] cs = s.toCharArray();
        char[] a = new char[n];
        for (int x = 0; x < n; x++) {
            a[x] = cs[n - 1 - x];
        }
        return String.valueOf(a);
    }
}

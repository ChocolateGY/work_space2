package study.leetcode4Java.String;

/**
 * Created by root on 2018-6-4.
 */
public class String3 {
    /**
     *
     * 超时
     * @param s
     * @return
     */
    public int fristUniqChar(String s) {
        char[] chars = s.toCharArray();
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            int count = 0;
            for (int j = 0; j < s.length(); j++) {
                if (chars[i] == chars[j] && i != j) {
                    count++;
                }
            }
            if (count == 0)
                return i;

        }
        if (s.length() != 1)
            return index;
        else
            return 1;
    }
}

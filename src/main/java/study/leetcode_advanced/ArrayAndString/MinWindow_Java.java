package study.leetcode_advanced.ArrayAndString;
/**
 * 最小窗口子字符串
 * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
public class MinWindow_Java {
    /*public String minWindow(String s, String t) {
        char[] tChars = t.toCharArray();
        int[] hash = new int[256];
        char[] sChars = s.toCharArray();
        for (char tChar : tChars) {
            hash[tChar]++;
        }
        int l = 0;
        int r = 0;
        int match = tChars.length;
        String res = "";
        int min = Integer.MAX_VALUE;
        while (r != sChars.length) {
            hash[sChars[r]]--;
            if (hash[sChars[r]] >= 0) {
                match--;
            }
            while (match == 0) {
                while (hash[sChars[l]] < 0) {
                    hash[sChars[l++]]++;
                }
                int len = r - l + 1;
                if (min > len) {
                    res = new String(sChars, l,  len);
                    min = len;
                }
                match++;
                hash[sChars[l++]]++;
            }
            r++;
        }
        return res;
    }*/
    public String minWindow(String s, String t) {
        int m=s.length(),n=t.length();
        char[] s1=s.toCharArray();
        char[] t1=t.toCharArray();

        if(m<n){
            return "";
        }
        int freq[] =new int [256];
        for(int i=0;i<n;i++){
            freq[t1[i]]++;
        }
        int min_l=-1;
        int l=0,r=0;//滑动窗口[L,R]
        int count=0;//保存窗口里已经找到了多少字符
        int min_size=m+1;
        while(l<=m-n&&r<m){
            freq[s1[r]]--;
            if(freq[s1[r]]>=0){
                count++;
            }
            if(count==n){
                while(freq[s1[l]]<0){//L尽量往左移动
                    freq[s1[l]]++;
                    l++;
                }
                if(r-l<min_size){//记录位置
                    min_size=r-l;
                    min_l=l;
                }
                freq[s1[l++]]++;//L继续右移一位，进行下次寻找
                count--;
            }
            r++;
        }
        if(min_size<m+1){
            return s.substring(min_l,min_l+min_size+1);
        }
        return "";
    }

}

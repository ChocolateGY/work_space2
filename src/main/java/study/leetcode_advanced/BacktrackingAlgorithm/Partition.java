package study.leetcode_advanced.BacktrackingAlgorithm;


import java.util.ArrayList;
import java.util.List;

/**
 * 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * <p>
 * 返回 s 所有可能的分割方案。
 * <p>
 * 示例:
 * <p>
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 */
public class Partition {
    List<List<String>> listList = new ArrayList<>();

    public List<List<String>> partition(String s) {
        nextWords(s, 0, new ArrayList<>());
        return listList;
    }

    private void nextWords(String s, int index, List<String> list) {
        if (index == s.length()) {
            listList.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            String subStr = s.substring(index, i + 1);
            if (isPalindrome(subStr)) {
                list.add(subStr);
                nextWords(s, i + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        for (int i = 0; i <= s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 时间短
     */
    public List<List<String>> partition2(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.length() == 0)
            return ans;
        backtracking(ans, s, new ArrayList<>());
        return ans;
    }

    private void backtracking(List<List<String>> ans, String s, List<String> combine) {
        if (s.length() == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (isPalindrome(s, 0, i)) {//如果0到i是回文
                combine.add(s.substring(0, i + 1));
                backtracking(ans, s.substring(i + 1), combine);//剩余部分再回溯
                combine.remove(combine.size() - 1);//删除最后一个元素，而非最后一个字符
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> res = new Partition().partition2(s);
        for (List<String> list : res) {
            for (String str : list) {
                System.out.print(str+" ");
            }
            System.out.println();
        }
    }
}

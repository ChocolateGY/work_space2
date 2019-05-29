package study.leetcode_advanced.TreeAndGraph;

import java.util.*;

public class LadderLength_java {


    /**
     * SPFA算法求最短路径
     * 无权图的最短路径依旧能用SPFA来解决，只不过每条边的边权为1。
     *
     * 期望时间复杂度是O(kM)，其中k是一个常数，在很多情况下k不超过2，M是图的边数，
     * 可见这个算法异常高效，并且经常性地优于堆优化的Dijkstra算法。
     */
    private boolean[][] graph;
    private int[] d;
    private final int INF = 1000000000;
    private int[] countInq;
    private boolean[] inq;
    private int size;

    public int ladderLength(String beginWord,  String endWord, List<String> wordList) {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(wordList);
        hashSet.add(beginWord);
        if (!hashSet.contains(endWord)) {
            return 0;
        }
        List<String> list = new ArrayList<>();
        int index = 0;
        int start = 0, end = 0;
        for (String s : hashSet) {
            list.add(s);
            if (s.equals(beginWord)) {
                start = index;
            }
            if (s.equals(endWord)) {
                end = index;
            }
            index++;
        }
        size = list.size();
        graph = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (hashPath(list.get(i), list.get(j))) {
                    graph[i][j] = graph[j][i] = true;
                }
            }
        }
        d = new int[size];
        Arrays.fill(d, INF);
        countInq = new int[size];
        Arrays.fill(countInq, 0);
        inq = new boolean[size];
        spfa(start);
        if (INF == d[end]) {
            return 0;
        }
        return d[end] + 1;
    }

    private boolean hashPath(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        if (1 == count) {
            return true;
        }
        return false;
    }

    private boolean spfa(int s) {
        d[s] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        countInq[s]++;
        inq[s] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            inq[u] = false;
            for (int v = 0; v < size; v++) {
                if (graph[u][v] && d[u] + 1 < d[v]) {
                    d[v] = d[u] + 1;
                    if (!inq[v]) {
                        queue.add(v);
                        countInq[v]++;
                        inq[v] = true;
                        if (countInq[v] > size - 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }




    public int ladderLength2(String beginWord, String endWord, List<String> wordDict){
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>(), wordSet = new HashSet<>(wordDict), visited = new HashSet<>();
        if (!wordDict.contains(endWord)){
            return 0;
        }
        int len = 1, strLen = beginWord.length();
        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()){
            if (beginSet.size() > endSet.size()){
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            Set<String> tempSet = new HashSet<>();
            for (String s : beginSet){
                char[] chars = s.toCharArray();
                for (int i = 0; i < strLen; i++){
                    char old = chars[i];
                    for (char j = 'a'; j <= 'z'; j++){
                        chars[i] = j;
                        String temp = new String(chars);
                        if (endSet.contains(temp)){
                            return len+1;
                        }
                        if (!visited.contains(temp) && wordSet.contains(temp)){
                            tempSet.add(temp);
                            visited.add(temp);
                        }
                    }
                    chars[i] = old;
                }
            }
            beginSet = tempSet;
            len++;
        }
        return 0;
    }

    /**
     * 用时最短
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) return 0;
        HashSet<String> start = new HashSet<>();
        HashSet<String> end = new HashSet<>();
        HashSet<String> dic = new HashSet<>(wordList);
        start.add(beginWord);
        end.add(endWord);
        if (!dic.contains(endWord)) return 0;
        return bfs(start, end, dic, 2);

    }

    public static int bfs(HashSet<String> st, HashSet<String> ed, HashSet<String> dic, int l) {
        if (st.size() == 0) return 0;
        if (st.size() > ed.size()) {
            return bfs(ed, st, dic, l);
        }
        dic.removeAll(st);
        HashSet<String> next = new HashSet<>();
        for (String s : st) {
            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char tmp = arr[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (tmp == c) continue;
                    arr[i] = c;
                    String nstr = new String(arr);
                    if (dic.contains(nstr)) {
                        if (ed.contains(nstr)) return l;
                        else next.add(nstr);
                    }
                }
                arr[i] = tmp;
            }
        }
        return bfs(next, ed, dic, l + 1);
    }
}

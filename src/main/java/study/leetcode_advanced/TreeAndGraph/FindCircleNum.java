package study.leetcode_advanced.TreeAndGraph;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Friend Circles
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * <p>
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [[1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 * 示例 2:
 * <p>
 * 输入:
 * [[1,1,0],
 * [1,1,1],
 * [0,1,1]]
 * 输出: 1
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 * 注意：
 * <p>
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 */
public class FindCircleNum {
    public int findCircleNum(int[][] M) {
        int res = 0;

        if (M == null || M.length == 0 || M[0].length == 0) {
            return 1;
        }
        int n = M.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visit[i]) {
                continue;
            }

            queue.offer(i);
            while (!queue.isEmpty()) {
                int pop = queue.poll();
                visit[pop] = true;
                for (int j = 0; j < n; j++) {
                    if (M[pop][j] == 1 && !visit[j] && pop != j) {
                        queue.offer(j);
                    }
                }
            }
            res++;
        }
        return res;
    }

    /**
     * 时间最短
     * @param M
     * @return
     */
    public int findCircleNum2(int[][] M) {
//         法一：dfs
        boolean[] visited=new boolean[M.length];
//         遍历每个点进行dfs
        int res=0;
        for(int i=0;i<M.length;i++){
            if(!visited[i]){
                dfs(M,visited,i);
                res+=1;
            }
        }
        return res;
    }
    public void dfs(int[][] M,boolean[] visited,int i){
//         i表示当前是第i个学生
        for(int j=0;j<M.length;j++){
            if(!visited[j] && M[i][j]==1){
                visited[j]=true;
//                 dfs过程
                dfs(M,visited,j);
            }
        }
    }

    public static void main(String[] args) {
        int[][] a = {{1,1,0},{1,1,0},{0,0,1}};
        FindCircleNum f = new FindCircleNum();
        int res = f.findCircleNum2(a);
        System.out.println(res);
    }
}
package study.leetcode_advanced.TreeAndGraph;

/**
 * 矩阵中的最长递增路径
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * <p>
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums =
 * [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 * 示例 2:
 * <p>
 * 输入: nums =
 * [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 */
public class LongestIncreasingPath {
    /**
     * 1.
     */
    public int n, m;
    public int[][] dr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[][] result = new int[1000][1000];

    public boolean check(int x, int y, int nx, int ny, int[][] matrix) {
        return nx >= 0 && ny >= 0 && nx < n && ny < m && matrix[nx][ny] > matrix[x][y];
    }

    public int dfs(int x, int y, int[][] matrix) {
        int max = 0;
        if (result[x][y] != 1)
            return result[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dr[i][0];
            int ny = y + dr[i][1];
            if (check(x, y, nx, ny, matrix))
                result[x][y] = Math.max(result[x][y], dfs(nx, ny, matrix) + 1);
        }
        return result[x][y];
    }

    public int longestIncreasingPath(int[][] matrix) {
        n = matrix.length;
        if (n == 0)
            return 0;
        m = matrix[0].length;
        int ans = 0;
        for (int i = 0; i < 1000; i++)
            for (int j = 0; j < 1000; j++)
                result[i][j] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, dfs(i, j, matrix));
            }
        }
        return ans;
    }

    /**
     * 2.
     * @param matrix
     * @return
     */

    public int longestIncreasingPath2(int[][] matrix) {
        if (matrix == null) return 0;
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int maxLength = 0;
//         保存从该点出发的最长路径
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int temp = dfs(matrix, i, j, dp);
                if (maxLength < temp) maxLength = temp;
            }
        }
        return maxLength;
    }

    public int dfs(int[][] matrix, int i, int j, int[][] dp) {
//         由于走的是递增路径，不会存在重复访问情况
//         若该点访问过，直接返回记忆的值
        if (dp[i][j] != 0) return dp[i][j];
        int m = matrix.length;
        int n = matrix[0].length;
        int maxLength = 0;
        if (j > 0 && matrix[i][j - 1] > matrix[i][j]) {
            maxLength = Math.max(maxLength, dfs(matrix, i, j - 1, dp));
        }
        if (j < n - 1 && matrix[i][j + 1] > matrix[i][j]) {
            maxLength = Math.max(maxLength, dfs(matrix, i, j + 1, dp));
        }
        if (i > 0 && matrix[i - 1][j] > matrix[i][j]) {
            maxLength = Math.max(maxLength, dfs(matrix, i - 1, j, dp));
        }
        if (i < m - 1 && matrix[i + 1][j] > matrix[i][j]) {
            maxLength = Math.max(maxLength, dfs(matrix, i + 1, j, dp));
        }
        dp[i][j] = maxLength + 1;
        return dp[i][j];
    }


    /**
     * 3.
     * dp[i][j]代表以i,j为起点的最长的递增长度，防止重复计算（因为他的子路线肯定被算过了 当我们遍历到子路线的点是就不用计算了）
     * dfs 深度搜索 遍历四个方向 只要不出界下个值比当前值大 则dfs子路线的最大长度数加一。
     */
    private int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private int[][] dp;

    public int dfs(int[][] matrix, int[][] dp, int i, int j) {
        if (dp[i][j] > 0)  //已经计算过的直接返回即可
            return dp[i][j];
        int n = matrix.length, m = matrix[0].length;
        int max = 1;
        for (int k = 0; k < 4; k++) {
            int x = dir[k][0] + i;
            int y = dir[k][1] + j;
            if (x < 0 || y < 0 || x >= n || y >= m || matrix[x][y] <= matrix[i][j])
                continue;
            int len = 1 + dfs(matrix, dp, x, y);
            max = Math.max(len, max);
        }
        dp[i][j] = max;
        return max;
    }

    public int longestIncreasingPath3(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length, m = matrix[0].length;
        dp = new int[n][m];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(dfs(matrix, dp, i, j), max);
            }
        }
        return max;
    }
}

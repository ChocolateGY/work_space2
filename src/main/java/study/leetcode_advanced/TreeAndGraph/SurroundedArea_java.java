package study.leetcode_advanced.TreeAndGraph;

/**
 * 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例:
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * <p>
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class SurroundedArea_java {
    int dep, wid;
    char[][] map;

    public void dsf(int depth, int width) {
        if (depth < 0 || depth >= dep || width < 0 || width >= wid || map[depth][width] != 'O') return;
        map[depth][width] = 'Q';
        dsf(depth - 1, width);
        dsf(depth + 1, width);
        dsf(depth, width - 1);
        dsf(depth, width + 1);
    }

    public void solve(char[][] board) {
        map = board;
        dep = board.length;
        if (dep == 0) return;
        wid = board[0].length;
        for (int i = 0; i < dep; i++) {
            if (map[i][0] == 'O') dsf(i, 0);
            if (map[i][wid - 1] == 'O') dsf(i, wid - 1);
        }
        for (int i = 0; i < wid; i++) {
            if (map[0][i] == 'O') dsf(0, i);
            if (map[dep - 1][i] == 'O') dsf(dep - 1, i);
        }
        for (int i = 0; i < dep; i++)
            for (int j = 0; j < wid; j++) {
                if (map[i][j] == 'O') map[i][j] = 'X';
                else if (map[i][j] == 'Q') map[i][j] = 'O';
            }
    }

}

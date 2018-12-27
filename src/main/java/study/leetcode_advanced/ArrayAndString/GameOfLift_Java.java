package study.leetcode_advanced.ArrayAndString;
/**
 * 生命游戏
 * 根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。
 *
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有一个初始状态 live（1）即为活细胞， 或 dead（0）即为死细胞。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 *
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 *
 * 示例:
 *
 * 输入:
 * [
 * [0,1,0],
 * [0,0,1],
 * [1,1,1],
 * [0,0,0]
 * ]
 * 输出:
 * [
 * [0,0,0],
 * [1,0,1],
 * [0,1,1],
 * [0,1,0]
 * ]
 * 进阶:
 *
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 */
public class GameOfLift_Java {
    public void gameOfLife3(int[][] board)
    {
        if(board == null || board.length == 0)
            return;

        int m = board.length; // rows
        int n = board[0].length; // columns

        // first iteration: mark states for each cell
        for(int i=0; i<m; i++) // rows
        {
            for(int j=0; j<n; j++) // columns
            {
                int cnt = 0;
                // count cell's live neighbors 3x3 matrix and set boundary
                for(int x= Math.max(0, i-1); x<= Math.min(m-1, i+1); x++)
                {
                    for(int y= Math.max(0, j-1); y<= Math.min(n-1, j+1); y++)
                    {
                        if(x == i && y == j) // skip itself
                            continue;
                        // only state 1 and 2: cell are live for previous state
                        if(board[x][y] == 1 || board[x][y] == 2)
                            cnt++;
                    }
                }

                if(board[i][j] == 0 && cnt == 3) // current is dead cell
                    board[i][j] = 3; // dead -> live
                else if(board[i][j] == 1 && (cnt < 2 || cnt > 3)) // current live cell
                    board[i][j] = 2; // live -> dead
            }
        }

        // second iteration: convert state back to dead or live
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++)
                board[i][j] %= 2;
    }


    public void gameOfLife2(int[][] board) {
        if(board.length==0||board[0].length==0){
            return;
        }
        int h=board.length;
        int w=board[0].length;
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                int num=board[i][j]%10;
                if(num==0){
                    continue;
                }
                if(i>0){
                    if(j>0){
                        board[i-1][j-1]+=10;
                    }
                    board[i-1][j]+=10;
                    if(j<w-1){
                        board[i-1][j+1]+=10;
                    }
                }
                if(j>0){
                    board[i][j-1]+=10;
                }
                if(j<w-1){
                    board[i][j+1]+=10;
                }
                if(i<h-1){
                    if(j>0){
                        board[i+1][j-1]+=10;
                    }
                    board[i+1][j]+=10;
                    if(j<w-1){
                        board[i+1][j+1]+=10;
                    }
                }
            }
        }

        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                int num=board[i][j];
                int div=num/10;
                if(num%10==0){
                    board[i][j]=div==3?1:0;
                }else{
                    if(div==2||div==3){
                        board[i][j]=1;
                    }else{
                        board[i][j]=0;
                    }
                }
            }
        }
    }
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        /*
        int rows=board.length, cols=board[0].length;
        int[][] record = new int[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(board[i][j] == 1)
                    fillBoard(board, record, i, j, 0);
            }
        }
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(board[i][j] == 1){
                    if(record[i][j]<2 || record[i][j]>3)
                        board[i][j] = 0;
                }else{
                    if(record[i][j] == 3)
                        board[i][j] = 1;
                }
            }
        }
        */
        //常数空间复杂度的解法
        int r = board.length, c = board[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int count = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int n = -1; n <= 1; n++) {
                        if (k == 0 && n == 0)
                            continue;
                        int ik = i + k;
                        int jn = j + n;
                        if (ik >= 0 && jn >= 0 && ik < r && jn < c && (board[ik][jn] == -1 || board[ik][jn] == 1))
                            ++count;
                    }
                }
                if (board[i][j] == 1 && (count < 2 || count > 3))
                    board[i][j] = -1;
                if (board[i][j] == 0 && count == 3)
                    board[i][j] = 2;
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == -1)
                    board[i][j] = 0;
                if (board[i][j] == 2)
                    board[i][j] = 1;
            }
        }
    }

    //利用回溯代替循环暴力解法，简化思路和代码结构，复杂度不变
    public void fillBoard(int[][] board, int[][] record, int i, int j, int count) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length)
            return;
        if (count == 1) {
            record[i][j] += 1;
            return;
        }
        fillBoard(board, record, i - 1, j - 1, 1);
        fillBoard(board, record, i - 1, j, 1);
        fillBoard(board, record, i - 1, j + 1, 1);
        fillBoard(board, record, i, j - 1, 1);
        fillBoard(board, record, i, j + 1, 1);
        fillBoard(board, record, i + 1, j - 1, 1);
        fillBoard(board, record, i + 1, j, 1);
        fillBoard(board, record, i + 1, j + 1, 1);
    }



}

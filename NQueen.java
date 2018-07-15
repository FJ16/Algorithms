import java.util.ArrayList;
import java.util.List;

public class NQueen {
    public List<List<String>> solveNQueens(int n) {
        boolean[][] visited = new boolean[2][n];
        boolean[] diag1 = new boolean[2 * n];
        boolean[] diag2 = new boolean[2 * n];

        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        dfs(n, 0, board, res, visited, diag1, diag2);
        return res;
    }

    private void dfs(int n, int lv, char[][] board, List<List<String>> res, boolean[][] visited,
                     boolean[] diag1, boolean[] diag2) {
        if (lv == n) {
            List<String> curRes = new ArrayList<>();
            for (char[] row : board) {
                curRes.add(new String(row));
            }
            res.add(curRes);
            return;
        }

        for (int i = 0; i < n; i++) {
            int idx1 = lv - i + n;
            int idx2 = 2 * n - (lv + i) - 1;
            if (!visited[0][lv] && !visited[1][i] && !diag1[idx1] && !diag2[idx2]) {
                visited[0][lv] = true; visited[1][i] = true;
                diag1[idx1] = true; diag2[idx2] = true;
                board[lv][i] = 'Q';
                dfs(n , lv + 1, board, res, visited, diag1, diag2);
                board[lv][i] = '.';
                visited[0][lv] = false; visited[1][i] = false;
                diag1[idx1] = false; diag2[idx2] = false;
            }
        }
    }
/*
The logic behind using a 1D boolean array to represent a diagonal is this:
Any point in the matrix is part of two diagonals that cut through it. One from its top-left-ish to its bottom-right-ish and another from top-right-ish to its bottom-left-ish.
For Example , we have the given 2D board:
(0,0) (0,1) (0,2) (0,3)
(1,0) (1,1) (1,2) (1,3)
(2,0) (2,1) (2,2) (2,3)
(3,0) (3,1) (3,2) (3,3)

where the (x,y) represents the position in the board.
Now lets just take a point (1,2). Now the two diagonals that pass through it are :

Through the points (0,1) (1,2) (2,3). These points are considered for d1 boolean array. If we look at any point in this set, x-y is the same. 0-1 == 1-2 == 2-3. Hence i-j + board.length will give the same index in d1 for all its diagonal elements,since i-j will be the same.
Through the points (0,3) (1,2) (2,1) (3,0) . These points are considered for the d2 boolean array. Now if you look at the points , the sum of x and y coordinates is the same for all points. Hence 2board.length -i-j-1 == 2board.length -(i+j) -1 which will be the same for all the points in the diagonal. Hence if the idx in d2 is true already , that would mean some point in the diagonal already had a queen.
*/
}

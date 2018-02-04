import java.util.Comparator;
import java.util.PriorityQueue;

public class KthElementInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) return 0;

        // Best First Search的理念就是 每次 generate取 最当前最优解，用Priority Queue实现
        PriorityQueue<Cell> generate = new PriorityQueue(k, new Comparator<Cell>() { // 堆的初始化大小，可以自动增长
            @Override
            public int compare(Cell a, Cell b) {
                if (a.value == b.value) return 0;
                else return a.value < b.value ? -1 : 1;
            }
        });

        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean[][] visited = new boolean[rows][cols]; // 避免重复generate同一个位置的数，visited matrix 十分实用！
        generate.offer(new Cell(0, 0, matrix[0][0]));
        visited[0][0] = true;

        for (int i = 0; i < k - 1; i++) {
            // 0 -> k - 1 次 poll，poll出 前 k 大的Cell, 因为是min PQ，每次generate，最小的都会在堆顶去等待下一次generate!
            Cell cur = generate.poll();
            if (cur.row + 1 < rows && !visited[cur.row + 1][cur.col]) { // !!再根据<Cell>里自带的 row , col 信息去 generate
                generate.offer(new Cell(cur.row + 1, cur.col, matrix[cur.row + 1][cur.col]));
                visited[cur.row + 1][cur.col] = true;
            }
            if (cur.col + 1 < cols && !visited[cur.row][cur.col + 1]) {
                generate.offer(new Cell(cur.row, cur.col + 1, matrix[cur.row][cur.col + 1]));
                visited[cur.row][cur.col + 1] = true;
            }
        }
        return generate.peek().value; // 第 k - 1 次，堆顶的就是 第 k 大 的 数
    }

    class Cell {
        int row;
        int col;
        int value;
        Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
}

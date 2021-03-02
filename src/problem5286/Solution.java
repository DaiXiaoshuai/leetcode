package problem5286;






import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Solution {

    public int[][] grid;

    class Desc {
        int rowIndex;
        int colIndex;

        public Desc(int rowIndex, int colIndex) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
        }

        public boolean isValid() {
            if (rowIndex < 0 || rowIndex >= grid.length) {
                return false;
            }
            if (colIndex < 0 || colIndex >= grid[rowIndex].length) {
                return false;
            }
            return grid[rowIndex][colIndex] == 0;
        }
    }

    public int shortestPath(int[][] grid, int k) {
        this.grid = grid;
        return -1;
    }

    int shortestPath0(int[][] grid) {
        this.grid = grid;
        boolean[][] flag = new boolean[grid.length][grid[0].length];
        Queue<Desc> queue = new LinkedList<>();
        if (grid[0][0] == 1) return -1;
        queue.add(new Desc(0, 0));
        int length = -1;

        while (!queue.isEmpty()) {
            Queue<Desc> queue2 = new LinkedList<>();
            while (!queue.isEmpty()) {
                Desc desc = queue.remove();
                flag[desc.rowIndex][desc.colIndex] = true;
                length++;
                if (desc.rowIndex == grid.length - 1 && desc.colIndex == grid[0].length - 1 ) return length;
                Desc up = new Desc(desc.rowIndex - 1, desc.colIndex);
                if (up.isValid() && !flag[up.rowIndex][up.colIndex]) queue2.add(up);
                Desc right = new Desc(desc.rowIndex, desc.colIndex + 1);
                if (right.isValid() && !flag[right.rowIndex][right.colIndex]) queue2.add(right);
                Desc down = new Desc(desc.rowIndex + 1, desc.colIndex);
                if (down.isValid() && !flag[down.rowIndex][down.colIndex]) queue2.add(down);
                Desc left = new Desc(desc.rowIndex, desc.colIndex - 1);
                if (left.isValid() && !flag[left.rowIndex][left.colIndex]) queue2.add(left);
            }
            queue = queue2;
        }
        return -1;
    }


    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(10, solution.shortestPath0(new int[][]{
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0}
        }));
    }
}

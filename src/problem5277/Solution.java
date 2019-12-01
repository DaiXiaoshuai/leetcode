package problem5277;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {
    int[][] matrix;

    public int countSquares(int[][] matrix) {
        this.matrix = matrix;
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                for (int k = 1; k <= Math.min(i + 1, matrix[i].length - j); k++) {
                    if (check(i, j, k)) count++;
                }
            }
        }
        return count;
    }

    boolean check(int row, int col, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    if (matrix[row - i][col + j] == 0) return false;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Test
    void test() {
        Solution solution = new Solution();
        assertEquals(15, solution.countSquares(new int[][]{
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        }));
    }
}

package problem62;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {


    //自顶向下采用递归方式
    public int uniquePaths(int m, int n) {
        int[][] data = new int[n][m];
        return get(data, n - 1, m - 1);
    }

    int get(int[][] data, int row, int column) {
        if (row < 0 || column < 0) return 0;
        if (row == 0 || column == 0) return 1;
        if (data[row][column] == 0) {
            int sum = 0;
            sum += get(data, row - 1, column);
            sum += get(data, row, column - 1);
            data[row][column] = sum;
            return sum;
        } else {
            return data[row][column];
        }
    }

    // 自底向上
    public int uniquePaths2(int m, int n) {
        if(m <=0||n<=0) return 0;
        if(m<n) {  // 确保m > n
            int tmp = m;
            m = n;
            n=tmp;
        }
        int[][] data = new int[n][m];
        int p=0, q=0;
        for(int i=0;i<m;i++){
            if(i==0) data[p][q] = 1;
            else{
                data[0][i] = 1;
                if(i<n) data[i][0] = 1;
                for(int j =1; j<=Math.min(i, n-1);j++){
                    data[j][i] = data[j-1][i] +  data[j][i-1];
                    if(i<n) data[i][j] = data[j][i];
                }
            }
        }
        return data[n-1][m-1];
    }


        @Test
    void test() {
        Solution solution = new Solution();
        assertEquals(3, solution.uniquePaths(3, 2));
        assertEquals(3, solution.uniquePaths2(3, 2));
        assertEquals(28, solution.uniquePaths(7, 3));
        assertEquals(28, solution.uniquePaths2(7, 3));
    }

}

package problem63;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) return 0;
        boolean flag = true;
        obstacleGrid[0][0] = 1;
        for(int i=0;i<obstacleGrid.length;i++){
            for(int j = 0;j<obstacleGrid[i].length;j++){
                if(obstacleGrid[i][j] == 1) {
                    if(i+j !=0)
                        obstacleGrid[i][j] = -1;
                }
                else{
                    if(i==0)
                        obstacleGrid[i][j] = max(0, obstacleGrid[i][j - 1]);
                    else if(j ==0)
                        obstacleGrid[i][j] = max(0, obstacleGrid[i-1][j]);
                    else
                        obstacleGrid[i][j] = max(0,obstacleGrid[i-1][j]) + max(0,obstacleGrid[i][j-1]);
                }
            }
        }
        return max(0,obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1]);
    }

    private int max(int a, int b){
        return (a >= b) ? a : b;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertEquals(0,solution.uniquePathsWithObstacles(new int[][]{{0,0},{1,1},{0,0}}));

        assertEquals(2,solution.uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
        assertEquals(0,solution.uniquePathsWithObstacles(new int[][]{{1}}));
        assertEquals(0,solution.uniquePathsWithObstacles(new int[][]{{1,0}}));
        assertEquals(0,solution.uniquePathsWithObstacles(new int[][]{{0,1}}));
    }
}

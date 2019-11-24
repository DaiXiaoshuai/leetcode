package problem5272;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {
    public int countServers(int[][] grid) {
        int count = 0;
        for(int i=0;i<grid.length;i++){
            int tmpCount = 0;
            int firstCol = -1;
            for(int j =0;j<grid[i].length;j++){
                if(grid[i][j] == 1){
                    tmpCount ++;
                    if(tmpCount == 1){
                        firstCol = j;
                    }
                }
            }
            if(tmpCount == 1){
                if(checkCol(grid,firstCol)){
                    count += 1;
                };
            }else{
                count += tmpCount;
            }
        }
        return count;
    }

    boolean checkCol(int[][] grid, int colNum){
        int count = 0;
        for(int i=0;i<grid.length;i++){
            if(grid[i][colNum] == 1){
                if(count == 1) return true;
                count++;
            }
        }
        return false;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertEquals(0, solution.countServers(new int[][]{
                {1}
        }));
        assertEquals(0, solution.countServers(new int[][]{
                {1,0},
                {0,1}
        }));
        assertEquals(3, solution.countServers(new int[][]{
                {1,0},
                {1,1}
        }));
        assertEquals(4, solution.countServers(new int[][]{
                {1,1,0,0},
                {0,0,1,0},
                {0,0,1,0},
                {0,0,0,1}
        }));
    }
}

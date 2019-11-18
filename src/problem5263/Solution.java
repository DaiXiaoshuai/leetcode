package problem5263;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int lastValue = 0;
        int moveRowNum = k/grid[0].length;
        int moveColNum = k%grid[0].length;
        int[][] result = new int[grid.length][grid[0].length];
        for(int row = 0;row< grid.length;row++){
            for(int col=0;col< grid[0].length;col++){
                result[((col + k)/grid[0].length + row) % grid.length][(col + k) % grid[0].length] = grid[row][col];
            }
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>(grid.length);
        for(int i=0;i<grid.length;i++){
            List<Integer> integers = new ArrayList<>(grid[0].length);
            for(int j=0;j<grid[0].length;j++){
                integers.add(result[i][j]);
            }
            res.add(integers);
        }
        return res;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        solution.shiftGrid(new int[][]{{1,2,3},{4,5,6},{7,8,9}},9);
        solution.shiftGrid(new int[][]{{1,2,3},{4,5,6},{7,8,9}},1);
        solution.shiftGrid(new int[][]{{3,8,1,9},{19,7,2,5},{4,6,11,10},{12,0,21,13}},1);
    }
}

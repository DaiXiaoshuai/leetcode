package problem5271;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int count = 0;
        for(int i=0;i<points.length - 1;i++){
            count += Math.abs(Math.abs(points[i][0] - points[i+1][0]) - Math.abs(points[i][1] - points[i+1][1])) +
                    Math.min(Math.abs(points[i][0] - points[i+1][0]) , Math.abs(points[i][1] - points[i+1][1]));
        }
        return count;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertEquals(7, solution.minTimeToVisitAllPoints(new int[][]{
                {1,1},
                {3,4},
                {-1,0}
        }));
        assertEquals(5, solution.minTimeToVisitAllPoints(new int[][]{
                {3,2},
                {-2,2}
        }));
        assertEquals(0, solution.minTimeToVisitAllPoints(new int[][]{
                {3,2}
        }));
    }
}

package problem1269;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {
    public int numWays(int steps, int arrLen) {
        long[] dp = new long[Math.min(steps, arrLen)];
        int interval = 1000000007;
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=steps;i++){
            long[] tmp = new long[Math.min(steps, arrLen)];
            for(int j =0;j<dp.length;j++){
                if(j -1 >=0 && j+1<dp.length) {
                    tmp[j] = dp[j] + dp[j-1] + dp[j+1];
                }else if(j-1>=0){
                    tmp[j] = dp[j] + dp[j-1];
                }else{
                    tmp[j] = dp[j] + dp[j+1];
                }
                tmp[j] = tmp[j] % interval;
            }
            dp = tmp;
        }
        return (int)(dp[0]%interval);
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertEquals(318671228, solution.numWays(47,38));

        assertEquals(4, solution.numWays(3,2));
        assertEquals(2, solution.numWays(2,4));
    }
}

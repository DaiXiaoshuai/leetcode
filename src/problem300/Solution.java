package problem300;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] flag = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int maxIndex = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (maxIndex == -1) maxIndex = j;
                    else if(flag[j] > flag[maxIndex]){
                        maxIndex = j;
                    };
                }
            }
            if (maxIndex == -1) flag[i] = 1;
            else flag[i] = flag[maxIndex] + 1;
        }
        int maxLen = 0;
        for (int value : flag) {
            if (value > maxLen) maxLen = value;
        }
        return maxLen;
    }

    @Test
    void test() {
        Solution solution = new Solution();
        assertEquals(2, solution.lengthOfLIS(new int[]{1, 2}));
        assertEquals(6, solution.lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4,10,5,6}));

        assertEquals(2, solution.lengthOfLIS(new int[]{-2, -1}));

        assertEquals(4, solution.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101}));
        assertEquals(0, solution.lengthOfLIS(new int[]{}));
        assertEquals(1, solution.lengthOfLIS(new int[]{1}));
    }
}

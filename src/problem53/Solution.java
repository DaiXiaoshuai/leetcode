package problem53;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public int maxSubArray(int[] nums) {
        int[] results = new int[nums.length]; // TODO 此处只需用一个变量记录results[i-1] 即可，无需使用数组
        int max = 0;
        for (int i = 0; i < nums.length; i++) { // TODO 循环可从1开始，优化代码
            if (i == 0) {
                max = nums[0];
                results[0] = nums[0];
            } else {
                if(results[i-1]<0){
                    results[i] = nums[i];
                    if(results[i]>=max){
                        max = results[i];
                    }
                    continue;
                }
                results[i] = nums[i] + results[i-1];
                if(nums[i] + results[i-1] > max) {
                    max = results[i];
                }
            }
        }
        return max;
    }

    @Test
    void test() {
        Solution solution = new Solution();
        assertEquals(6, solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        assertEquals(3, solution.maxSubArray(new int[]{1,2}));
    }

}

package problem31;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public void nextPermutation(int[] nums) {
        if(nums.length<=1) return;
        int targetIndex = -1;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i] < nums[i+1]){
                targetIndex = i;
            }
        }
        int tmp = 0;
        if(targetIndex!=-1) {
            int minIntervalIndex = targetIndex +1;
            for(int i = targetIndex+1;i<nums.length;i++){
                if(nums[i] - nums[targetIndex]>0 && nums[i] - nums[targetIndex] <= nums[minIntervalIndex] - nums[targetIndex]){
                    minIntervalIndex = i;
                }
            }
            tmp = nums[minIntervalIndex];
            nums[minIntervalIndex] = nums[targetIndex];
            nums[targetIndex] = tmp;
            Arrays.sort(nums, targetIndex+1,nums.length);
        }else{
            for(int i=0;i<nums.length/2;i++){
                tmp = nums[i];
                nums[i] = nums[nums.length -1 -i];
                nums[nums.length-1-i] = tmp;
            }
        }
    }

    @Test
    void test(){
        Solution solution = new Solution();

        int[] case5 = new int[]{2,3,1};
        solution.nextPermutation(case5);
        assertArrayEquals(new int[]{3,1,2}, case5);


        int[] case4 = new int[]{1,3,2};
        solution.nextPermutation(case4);
        assertArrayEquals(new int[]{2,1,3}, case4);


        int[] case1 = new int[]{1,2,3};
        solution.nextPermutation(case1);
        assertArrayEquals(new int[]{1,3,2}, case1);

        int[] case2 = new int[]{3,2,1};
        solution.nextPermutation(case2);
        assertArrayEquals(new int[]{1,2,3}, case2);

        int[] case3 = new int[]{1,1,5};
        solution.nextPermutation(case3);
        assertArrayEquals(new int[]{1,5,1}, case3);


    }
}

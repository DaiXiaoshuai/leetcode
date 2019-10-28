package problem16;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        int result = nums[0]+nums[1]+nums[nums.length-1];
        int tmp = 0;
        for(int i=0;i<nums.length;i++){
            if(i>0 && nums[i] == nums[i-1]) continue;
            int left = i+1;
            int right = nums.length -1;
            int min,max = 0;
            while(left<right){
                sum = nums[i] + nums[left] + nums[right];
                tmp = sum - target;
                min =nums[i] + nums[left] + nums[left+1];
                if(target < min){
                    if(Math.abs(result - target) > Math.abs(min - target))
                        result = min;
                    break;
                }
                max = nums[i] + nums[right] + nums[right - 1];
                if(target > max){
                    if(Math.abs(result - target) > Math.abs(max - target))
                        result = max;
                    break;
                }
                if(tmp == 0) return sum;
                if(Math.abs(tmp) < Math.abs(result-target)) result = sum;
                if(tmp < 0){
                    left++;
                    while (nums[left-1] == nums[left] && left<right) left++;
                }else{
                    right--;
                    while (nums[right+1] == nums[right] && left<right) right--;
                }
            }
        }
        return result;
    }

    @Test
    void test() {
        Solution solution = new Solution();
        assertEquals(2,solution.threeSumClosest(new int[]{-1,2,1,-4}, 1));
        assertEquals(2,solution.threeSumClosest(new int[]{1,1,1,0}, -100));
        assertEquals(82,solution.threeSumClosest(new int[]{1,2,4,8,16,32,64,128}, 82));
    }
}

package problem34;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        return fun(nums, 0,nums.length-1,target);
    }

    int[] fun(int[] nums, int beginIndex, int endIndex, int target){
        if(beginIndex>endIndex) return new int[]{-1,-1};
        int middle = (beginIndex + endIndex) /2;
        if(nums[middle] == target){
            return new int[]{getLeftIndex(nums,beginIndex,middle,target), getRightIndex(nums, middle,endIndex,target)};
        }
        if(nums[middle] > target) return fun(nums,beginIndex, middle-1,target);
        else return fun(nums,middle+1,endIndex,target);

    }

    int getLeftIndex(int[] nums, int beginIndex, int endIndex, int target){
        if(beginIndex + 1 == endIndex) return nums[beginIndex] == target? beginIndex:endIndex;
        if( beginIndex>= endIndex) return endIndex;
        int middle = (beginIndex + endIndex) /2;
        if(nums[middle] == target) return getLeftIndex(nums, beginIndex,middle,target);
        else return getLeftIndex(nums,middle+1, endIndex,target);
    }

    private int getRightIndex(int[] nums, int beginIndex, int endIndex, int target) {
        if(beginIndex + 1 == endIndex) return nums[endIndex] == target? endIndex:beginIndex;
        if(beginIndex>=endIndex) return endIndex;
        int middle = (beginIndex + endIndex)/2;
        if(nums[middle] == target) return getRightIndex(nums, middle,endIndex,target);
        else return getRightIndex(nums,beginIndex,middle-1,target);
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertArrayEquals(new int[]{3,4}, solution.searchRange(new int[]{5,7,7,8,8,10},8));
        assertArrayEquals(new int[]{1,2}, solution.searchRange(new int[]{5,7,7,8,8,10},7));
        assertArrayEquals(new int[]{-1,-1}, solution.searchRange(new int[]{5,7,7,8,8,10},6));
    }
}

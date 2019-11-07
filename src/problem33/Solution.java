package problem33;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public int search(int[] nums, int target) {
        return fun2(nums,0,nums.length-1, target);
    }

    /**
     * 二分查找
     * @param nums
     * @param beginIndex 包含
     * @param endIndex 包含
     * @return 所在下标
     */
    int fun(int[] nums, int beginIndex, int endIndex, int target){
        if(beginIndex > endIndex) return -1;
        int middle = (beginIndex + endIndex) /2;
        if(nums[middle] == target) return middle;
        if(nums[middle] > target) return fun(nums,beginIndex,middle-1,target);
        return fun(nums,middle+1, endIndex,target);
    }

    int fun2(int[] nums,int beginIndex, int endIndex, int target){
        if(beginIndex>endIndex) return  -1;
        if(nums[beginIndex] == target) return beginIndex;
        if(nums[endIndex] == target) return endIndex;
        int middle = (beginIndex + endIndex) / 2;
        if(nums[middle] == target) return middle;
        if(nums[middle] > nums[beginIndex]){
            return Math.max(fun(nums, beginIndex, middle-1,target), fun2(nums, middle+1, endIndex,target));
        }else{
            return Math.max(fun(nums, middle+1, endIndex, target), fun2(nums,beginIndex,middle-1,target));
        }
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertEquals(4,solution.search(new int[]{4,5,6,7,0,1,2}, 0));
        assertEquals(5,solution.search(new int[]{4,5,6,7,0,1,2}, 1));
        assertEquals(6,solution.search(new int[]{4,5,6,7,0,1,2}, 2));
        assertEquals(-1,solution.search(new int[]{4,5,6,7,0,1,2}, 3));
        assertEquals(0,solution.search(new int[]{4,5,6,7,0,1,2}, 4));
        assertEquals(1,solution.search(new int[]{4,5,6,7,0,1,2}, 5));
        assertEquals(2,solution.search(new int[]{4,5,6,7,0,1,2}, 6));
        assertEquals(3,solution.search(new int[]{4,5,6,7,0,1,2}, 7));
        assertEquals(1,solution.search(new int[]{5,1,2,3,4}, 1));
    }
}

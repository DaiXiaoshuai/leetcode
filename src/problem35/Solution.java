package problem35;

import com.sun.xml.internal.bind.v2.util.StackRecorder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public int searchInsert(int[] nums, int target) {
        return fun2(nums,0,nums.length-1,target);
    }


    int fun2(int[] nums, int begin, int end, int target){
        while (begin<=end){ // 参考Arrays.binarySearch api.  此处如果是 < 则第48行代码应为 else end = middle; 此处对边界值的处理太巧妙了
            int middle = (begin+end)/2;
            if(nums[middle] == target) return middle;
            if(target > nums[middle] ) begin = middle + 1;
            else end = middle -1;
        }
        return begin ;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertEquals(1, solution.searchInsert(new int[]{1,3}, 2));

        assertEquals(0, solution.searchInsert(new int[]{1,3,5,6}, 1));
        assertEquals(1, solution.searchInsert(new int[]{1,3,5,6}, 2));
        assertEquals(1, solution.searchInsert(new int[]{1,3,5,6}, 3));
        assertEquals(2, solution.searchInsert(new int[]{1,3,5,6}, 4));
        assertEquals(2, solution.searchInsert(new int[]{1,3,5,6}, 5));
        assertEquals(3, solution.searchInsert(new int[]{1,3,5,6}, 6));
        assertEquals(4, solution.searchInsert(new int[]{1,3,5,6}, 7));
        assertEquals(0, solution.searchInsert(new int[]{}, 7));
        assertEquals(1, solution.searchInsert(new int[]{6}, 7));
        assertEquals(0, solution.searchInsert(new int[]{6,7}, 5));
        assertEquals(0, solution.searchInsert(new int[]{6}, 5));
    }
}

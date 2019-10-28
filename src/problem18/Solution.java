package problem18;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if(i>0 && nums[i] == nums[i-1]) continue;
            for (int j = i + 1; j < nums.length-2; j++) {
                if(j>i+1&&nums[j]==nums[j-1]) continue;
                int left = j + 1;
                int right = nums.length - 1;
                int sum = 0;
                while (left < right) {
                    sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        ArrayList<Integer> result = new ArrayList<>();
                        result.add(nums[left]);
                        result.add(nums[right]);
                        result.add(nums[i]);
                        result.add(nums[j]);
                        results.add(result);
                        left++;
                        while (nums[left] == nums[left - 1] && left < right) left++;
                        right--;
                        while (nums[right] == nums[right + 1] && left < right) right--;
                    } else if (sum < target) {
                        left++;
                        while (nums[left] == nums[left - 1] && left < right) left++;
                    } else {
                        right--;
                        while (nums[right] == nums[right + 1] && left < right) right--;
                    }
                }
            }

        }
        return results;
    }

    /**
     * 二分查找
     *
     * @param data
     * @param beginIndex
     * @param endIndex
     * @param target
     * @return
     */
    int find(int[] data, int beginIndex, int endIndex, int target) {
        if (target < data[beginIndex] || target > data[endIndex]) return -1;
        int middle;
        while (beginIndex <= endIndex) {
            middle = (beginIndex + endIndex) / 2;
            if (data[middle] == target) return middle;
            if (target > data[(beginIndex + endIndex) / 2]) beginIndex = middle + 1;
            else endIndex = middle - 1;
        }
        return -1;
    }

    @Test
    void test() {
        Solution solution = new Solution();
        List<List<Integer>> lists3 = solution.fourSum(new int[]{-1,-5,-5,-3,2,5,0,4}, -7);

        List<List<Integer>> lists = solution.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        List<List<Integer>> lists2 = solution.fourSum(new int[]{-3, -1, 0, 2, 4, 5}, 2);
        assertEquals(3, lists.size());
        assertEquals(1, lists2.size());
        assertEquals(2, lists3.size());

    }

}

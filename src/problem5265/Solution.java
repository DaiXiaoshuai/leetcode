package problem5265;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,6,5,1,8]
 * 输出：18
 * 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
 * 示例 2：
 *
 * 输入：nums = [4]
 * 输出：0
 * 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
 * 示例 3：
 *
 * 输入：nums = [1,2,3,4,4]
 * 输出：12
 * 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/greatest-sum-divisible-by-three
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {
    public int maxSumDivThree(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int item : nums){
            sum += item;
        }
        if(sum %3 ==0) return sum;
        List<Integer> sumData = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            List<Integer> dp = dp(sumData, nums[i]);
            for(int item : dp){
                int value = sum - item;
                if(value%3 == 0 && (i == nums.length-1 || item < nums[i+1])){
                    return value;
                }
            }
        }
        return 0;
    }


    /**
     * 求一个集合加入一个新的数后的sum集
     * @param data 求解钱sum集
     * @param target 加入的新数
     * @return sum集中新增的sum
     */
    List<Integer> dp(List<Integer> data, int target){
        List<Integer> list = new ArrayList<>();
        list.add(target);
        int preNum = -1;
        for(Integer sum : data){
            if(sum != preNum)
                list.add(sum + target);
            preNum = sum;
        }
        data.addAll(list);
        return list;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertEquals(0, solution.maxSumDivThree(new int[]{4,4}));
        assertEquals(3, solution.maxSumDivThree(new int[]{1,1,1,2}));
        assertEquals(6, solution.maxSumDivThree(new int[]{1,3,3}));
        assertEquals(12, solution.maxSumDivThree(new int[]{1,2,3,4,4}));
        assertEquals(18, solution.maxSumDivThree(new int[]{3,6,5,1,8}));
        assertEquals(6, solution.maxSumDivThree(new int[]{3,1,2}));
        assertEquals(49701, solution.maxSumDivThree(new int[]{972,944,817,475,436,623,900,268,25,263,627,799,38,943,968,17,813,139,772,333,498,593,567,556,550,40,4,862,915,935,366,253,994,893,47,211,332,854,73,694,37,63,789,785,419,129,170,404,854,424,712,784,539,697,478,978,509,76,528,65,194,352,986,713,730,223,858,366,71,18,60,8,835,70,349,905,446,593,909,592,95,280,900,887,303,245,612,708,7,58,564,577,718,410,512,637,535,432,332,770}));
    }

}

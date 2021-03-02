package problem42;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Solution {
    public int trap(int[] height) {
        if(height.length<1) return 0;
        int left = 0;
        int right = left+1;
        int sum = 0;
        while(right<height.length){
            boolean flag = false; // 标记是否是否找到不低于当前左柱的右柱
            int maxHeightIndex = right;
            for(int i = right;i<height.length;i++){
                if(height[i] > height[maxHeightIndex]){
                    maxHeightIndex = i;
                }
                if(height[i] < height[left]) continue; // 略过比左柱矮的柱子
                else{   // 找到区间，求和
                    for(int j = left;j<i;j++){
                        sum += height[left] - height[j];
                    }
                    left = i;
                    right = left+1;
                    flag = true;
                    break;
                }
            }
            if(flag == false){
                for(int j = left;j<maxHeightIndex;j++){
                    sum += Math.max((height[maxHeightIndex] - height[j]), 0);
                }
                left = maxHeightIndex;
                right = left+1;
            }
        }
        return sum;
    }

    @Test
    public void test(){
        Solution solution = new Solution();
        assertEquals(6,solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        assertEquals(5,solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,1,1}));
        assertEquals(5,solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,1,1}));
        assertEquals(0,solution.trap(new int[]{0,0}));
        assertEquals(0,solution.trap(new int[]{0,1}));
        assertEquals(1,solution.trap(new int[]{1,0,100}));
        assertEquals(100,solution.trap(new int[]{100,0,100}));
        assertEquals(1,solution.trap(new int[]{100,0,1}));
    }
}
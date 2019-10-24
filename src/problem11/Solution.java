package problem11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 *
 *
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    /**
     * 暴力法
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int maxSize = 0;
        for(int i=1;i<height.length;i++){
            for(int j=0;j<i;j++){
                int tmpSize = Math.min(height[j], height[i]) * (i-j);
                if(tmpSize > maxSize){
                    maxSize = tmpSize;
                }
            }
        }
        return maxSize;
    }

    /**
     * 双指针法
     */
    public int maxArea(int[] height) {
        int maxSize = 0;
        int head = 0;
        int tail = height.length-1;
        while (head<tail){
            maxSize = Math.max((tail-head) * Math.min(height[head], height[tail]), maxSize);
            if(height[head] < height[tail]) head++;
            else tail--;
        }
        return maxSize;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertEquals(49, solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        assertEquals(1, solution.maxArea(new int[]{1,8}));
        assertEquals(1, solution.maxArea(new int[]{1,1}));
        assertEquals(2, solution.maxArea(new int[]{1,2,1}));
    }
}

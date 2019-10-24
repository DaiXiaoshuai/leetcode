package problem9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        int y = x;
        int tmp = 0;
        while(y>0){
            tmp = y%10 + tmp*10;
            y = y/10;
        }
        return x==tmp;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertTrue(solution.isPalindrome(121));
        assertFalse(solution.isPalindrome(10));
        assertFalse(solution.isPalindrome(-121));
        assertTrue(solution.isPalindrome(0));
        assertTrue(solution.isPalindrome(1));
    }
}

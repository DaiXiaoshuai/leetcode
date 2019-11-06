package problem29;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        boolean flag = false;
        if(dividend > 0){ dividend = - dividend;flag = true; }
        if(divisor > 0){ divisor = - divisor;flag = !flag; }
        if(dividend>divisor) return 0;

        if(flag) return -divide2(dividend, divisor);
        else return divide2(dividend, divisor);
    }

    public int divide2(int dividend, int divisor){
        if(dividend>divisor) return 0;
        if(dividend==divisor) return 1;
        int count = 1;
        int tmp = divisor;
        while(tmp<<1 >= dividend){
            if(tmp < -((int)Math.pow(2,30) - 1)) break;
            count = count<<1;
            tmp = tmp <<1;
        }
        return count+divide2(dividend-tmp, divisor);
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertEquals(2147483647/3,solution.divide(2147483647, 3));

        assertEquals(100/3,solution.divide(100, 3));
        assertEquals(99/3,solution.divide(99, 3));


        assertEquals(765231866/-2147483648,solution.divide(765231866, -2147483648));

        assertEquals(3,solution.divide(10,3));
        assertEquals(-2,solution.divide(7,-3));
        assertEquals(Integer.MAX_VALUE,solution.divide(Integer.MIN_VALUE,-1));
        assertEquals(Integer.MIN_VALUE,solution.divide(Integer.MIN_VALUE,1));
        assertEquals(Integer.MAX_VALUE,solution.divide(Integer.MAX_VALUE,1));
        assertEquals(Integer.MIN_VALUE+1,solution.divide(Integer.MAX_VALUE,-1));
    }
}

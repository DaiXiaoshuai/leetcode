package problem7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {
    public int reverse(int x) {
        int flag = 1;
        int result = 0;
        if(x<0) {
            flag = -1;
            x = -x;
        }
        int num;
        int overNum = (int)Math.pow(2, 31);
        while(x>0){
            num = x%10;
            if(result > Integer.MAX_VALUE/10) return 0;
            result = result*10 + num;
            x = x/10;
        }
        return result*flag;
    }

    @Test
    void testReverse(){
        Solution solution = new Solution();
        assertEquals(-1, -1%5);
        assertEquals(123, solution.reverse(321));
        assertEquals(-123, solution.reverse(-321));
        assertEquals(21, solution.reverse(120));
        assertEquals(0, solution.reverse(1534236469));
    }
}

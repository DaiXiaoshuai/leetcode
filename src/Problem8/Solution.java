package Problem8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {
    public int myAtoi(String str) {
        str = str.trim();
        if(str.length() == 0) return 0;
        if(!isValid(str, 0)) return 0;
        int i=0;
        while (isValid(str,i)){
            if(i<str.length()-1) i++;
            else break;
        }
        if(!isValid(str, i)) i=i-1;
        long value = 0;
        boolean flag = false; // 是否负数
        int j = 0;
        if(str.charAt(0) == '-'){
            flag = true;
            j = 1;
        }
        if(str.charAt(0) == '+'){
            j = 1;
        }
        for(;j<=i;j++){
            value = value*10 + (str.charAt(j) - '0') ;
            if(j==i) break;
            long num = value*10  + (str.charAt(j + 1) - '0') ;
            if(flag){
                if( num -1>= Integer.MAX_VALUE){
                    return Integer.MIN_VALUE;
                }
            }else {
                if(num >= Integer.MAX_VALUE){
                    return Integer.MAX_VALUE;
                }
            }
        }
        if(flag) return (int)-value;
        return (int)value;
    }

    public boolean isValid(String str, int index){
        if(str.charAt(index) >= '0' && str.charAt(index) <= '9') return true;
        if(index>0) return false;
        if(str.length()>1){
            if(str.charAt(0) == '+' && str.charAt(1) >= '0' && str.charAt(1) <= '9') return true;
            if(str.charAt(0) == '-' && str.charAt(1) >= '0' && str.charAt(1) <= '9') return true;
        }
        return false;
    }

    @Test
    void test(){
        Solution solution = new Solution();

        assertEquals(Integer.MAX_VALUE,solution.myAtoi("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000522545459"));
        assertEquals(4193,solution.myAtoi("4193 with words"));
        assertEquals(42,solution.myAtoi("42"));
        assertEquals(-42,solution.myAtoi("-42"));
        assertEquals(0,solution.myAtoi("words and 987"));
        assertEquals(Integer.MAX_VALUE,solution.myAtoi("91283472332"));
        assertEquals(Integer.MIN_VALUE,solution.myAtoi("-91283472332"));
        assertEquals(0,solution.myAtoi("-"));
        assertEquals(0,solution.myAtoi("+"));
        assertEquals(0,solution.myAtoi(""));
        assertEquals(-2147483647,solution.myAtoi("-2147483647"));
        assertEquals(-2147483648,solution.myAtoi("-2147483648"));
        assertEquals(-2147483648,solution.myAtoi("-2147483649"));
        assertEquals(2147483646,solution.myAtoi("2147483646"));
        assertEquals(2147483647,solution.myAtoi("2147483647"));
        assertEquals(2147483647,solution.myAtoi("2147483648"));
        assertEquals(2147483647,solution.myAtoi("2147483649"));
        assertEquals(0,solution.myAtoi("0-1"));
    }
}

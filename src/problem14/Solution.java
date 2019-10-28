package problem14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        String result = strs[0];
        for(int i=1;i<strs.length;){
            if(strs[i].startsWith(result)) i++;
            else {
                if(result.length()>1)
                    result = result.substring(0, result.length()-1);
                else return "";
            }
        }
        return result;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertEquals("fl", solution.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        assertEquals("", solution.longestCommonPrefix(new String[]{"dog","racecar","car"}));
        assertEquals("dog", solution.longestCommonPrefix(new String[]{"dog","doge"}));
        assertEquals("dog", solution.longestCommonPrefix(new String[]{"dog","dog"}));
        assertEquals("dog", solution.longestCommonPrefix(new String[]{"dog"}));
    }
}

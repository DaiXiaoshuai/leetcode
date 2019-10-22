package problem5

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-palindromic-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {
    // 采用题解中的中心拓展思路的解题
    fun longestPalindrome(s: String): String {
        if(s.isEmpty() ) return ""
        var maxSize = 0;
        var beginIndex = 0
        var endIndex = 0
        for(index in s.indices){
            var offset = 0
            while(index-offset>=0 && index+offset<=s.length-1){
                if(s[index-offset] == s[index+offset]){
                    if(offset*2+1 > maxSize){
                        maxSize = offset*2+1
                        beginIndex = index - offset
                        endIndex = index + offset
                    }
                    offset += 1
                }else{
                    break
                }
            }
        }
        if(s.length>=2){
            for(index in 0..s.length-2){
                if(s[index] == s[index+1]){
                    if(maxSize<2) {
                        maxSize = 2
                        beginIndex = index
                        endIndex = index+1
                    }
                    var offset = 0
                    while(index-offset>=0 && index+1+offset<=s.length-1){
                        if(s[index-offset] == s[index+1+offset]){
                            if(offset*2 + 2 > maxSize){
                                maxSize = offset*2+2
                                beginIndex = index - offset
                                endIndex = index +1 + offset
                            }
                            offset += 1
                        }else{
                            break
                        }
                    }
                }
            }
        }
        return s.substring(beginIndex, endIndex+1)
    }

    @Test
    fun testLongestPalindrome(){
        val solution = Solution()
        assertEquals("a",solution.longestPalindrome("abcda"))
        assertEquals("aaaa",solution.longestPalindrome("aaaa"))
        assertEquals("bab",solution.longestPalindrome("babad"))
        assertEquals("bb",solution.longestPalindrome("bb"))
        assertEquals("bb",solution.longestPalindrome("cbbd"))
        assertEquals("a",solution.longestPalindrome("a"))
        assertEquals("",solution.longestPalindrome(""))
    }
}
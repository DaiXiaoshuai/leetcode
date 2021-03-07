package problem5679

/**
 * 5697. 检查二进制字符串字段 显示英文描述
通过的用户数0
尝试过的用户数0
用户总通过次数0
用户总提交次数0
题目难度Easy
给你一个二进制字符串 s ，该字符串 不含前导零 。

如果 s 最多包含 一个由连续的 '1' 组成的字段 ，返回 true​​​ 。否则，返回 false 。



示例 1：

输入：s = "1001"
输出：false
解释：字符串中的 1 没有形成一个连续字段。
示例 2：

输入：s = "110"
输出：true


提示：

1 <= s.length <= 100
s[i]​​​​ 为 '0' 或 '1'
s[0] 为 '1'
 */
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SolutionTest{
    @Test
    fun test(){
        val solution = Solution()
        assertTrue(solution.checkOnesSegment("110"))
        assertTrue(solution.checkOnesSegment("1"))
        assertFalse(solution.checkOnesSegment("1001"))
        assertTrue(solution.checkOnesSegment("1111111"))
        assertTrue(solution.checkOnesSegment("1000"))
    }
}
class Solution {
    fun checkOnesSegment(s: String): Boolean {
        var count = 0
        var prev :Char = '1'
        for(ch in s){
            if(ch == '1'){
                if(count==1){
                    return false
                }
            }
            if(ch =='0' && prev == '1'){
                count ++
            }
            prev = ch
        }
        return true
    }
}
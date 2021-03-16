package problem678

/**
 * 678. 有效的括号字符串
给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：

任何左括号 ( 必须有相应的右括号 )。
任何右括号 ) 必须有相应的左括号 ( 。
左括号 ( 必须在对应的右括号之前 )。
 * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
一个空字符串也被视为有效字符串。
示例 1:

输入: "()"
输出: True
示例 2:

输入: "(*)"
输出: True
示例 3:

输入: "(*))"
输出: True
注意:

字符串大小将在 [1，100] 范围内。
 */
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.Math.abs

internal class SolutionTest {

    @Test
    fun checkValidString() {
        val solution = Solution()
        assertTrue(solution.checkValidString("()"))
        assertTrue(solution.checkValidString("(*)"))
        assertTrue(solution.checkValidString("(*))"))
        assertTrue(solution.checkValidString(""))
        assertTrue(solution.checkValidString("((*())*)"))
        assertFalse(solution.checkValidString("((*)*)("))
        assertFalse(solution.checkValidString("((*())*)("))
        assertFalse(solution.checkValidString("((*)*)("))
        assertTrue(solution.checkValidString("((**)"))
        assertTrue(solution.checkValidString("((*()))"))
        assertTrue(solution.checkValidString("**"))
        assertTrue(solution.checkValidString("*"))
        assertTrue(solution.checkValidString("**********************"))
    }
}
class Solution {
    fun checkValidString(s: String): Boolean {
        var low = 0  // 表示最少未匹配的左括号数
        var high = 0 // 表示最多未匹配的左括号数
        for(item in s){
            if(item == '('){
                low++
                high++
            }else if(item == ')'){
                low = maxOf(0, low - 1)
                high--
                if(high < 0) return false
            }else{
                low = maxOf(0, low - 1)
                high++
            }
        }
        if(high < 0 || low > 0) return false
        return true
    }
}
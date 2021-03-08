package problem1016

/**
 * 1016. 子串能表示从 1 到 N 数字的二进制串
给定一个二进制字符串 S（一个仅由若干 '0' 和 '1' 构成的字符串）和一个正整数 N，如果对于从 1 到 N 的每个整数 X，其二进制表示都是 S 的子串，就返回 true，否则返回 false。



示例 1：

输入：S = "0110", N = 3
输出：true
示例 2：

输入：S = "0110", N = 4
输出：false


提示：

1 <= S.length <= 1000
1 <= N <= 10^9
通过次数4,353提交次数7,481
 */
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.StringBuilder

internal class SolutionTest {

    @Test
    fun intToBinStr() {
        val solution = Solution()
        assertEquals("1", solution.intToBinStr(1))
        assertEquals("1010", solution.intToBinStr(10))
        assertEquals("0", solution.intToBinStr(0))
    }

    @Test
    fun queryString() {
        val solution = Solution()
        assertEquals(true,solution.queryString("0110",3))
        assertEquals(false,solution.queryString("0110",4))
    }
}
class Solution {
    fun intToBinStr(num:Int):String{
        if(num == 0) return "0"
        val sb = StringBuilder()
        var num = num
        while (num > 0){
            sb.append((num % 2).toString())
            num /= 2
        }
        return sb.reverse().toString()
    }

    fun queryString(S: String, N: Int): Boolean {
        for(item in 1..N){
            if(!S.contains(intToBinStr(item))){
                return false
            }
        }
        return true
    }
}
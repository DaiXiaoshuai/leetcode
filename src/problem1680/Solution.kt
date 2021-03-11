package problem1680

import java.lang.StringBuilder

/**
 * 1680. 连接连续二进制数字
给你一个整数 n ，请你将 1 到 n 的二进制表示连接起来，并返回连接结果对应的 十进制 数字对 109 + 7 取余的结果。



示例 1：

输入：n = 1
输出：1
解释：二进制的 "1" 对应着十进制的 1 。
示例 2：

输入：n = 3
输出：27
解释：二进制下，1，2 和 3 分别对应 "1" ，"10" 和 "11" 。
将它们依次连接，我们得到 "11011" ，对应着十进制的 27 。
示例 3：

输入：n = 12
输出：505379714
解释：连接结果为 "1101110010111011110001001101010111100" 。
对应的十进制数字为 118505380540 。
对 109 + 7 取余后，结果为 505379714 。


提示：

1 <= n <= 105
 */
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SolutionTest {

    @Test
    fun concatenatedBinary() {
        val solution = Solution()

        assertEquals(27, solution.concatenatedBinary(3))
        assertEquals(1, solution.concatenatedBinary(1))
        assertEquals(505379714, solution.concatenatedBinary(12))
        assertEquals(757631812, solution.concatenatedBinary(100000))


    }
}
class Solution {

    fun concatenatedBinary(n: Int): Int {
        val sb = StringBuilder()
        for(i in 1..n){
            sb.append(Integer.toBinaryString(i))
        }
        sb.length
        return mod(sb).toInt()
    }

    val p = 1000000007
    val unit = 62 //单次计算的位数

    /**
     * 笨办法。网站上有好多高级的解法，我脑子不够用
     */
    fun mod(sb:StringBuilder):Long{

        var index = sb.length
        var result = 0L
        var jinzhiP = 1L
        while (index > 0){
            result = (((((binToInt(sb.subSequence(maxOf(0,index - unit), index)) % p) * jinzhiP) ) % p )+ (result % p)) % p
            jinzhiP = (jinzhiP % p * ((1L shl unit)%p)) % p
            index -= unit
        }
        return result
//         递归会堆栈溢出
//        if(sb.length >= unit) {
//            return (binToInt(sb.subSequence(sb.length - unit, sb.length)) % p + ((mod(sb.delete(sb.length - unit, sb.length)) * ((1 shl unit) % p)) % p)) % p
//        }else{
//            return binToInt(sb) % p
//        }

    }

    fun binToInt(sb: CharSequence):Long{
        var value = 0L
        for(item in sb){
            value = value shl 1
            value += (item - '0')
        }
        return value
    }
}
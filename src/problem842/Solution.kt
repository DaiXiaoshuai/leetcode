package problem842

/**
 *
 * 842. 将数组拆分成斐波那契序列
给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。

形式上，斐波那契式序列是一个非负整数列表 F，且满足：

0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
F.length >= 3；
对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。

返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。



示例 1：

输入："123456579"
输出：[123,456,579]
示例 2：

输入: "11235813"
输出: [1,1,2,3,5,8,13]
示例 3：

输入: "112358130"
输出: []
解释: 这项任务无法完成。
示例 4：

输入："0123"
输出：[]
解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
示例 5：

输入: "1101111"
输出: [110, 1, 111]
解释: 输出 [11,0,11,11] 也同样被接受。
提示：
1 <= S.length <= 200
字符串 S 中只含有数字。
通过次数25,856提交次数52,491
 */

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SolutionTest {

    @Test
    fun splitIntoFibonacci() {
        val solution = Solution()
        assertArrayEquals(intArrayOf(123, 456, 579), solution.splitIntoFibonacci("123456579").toIntArray())
        assertArrayEquals(intArrayOf(1,1,2,3,5,8,13), solution.splitIntoFibonacci("11235813").toIntArray())
        assertArrayEquals(intArrayOf(), solution.splitIntoFibonacci("112358130").toIntArray())
        assertArrayEquals(intArrayOf(), solution.splitIntoFibonacci("0123").toIntArray())
        assertArrayEquals(intArrayOf(11, 0, 11, 11), solution.splitIntoFibonacci("1101111").toIntArray())
    }
}

class Solution {
    fun splitIntoFibonacci(S: String): List<Int> {
        var i = 1
        while (S.substring(0 until i).toLong() < Int.MAX_VALUE) {
            var j = i + 1
            while (S.substring(i until j).toLong() < Int.MAX_VALUE) {
                val splitList = split(i, j, S)
                if (splitList.isNotEmpty()) {
                    return splitList
                }
                j++
                if (j > S.length) break
            }
            i++
            if (i >= S.length) break
        }
        return ArrayList<Int>(0)
    }

    private fun split(i: Int, j: Int, s: String): List<Int> {
        val list = ArrayList<Int>()
        var startIndex = j
        if( j-i>1 && s[i] == '0') return ArrayList<Int>(0)
        if(i > 1 && s[0] == '0') return ArrayList<Int>(0)
        var prev = s.substring(i until j).toInt()
        var prev2 = s.substring(0 until i).toInt()
        list.add(prev2)
        list.add(prev)
        while (startIndex < s.length) {
            val v = prev + prev2.toLong()
            if (v > Int.MAX_VALUE) return ArrayList<Int>(0)
            if (s.subSequence(startIndex until s.length).startsWith(v.toString())) {
                list.add(v.toInt())
                startIndex += v.toString().length
                prev2 = prev
                prev = v.toInt()
            } else {
                return ArrayList<Int>(0)
            }
        }
        if (list.size < 3) return ArrayList<Int>(0)
        return list
    }
}

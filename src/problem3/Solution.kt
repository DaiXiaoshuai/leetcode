package problem3

import org.junit.jupiter.api.Test
import java.lang.Integer.max
import kotlin.test.assertEquals

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        if(s.isEmpty()) return 0;
        if(s.length == 1) return 1;
        var max = 1;
        var beginIndex = 0;
        var endIndex = 0;
        for (index in 1 until s.length) {
            val dupIndex = contain(s, s[index], beginIndex, endIndex);
            if (dupIndex == -1) {
                endIndex = index
                max = max(max, endIndex - beginIndex + 1)
            } else {
                beginIndex = dupIndex + 1
                endIndex = index
            }
        }
        return max;
    }

    /**
     * 对 source[endIndex] 进行检测
     * @return 首个target所在的下标，-1 不包含target
     */
    fun contain(source: String, target: Char, beginIndex: Int = 0, endIndex: Int = source.length - 1): Int {
        for (index in beginIndex..endIndex) {
            if (source[index] == target)
                return index
        }
        return -1
    }

    @Test
    fun testLengthOfLongestSubstring() {
        assertEquals(3, Solution().lengthOfLongestSubstring("abcabcbb"))
        assertEquals(1, Solution().lengthOfLongestSubstring("bbbbb"))
        assertEquals(3, Solution().lengthOfLongestSubstring("pwwkew"))
    }
}
package problem1340

/**
 * 1340. 跳跃游戏 V
给你一个整数数组 arr 和一个整数 d 。每一步你可以从下标 i 跳到：

i + x ，其中 i + x < arr.length 且 0 < x <= d 。
i - x ，其中 i - x >= 0 且 0 < x <= d 。
除此以外，你从下标 i 跳到下标 j 需要满足：arr[i] > arr[j] 且 arr[i] > arr[k] ，其中下标 k 是所有 i 到 j 之间的数字（更正式的，min(i, j) < k < max(i, j)）。

你可以选择数组的任意下标开始跳跃。请你返回你 最多 可以访问多少个下标。

请注意，任何时刻你都不能跳到数组的外面。



示例 1：



输入：arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
输出：4
解释：你可以从下标 10 出发，然后如上图依次经过 10 --> 8 --> 6 --> 7 。
注意，如果你从下标 6 开始，你只能跳到下标 7 处。你不能跳到下标 5 处因为 13 > 9 。你也不能跳到下标 4 处，因为下标 5 在下标 4 和 6 之间且 13 > 9 。
类似的，你不能从下标 3 处跳到下标 2 或者下标 1 处。
示例 2：

输入：arr = [3,3,3,3,3], d = 3
输出：1
解释：你可以从任意下标处开始且你永远无法跳到任何其他坐标。
示例 3：

输入：arr = [7,6,5,4,3,2,1], d = 1
输出：7
解释：从下标 0 处开始，你可以按照数值从大到小，访问所有的下标。
示例 4：

输入：arr = [7,1,7,1,7,1], d = 2
输出：2
示例 5：

输入：arr = [66], d = 1
输出：1


提示：

1 <= arr.length <= 1000
1 <= arr[i] <= 10^5
1 <= d <= arr.length
通过次数3,368提交次数6,073
 */
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SolutionTest {

    @Test
    fun maxJumps() {
        val solution = Solution()
        assertEquals(4, solution.maxJumps(intArrayOf(6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12), 2))
        assertEquals(1, solution.maxJumps(intArrayOf(3, 3, 3, 3, 3), 3))
        assertEquals(7, solution.maxJumps(intArrayOf(7, 6, 5, 4, 3, 2, 1), 1))
        assertEquals(2, solution.maxJumps(intArrayOf(7, 1, 7, 1, 7, 1), 2))
        assertEquals(1, solution.maxJumps(intArrayOf(66), 1))
    }
}

class Solution {
    lateinit var dp: IntArray

    lateinit var arr: IntArray
    var d = 0
    fun maxJumps(arr: IntArray, d: Int): Int {
        this.arr = arr
        this.d = d
        dp = IntArray(arr.size)
        var maxValue = 1
        for (index in arr.indices) {
            maxValue = maxOf(getDp(index), maxValue)
        }
        return maxValue
    }

    fun getDp(index: Int): Int {
        if (dp[index] != 0) {
            return dp[index]
        } else {
            var maxValue = 0
            for (item in index - 1 downTo maxOf(0, index - d)) {
                if (arr[item] >= arr[index]) break;
                val value = getDp(item)
                if (value > maxValue) {
                    maxValue = value
                }
            }
            for (item in (index + 1)..minOf(arr.size - 1, index + d)) {
                if (arr[item] >= arr[index]) break;
                val value = getDp(item)
                if (value > maxValue) {
                    maxValue = value
                }
            }
            dp[index] = maxValue + 1
            return dp[index]
        }
    }
}

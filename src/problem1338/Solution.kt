package problem1338

import java.util.*
import kotlin.collections.HashMap

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
/**
 * 1338. 数组大小减半
给你一个整数数组 arr。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。

返回 至少 能删除数组中的一半整数的整数集合的最小大小。



示例 1：

输入：arr = [3,3,3,3,5,5,5,2,2,7]
输出：2
解释：选择 {3,7} 使得结果数组为 [5,5,5,2,2]、长度为 5（原数组长度的一半）。
大小为 2 的可行集合有 {3,5},{3,2},{5,2}。
选择 {2,7} 是不可行的，它的结果数组为 [3,3,3,3,5,5,5]，新数组长度大于原数组的二分之一。
示例 2：

输入：arr = [7,7,7,7,7,7]
输出：1
解释：我们只能选择集合 {7}，结果数组为空。
示例 3：

输入：arr = [1,9]
输出：1
示例 4：

输入：arr = [1000,1000,3,7]
输出：1
示例 5：

输入：arr = [1,2,3,4,5,6,7,8,9,10]
输出：5


提示：

1 <= arr.length <= 10^5
arr.length 为偶数
1 <= arr[i] <= 10^5
通过次数7,197提交次数11,326
 */
class Solution {
    fun minSetSize(arr: IntArray): Int {
        val map = HashMap<Int, Int>()
        for(item in arr){
            if(map.containsKey(item)){
                map[item] = map[item]!! + 1
            }else{
                map[item] = 1
            }
        }
        val countArray = IntArray(map.size)
        var index = 0
        for(item in map){
            countArray[index++] = item.value
        }
        countArray.sortDescending()
        var numCount = 0
        var count = 0
        for(item in countArray){
            numCount += 1
            count += item
            if(count >= arr.size / 2){
                return numCount
            }
        }
        return -1
    }
}


internal class SolutionTest {

    @Test
    fun minSetSize() {
        val solution = Solution()
        assertEquals(5,solution.minSetSize(intArrayOf(1,2,3,4,5,6,7,8,9,10)))
        assertEquals(2,solution.minSetSize(intArrayOf(3,3,3,3,5,5,5,2,2,7)))
        assertEquals(1,solution.minSetSize(intArrayOf(7,7,7,7,7,7)))
        assertEquals(1,solution.minSetSize(intArrayOf(1, 9)))
        assertEquals(1,solution.minSetSize(intArrayOf(1000,1000,3,7)))

    }
}
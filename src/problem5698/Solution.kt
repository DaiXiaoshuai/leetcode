package problem5698

import java.lang.Math.abs

/**
 * 5698. 构成特定和需要添加的最少元素 显示英文描述
通过的用户数1962
尝试过的用户数2515
用户总通过次数1972
用户总提交次数5415
题目难度Medium
给你一个整数数组 nums ，和两个整数 limit 与 goal 。数组 nums 有一条重要属性：abs(nums[i]) <= limit 。

返回使数组元素总和等于 goal 所需要向数组中添加的 最少元素数量 ，添加元素 不应改变 数组中 abs(nums[i]) <= limit 这一属性。

注意，如果 x >= 0 ，那么 abs(x) 等于 x ；否则，等于 -x 。



示例 1：

输入：nums = [1,-1,1], limit = 3, goal = -4
输出：2
解释：可以将 -2 和 -3 添加到数组中，数组的元素总和变为 1 - 1 + 1 - 2 - 3 = -4 。
示例 2：

输入：nums = [1,-10,9,1], limit = 100, goal = 0
输出：1


提示：

1 <= nums.length <= 105
1 <= limit <= 106
-limit <= nums[i] <= limit
-109 <= goal <= 109
 */
class Solution {
    fun minElements(nums: IntArray, limit: Int, goal: Int): Int {
        var sum:Long = 0
        for(item in nums){
            sum += item
        }

        if((goal - sum) % limit == 0L) return kotlin.math.abs(((goal - sum) / limit).toInt())
        else return kotlin.math.abs(((goal - sum) / limit).toInt()) +1
    }
}
package problem1026



/**
 * 1026. 节点与其祖先之间的最大差值
给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。

（如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）



示例 1：



输入：root = [8,3,10,1,6,null,14,null,null,4,7,13]
输出：7
解释：
我们有大量的节点与其祖先的差值，其中一些如下：
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
示例 2：


输入：root = [1,null,2,null,0,3]
输出：3


提示：

树中的节点数在 2 到 5000 之间。
0 <= Node.val <= 105
 */

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
   var right: TreeNode? = null
 }
class Solution {
    var result = 0
    fun maxAncestorDiff(root: TreeNode?): Int {
        if(root == null) return result
        getMaxMinValue(root)
        return result
    }

    fun getMaxMinValue(root: TreeNode) :Pair<Int, Int>{
        if(root.left == null && root.right == null){
            return Pair(root.`val`, root.`val`)
        }
        if(root.left == null){
            val rightPair = getMaxMinValue(root.right!!)
            val pair =  Pair(minOf(root.`val`, rightPair.first), maxOf(root.`val`, rightPair.second))
            result = maxOf(Math.abs(root.`val` - pair.first), Math.abs(root.`val` - pair.second), result)
            return pair
        }else if(root.right == null){
            val leftPair = getMaxMinValue(root.left!!)
            val pair =  Pair(minOf(root.`val`, leftPair.first), maxOf(root.`val`, leftPair.second))
            result = maxOf(Math.abs(root.`val` - pair.first), Math.abs(root.`val` - pair.second), result)
            return pair
        }else{
            val rightPair = getMaxMinValue(root.right!!)
            val leftPair = getMaxMinValue(root.left!!)
            val pair =  Pair(minOf(root.`val`, leftPair.first, rightPair.first), maxOf(root.`val`, leftPair.second, rightPair.second))
            result = maxOf(Math.abs(root.`val` - pair.first), Math.abs(root.`val` - pair.second), result)
            return pair
        }
    }
}



















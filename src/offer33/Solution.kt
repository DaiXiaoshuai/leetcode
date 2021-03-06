package offer33

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。



参考以下这颗二叉搜索树：

5
/ \
2   6
/ \
1   3
示例 1：

输入: [1,6,3,2,5]
输出: false
示例 2：

输入: [1,3,2,6,5]
输出: true


提示：

数组长度 <= 1000
 */
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SolutionTest {

    @Test
    fun verifyPostorder() {
        val solution = Solution()
        assertTrue(solution.verifyPostorder(intArrayOf(1,3,2,6,5)))
        assertFalse(solution.verifyPostorder(intArrayOf(1,6,3,2,5)))
        assertFalse(solution.verifyPostorder(intArrayOf()))
    }
}
class Solution {

    class TreeNode(val value: Int){
        var left:TreeNode? = null
        var right:TreeNode? = null

        val order:ArrayList<Int> = arrayListOf<Int>()

        private fun rootLastWalk(root:TreeNode):Int{
            if(root.left!=null){
                order.add(rootLastWalk(root.left!!))
            }
            if(root.right!=null){
                order.add(rootLastWalk(root.right!!))
            }
            return root.value
        }

        fun getRootLastOrder():List<Int>{
            order.add(rootLastWalk(this))
            return order
        }
    }

    fun verifyPostorder(postorder: IntArray): Boolean {
        if(postorder.isEmpty()) return true
        var tree : TreeNode? = null
        for(index in postorder.lastIndex downTo 0){
            tree = addNode(tree, postorder[index])
        }
        tree!!.getRootLastOrder()
        return postorder contentEquals tree.order.toIntArray()
    }

    fun addNode(tree:TreeNode?, value:Int):TreeNode{
        if(tree == null) return TreeNode(value)
        if(value < tree.value){
            tree.left = addNode(tree.left, value)
        }else{
            tree.right = addNode(tree.right, value)
        }
        return tree
    }
}
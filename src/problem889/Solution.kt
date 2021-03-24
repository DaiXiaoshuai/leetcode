package problem889

/**
 * 889. 根据前序和后序遍历构造二叉树
返回与给定的前序和后序遍历匹配的任何二叉树。

pre 和 post 遍历中的值是不同的正整数。



示例：

输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
输出：[1,2,3,4,5,6,7]


提示：

1 <= pre.length == post.length <= 30
pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。
通过次数10,728提交次数15,856
 */


  class TreeNode(var `val`: Int) {
      var left: TreeNode? = null
      var right: TreeNode? = null
  }

class Solution {
//    fun constructFromPrePost(pre: IntArray, post: IntArray): TreeNode? {
//
//    }
}
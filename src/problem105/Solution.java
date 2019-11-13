package problem105;

import common.TreeNode;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0) return null;
        return generate(preorder,0,preorder.length-1, inorder,0,inorder.length-1);
    }

    TreeNode generate(int[] preorder, int l1,int r1, int[] inorder, int l2, int r2){
        TreeNode root = new TreeNode(preorder[l1]);
        int inorderIndex = indexOf(inorder, root.val); // 根节点在中序遍历中的位置
        if(l1<r1){
            int childIndex = indexOf(inorder, preorder[l1+1]);
            if(childIndex < inorderIndex) { // 子节点为左节点
                if(inorderIndex == r2) {
                    root.left = generate(preorder, l1 + 1, r1, inorder, l2, inorderIndex - 1);
                } else{
                    int rightChildRootPreIndex = r1; // 右子树的根节点在前序中的下标
                    while(rightChildRootPreIndex > l1){
                        if(indexOf(inorder,preorder[rightChildRootPreIndex]) <= inorderIndex) break;
                        else rightChildRootPreIndex--;
                    }
                    root.left = generate(preorder, l1+1,rightChildRootPreIndex , inorder, l2, inorderIndex-1);
                    root.right = generate(preorder, rightChildRootPreIndex+1,r1,inorder, inorderIndex+1,r2);
                }
            } else{
                root.right = generate(preorder, l1+1, r1, inorder,childIndex, r2);
            }
        }
        return root;
    }

    int indexOf(int[] array, int target){
        for(int i=0;i<array.length;i++){
            if(target == array[i]) return i;
        }
        return -1;
    }
}

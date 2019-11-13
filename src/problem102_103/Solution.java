package problem102_103;

import common.TreeNode;

import java.util.*;


class Solution {

    /**
     * problem102
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Queue<TreeNode> queue1 = new LinkedList<>();
        queue1.add(root);
        Deque<TreeNode> queue2 = new LinkedList<>();
        while (!queue2.isEmpty() || !queue1.isEmpty()){
            LinkedList<Integer> rowResult = new LinkedList<>();
            while(!queue1.isEmpty()){
                TreeNode node = queue1.poll();
                rowResult.add(node.val);
                if(node.left!=null) queue2.add(node.left);
                if(node.right!=null) queue2.add(node.right);
            }
            if(rowResult.size()>0)
                result.add(rowResult);
            rowResult = new LinkedList<>();
            while (!queue2.isEmpty()){
                TreeNode node = queue2.poll();
                rowResult.add(node.val);
                if(node.left!=null) queue1.add(node.left);
                if(node.right!=null) queue1.add(node.right);
            }
            if(rowResult.size()>0)
                result.add(rowResult);
        }
        return result;
    }


    /**
     * problem103
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     *
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回锯齿形层次遍历如下：
     *
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Queue<TreeNode> queue1 = new LinkedList<>();
        queue1.add(root);
        Deque<TreeNode> queue2 = new LinkedList<>();
        while (!queue2.isEmpty() || !queue1.isEmpty()){
            LinkedList<Integer> rowResult = new LinkedList<>();
            while(!queue1.isEmpty()){
                TreeNode node = queue1.poll();
                rowResult.add(node.val);
                if(node.left!=null) queue2.add(node.left);
                if(node.right!=null) queue2.add(node.right);
            }
            if(rowResult.size()>0)
                result.add(rowResult);
            rowResult = new LinkedList<>();
            while (!queue2.isEmpty()){
                TreeNode node = queue2.poll();
                rowResult.addFirst(node.val);
                if(node.left!=null) queue1.add(node.left);
                if(node.right!=null) queue1.add(node.right);
            }
            if(rowResult.size()>0)
                result.add(rowResult);
        }
        return result;
    }
}

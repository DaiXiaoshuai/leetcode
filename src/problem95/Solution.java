package problem95;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> roots = new ArrayList<TreeNode>();
        if(n<=0) return roots;
        return helper(1,n);
    }

    public List<TreeNode> helper(int start, int end){
        List<TreeNode> roots = new ArrayList<TreeNode>();
        if(start>end){
            roots.add(null);
            return roots;
        }
        for(int i = start;i<=end;i++){
            List<TreeNode> leftRoots = helper(start, i-1);
            List<TreeNode> rightRoots = helper(i+1, end);
            for(TreeNode lRoot: leftRoots){
                for(TreeNode rRoot:rightRoots){
                    TreeNode root = new TreeNode(i);
                    root.left = lRoot;
                    root.right = rRoot;
                    roots.add(root);
                }
            }
        }
        return roots;
    }



}

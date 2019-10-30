package problem22;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    static class TreeNode {
        TreeNode(){}
        TreeNode(String s){this.desc = s; }

        List<TreeNode> children = new ArrayList<>();
        String desc = "";


        /**
         * 可拓展的node1
         */
        TreeNode lNode;

        /**
         * 可拓展的node2
         */
        TreeNode rNode;

        /**
         * （（）） 形如此的结果，用于表示最外层的括号
         */
        String leftDesc = "";
        String rightDesc = "";

    }

    /**
     * 生成上层节点的子节点
     * @param parent
     * @return
     */
    public  List<TreeNode> generateChildren (TreeNode parent){
        List<TreeNode> result = new ArrayList<TreeNode>();
        for(TreeNode child : parent.lNode.children){
            TreeNode node = new TreeNode();
            node.lNode = child;
            node.rNode = parent.rNode;
            node.leftDesc = parent.leftDesc;
            node.rightDesc = parent.rightDesc;
            node.desc = node.leftDesc + node.lNode.desc + (node.rNode == null?"":node.rNode.desc) + node.rightDesc;
            if(!isDuplicated(result, node.desc)) result.add(node);
        }
        if(parent.rNode !=null){
            for(TreeNode child:parent.rNode.children){
                TreeNode node = new TreeNode();
                node.lNode = parent.lNode;
                node.rNode = child;
                node.leftDesc = parent.leftDesc;
                node.rightDesc = parent.rightDesc;
                node.desc = node.leftDesc + node.lNode.desc + (node.rNode == null?"":node.rNode.desc) + node.rightDesc;
                if(!isDuplicated(result, node.desc)) result.add(node);
            }
        }
        parent.children = result;
        return result;
    }

    public List<String> generateParenthesis(int n) {
        if(n == 0) return  new ArrayList<>();
        if(n == 1) {
            List<String>  r = new ArrayList<>(1);
            r.add("()");
            return r;
        }
        List<TreeNode> result = new ArrayList<TreeNode>();
        // n=1 时的根节点
        TreeNode root = new TreeNode("()");
        root.lNode = root;
        // n=2时，由根节点拓展出的子节点1
        TreeNode node2 = new TreeNode("()()");
        node2.lNode = root;
        node2.rNode = root;
        root.children.add(node2);
        // n=2时，由根节点拓展出的子节点2
        TreeNode node3 = new TreeNode("(())");
        node3.lNode = root;
        node3.leftDesc = "(";
        node3.rightDesc = ")";
        root.children.add(node3);

        result.add(node2);
        result.add(node3);

        for(int i=2;i<n;i++){
            List<TreeNode> tmp = new ArrayList<TreeNode>();
            for(TreeNode node : result){
                tmp.addAll(generateChildren(node));
            }
            result = tmp;
        }
        //将对象列表转成字符串列表，此处有个去重操作
        ArrayList<String> res = new ArrayList<>(result.size());
        for(TreeNode node : result){
            if(!isDuplicated2(res,node.desc)){
                res.add(node.desc);
            }
        }
        return res;
    }

    /**
     * 用于去重操作
     */
    public boolean isDuplicated2(List<String> head,String string){
        for(String node : head){
            if(node.equals(string)) return true;
        }
        return false;
    }
    /**
     * 用于去重操作
     */
    public boolean isDuplicated(List<TreeNode> head, String string){
        for(TreeNode node : head){
            if(node.desc.equals(string)) return true;
        }
        return false;
    }


    @Test
    void test(){
        Solution solution = new Solution();
        assertEquals(5,solution.generateParenthesis(3).size());
        assertEquals(14,solution.generateParenthesis(4).size());
        assertEquals(42,solution.generateParenthesis(5).size());

    }
}

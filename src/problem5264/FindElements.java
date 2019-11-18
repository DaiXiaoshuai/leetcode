package problem5264;

import common.TreeNode;

class FindElements {

    TreeNode root;

    public FindElements(TreeNode root) {
        helper(root, 0);
        this.root = root;

    }

    public void helper(TreeNode node, int value){
        if(node !=null){
            node.val = value;
            if(node.left !=null) helper(node.left, value*2 + 1);
            if(node.right!=null) helper(node.right,value*2 + 2);
        }
    }

    public boolean find(int target) {
        return findHelper(root, target);
    }

    public boolean findHelper(TreeNode root,int  target){
        if(root==null) return false;
        if(root.val == target) return true;
        return findHelper(root.left, target) || findHelper(root.right,target);
    }
}

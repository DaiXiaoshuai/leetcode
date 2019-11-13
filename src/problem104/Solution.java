package problem104;

import common.TreeNode;
import org.junit.jupiter.api.Test;

public class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

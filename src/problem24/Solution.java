package problem24;

import org.junit.jupiter.api.Test;
import problem2.ListNode;

public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null) return null;
        ListNode node = head;
        ListNode result = null;
        if(head.next!=null) result = head.next;
        else return head;
        ListNode tmp = null;
        ListNode preNode = null;
        while (node!=null &&node.next!=null){
            if(preNode != null)
                preNode.next =node.next;
            preNode = node;
            tmp = node.next.next;
            node.next.next = node;
            node.next = tmp;
            node = tmp;

        }
        return result;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        solution.swapPairs(ListNode.toList(new int[]{1,2,3,4}));
    }
}

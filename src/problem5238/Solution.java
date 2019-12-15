package problem5238;

import problem2.ListNode;

public class Solution {
    public int getDecimalValue(ListNode head) {
        int result = 0;
        while(head!=null){
            result = (result << 1) + head.val;
            head = head.next;
        }
        return result;
    }
}

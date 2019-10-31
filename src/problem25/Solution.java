package problem25;

import org.junit.jupiter.api.Test;
import problem2.ListNode;

public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1) return head;
        ListNode result = null;
        ListNode preTail = null;
        ListNode tmpHead = null;
        ListNode tmp = null;
        ListNode firstNode;
        while(head!=null){
            firstNode = head;
            tmpHead = null;
            int i = 0;
            for(i=0;i<k;i++){
                if(head == null) break;
                tmp = head.next;
                head.next = tmpHead;
                tmpHead = head;
                head = tmp;
            }
            if(i!=k && i!=1){
                if(preTail!=null) preTail.next =firstNode;
                else result = firstNode;
                head = tmpHead;
                tmpHead = null;
                for(int j=0;j<i;j++){
                    tmp = head.next;
                    head.next = tmpHead;
                    tmpHead = head;
                    head = tmp;
                }
                break;
            }else{
                if(preTail != null)
                    preTail.next = tmpHead;
                preTail = firstNode;
            }
            if(result == null) result = tmpHead;
        }
        return result;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        ListNode listNode = solution.reverseKGroup(ListNode.toList(new int[]{1, 2, 3, 4, 5}), 9);
        System.out.println(12);
    }
}

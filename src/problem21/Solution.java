package problem21;

import problem2.ListNode;

public class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        ListNode node = null;
        while(l1!=null && l2!=null){
            if(l1.val<=l2.val){ node = l1;l1 = l1.next; }
            else{ node = l2;l2 =l2.next;}
            if(head == null) {
                head = node;
                tail = node;
            }
            else {
                tail.next = node;
                tail = tail.next;
            }
        }
        if(tail == null){
            if(l1!=null) return l1;
            else return l2;
        }
        if(l1 == null) tail.next = l2;
        if(l2 == null) tail.next = l1;
        return head;
    }

}

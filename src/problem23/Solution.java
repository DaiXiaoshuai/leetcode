package problem23;

import problem2.ListNode;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null;
        for(ListNode node : lists){
            head = mergeTwoLists(head, node);
        }
        return head;

    }

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

//    public ListNode sort(ListNode head){
//        ListNode node = head;
//        int size = 0;
//        while(node.next!=null){
//            if(node.val >)
//        }
//    }

}

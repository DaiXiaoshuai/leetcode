package problem19;

import org.junit.jupiter.api.Test;
import problem2.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        for(int i=0;i<n;i++){
            node = node.next;
        }
        if(node==null) return head.next;
        ListNode target = head;
        while (node.next!=null){
            node = node.next;
            target = target.next;
        }
        target.next = target.next.next;
        return head;
    }

    @Test
    void test(){
        ListNode head = ListNode.toList(new int[]{1,2,3,4,5});
        Solution solution = new Solution();
//        assertEquals(1,solution.removeNthFromEnd(head,2).val);
//        assertEquals(1,solution.removeNthFromEnd(head,3).val);
//        assertEquals(1,solution.removeNthFromEnd(head,1).val);
        assertEquals(1,solution.removeNthFromEnd(head,4).val);
//        assertEquals(2,solution.removeNthFromEnd(head,5).val);

    }
}

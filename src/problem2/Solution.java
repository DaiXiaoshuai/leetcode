package problem2;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    //TODO 用于非逆置链表数相加，未进行测试
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode rl1 = reverse(l1);
        ListNode rl2 = reverse(l2);
        ListNode pre1 = rl1;
        ListNode pre2 = rl2;
        ListNode head  = null;
        ListNode prev = null;
        int flag = 0;
        while (pre1 != null && pre2 != null){
            int value = pre1.val + pre2.val + flag;
            if(value >= 10) {
                flag = 1;
                value = value%10;
            }else {
                flag = 0;
            }
            ListNode node = new ListNode(value);
            if(head == null){
                head = node;
                prev = node;
            }else{
                prev.next = node;
                prev = node;
            }
            pre1 = pre1.next;
            pre2 = pre2.next;
        }
        if(pre1 == null){
            while (pre2!=null){
                int value = pre2.val + flag;
                if(value>10){
                    flag = 1;
                    value = value%10;
                }
                ListNode node = new ListNode(pre2.val + flag);
                prev.next = node;
                prev = node;
            }
        }else{
            while (pre1!=null){
                int value = pre1.val + flag;
                if(value>10){
                    flag = 1;
                    value = value%10;
                }
                ListNode node = new ListNode(pre1.val + flag);
                prev.next = node;
                prev = node;
            }
        }

        return reverse(head);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode head = null;
        ListNode head1 = l1;
        ListNode head2 = l2;
        ListNode tmp = null; // 记录每次计算出的结果节点
        int flag = 0; // 进位标志
        while(l1!=null && l2!=null){ //TODO 看了解析，这边可以用 或 条件，可显著减少代码量
            int value = l1.val + l2.val + flag;
            if(value >= 10)
                flag = 1;
            else
                flag = 0;
            ListNode node = new ListNode(value % 10);
            if(head == null)
                head = node;
            else
                tmp.next = node;
            tmp = node;
            l1 = l1.next;
            l2 = l2.next;
        }
        if(l1 == null){
            while (l2!=null){
                int value = l2.val + flag;
                if(value >= 10)
                    flag = 1;
                else
                    flag = 0;
                ListNode node = new ListNode(value % 10);
                tmp.next = node;
                tmp = node;
                l2 = l2.next;
            }
        }
        if(l2 == null){
            while (l1!=null){
                int value = l1.val + flag;
                if(value >= 10)
                    flag = 1;
                else
                    flag = 0;
                ListNode node = new ListNode(value % 10);
                tmp.next = node;
                tmp = node;
                l1 = l1.next;
            }
        }
        if(flag == 1){
            tmp.next = new ListNode(1);
        }
        return head;
    }

    /**
     * 链表逆置
     * @param head 链表头元素
     * @return 逆置的链表头元素
     */
    public static ListNode reverse(ListNode head){
        ListNode leftHead = head;
        ListNode tmp = null;
        while(leftHead != null){
            if(leftHead == head) {
                leftHead = head.next;
                head.next = null;
            }else{
                tmp = leftHead.next;
                leftHead.next = head;
                head = leftHead;
                leftHead = tmp;
            }
        }
        return head;
    }
}
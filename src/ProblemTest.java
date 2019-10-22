import org.junit.jupiter.api.Test;
import problem2.ListNode;
import problem2.Solution;

import java.util.logging.SocketHandler;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class ProblemTest {

    @Test
    public void twoSum() {
        Problem1 problem1 = new Problem1();
        assertArrayEquals(new int[]{0, 1},problem1.twoSum(new int[]{2,7,11,5}, 9));
        assertArrayEquals(new int[]{0, 3},problem1.twoSum(new int[]{0,4,3,0}, 0));
        assertArrayEquals(new int[]{2, 4},problem1.twoSum(new int[]{-1,-2,-3,-4,-5}, -8));
    }

    /**
     * 链表逆置测试
     */
    @Test
    public void testReverse(){
        ListNode head = null;
        ListNode pre = head;
        int nums[] = {1, 2, 3,4,5,6};
        int reverse[] = {6,5,4,3,2,1};
        for(int num: nums){
            ListNode node = new ListNode();
            node.val = num;
            if(head == null){
                head = node;
                pre = head;
            }else{
                pre.next = node;
                pre = node;
            }
        }
        assertArrayEquals(nums, ListNode.toArray(head));
        assertArrayEquals(reverse, ListNode.toArray(Solution.reverse(head)));

    }

    /**
     * 数组转list
     */
    @Test
    public void testIntArrayToListNode(){
        assertArrayEquals(new int[] {8,0,7}, ListNode.toArray( ListNode.toList(new int[]{8,0,7})));
    }

    @Test
    public void testAddTwoNumbers(){
        Solution solution = new Solution();
        assertArrayEquals(new int[]{7,0,8}, ListNode.toArray(solution.addTwoNumbers(ListNode.toList(new int[]{2, 4, 3}), ListNode.toList(new int[]{5,6,4}))));
        assertArrayEquals(new int[]{0,1}, ListNode.toArray(solution.addTwoNumbers(ListNode.toList(new int[]{5}), ListNode.toList(new int[]{5}))));
        for(int i =0;i<0;i++){}
    }
}

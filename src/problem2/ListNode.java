package problem2;

import java.util.ArrayList;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(){}

    public static int[] toArray(ListNode head){
        ArrayList<Integer> integers = new ArrayList<>();
        while (head!=null){
            integers.add(head.val);
            head = head.next;
        }
        int[] nums = new int[integers.size()];
        for(int i =0;i<nums.length;i++){
            nums[i] = integers.get(i);
        }
        return  nums;
    }

    public static ListNode toList(int [] data){
        if(data.length == 0) return null;
        ListNode result = null;
        ListNode tmp = null;
        for(int value : data){
            ListNode node = new ListNode(value);
            if(result == null){
                result = node;
            }else{
                tmp.next = node;
            }
            tmp = node;
        }
        return result;
    }
}

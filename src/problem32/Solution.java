package problem32;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public int longestValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> valueStack = new Stack<>();
        int maxCount = 0;
        int count = 0;
        boolean prePop = false;
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i) == '(') {
                stack.push(s.charAt(i));
                if(prePop) {
                    valueStack.push(count + 2);
                }
                else {
                    valueStack.push(2);
                }
                count = 0;

            }
            else{
                if(stack.isEmpty()) {
                    stack.removeAllElements();
                    valueStack.removeAllElements();
                    prePop = false;
                    if(count>maxCount) maxCount = count;
                    count = 0;
                } else if(stack.peek() == '(') {
                    prePop = true;
                    count += valueStack.pop();
                    if(count>maxCount) maxCount = count;
                    stack.pop();
                }
            }
        }
        return Math.max(count, maxCount);
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertEquals(2,solution.longestValidParentheses("(()"));
        assertEquals(4,solution.longestValidParentheses(")()())"));
        assertEquals(2,solution.longestValidParentheses("()(()"));
        assertEquals(22,solution.longestValidParentheses(")(((((()())()()))()(()))("));
    }
}

package problem5278;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 5278. 分割回文串 III 显示英文描述
 * 给你一个由小写字母组成的字符串 s，和一个整数 k。
 * <p>
 * 请你按下面的要求分割字符串：
 * <p>
 * 首先，你可以将 s 中的部分字符修改为其他的小写英文字母。
 * 接着，你需要把 s 分割成 k 个非空且不相交的子串，并且每个子串都是回文串。
 * 请返回以这种方式分割字符串所需修改的最少字符数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abc", k = 2
 * 输出：1
 * 解释：你可以把字符串分割成 "ab" 和 "c"，并修改 "ab" 中的 1 个字符，将它变成回文串。
 * 示例 2：
 * <p>
 * 输入：s = "aabbc", k = 3
 * 输出：0
 * 解释：你可以把字符串分割成 "aa"、"bb" 和 "c"，它们都是回文串。
 * 示例 3：
 * <p>
 * 输入：s = "leetcode", k = 8
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= s.length <= 100
 * s 中只含有小写英文字母。
 */
public class Solution {

    static class Desc {
        public Desc(int[] indexes, int count) {
            this.indexes = indexes;
            this.count = count;
        }

        @Override
        public boolean equals(Object obj) {
            if(((Desc)obj).count != this.count) return false;
            return Arrays.equals(this.indexes, ((Desc) obj).indexes);
        }

        int[] indexes;
        int count = 0;
    }

    String s;

    // 暴力解法。。。过不了时间限制
    public int palindromePartition(String s, int k) {
        this.s = s;

        if (k == 1) return check(0, s.length());
        ArrayList<Desc> record = new ArrayList<>();
        int[] indexes = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            indexes[i] = i;
        }
        record.add(new Desc(indexes, 0));
        for (int i = s.length() - 1; i >= k; i--) {  // i 分为几部分
            int len = i; // 下标数组长度
            ArrayList<Desc> tmp = new ArrayList<>();
            for (Desc item : record) {
                for (int j = 0; j < i; j++) {
                    int[] ints = new int[len];
                    int m = 0;
                    for (int n = 0; n < item.indexes.length; ) {
                        if (n != j + 1) {
                            ints[m] = item.indexes[n];
                            m++;
                            n++;
                        } else {
                            n++;
                        }
                    }
                    int count = 0;
                    for (int p = 0; p < len - 1; p++) {
                        count += check(ints[p], ints[p + 1]);
                    }
                    count += check(ints[i - 1], s.length());
                    if(count <= s.length()/2){
                        Desc desc = new Desc(ints, count);
                        if (!tmp.contains(desc)) {
                            tmp.add(desc);
                        }
                    }

                }
            }
            record = tmp;
        }
        int min = s.length();
        for (Desc item : record) {
            if (item.count < min) {
                min = item.count;
            }
        }
        return min;

    }

    /**
     * @param start 起始下标【包含】
     * @param end   终止下标【不包含】
     * @return
     */
    int check(int start, int end) {
        int count = 0;
        end -= 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                count++;
            }
            ;
            start++;
            end--;
        }
        return count;
    }

    @Test
    void test() {
        Solution solution = new Solution();
        assertEquals(5, solution.palindromePartition("jearvgnojzacvmin",4));
        assertEquals(2, solution.palindromePartition("tcymekt", 4));

        assertEquals(2, solution.palindromePartition("aabbc", 1));

        assertEquals(0, solution.palindromePartition("aabbc", 5));
        assertEquals(0, solution.palindromePartition("aabbc", 4));
        assertEquals(0, solution.palindromePartition("aabbc", 3));
        assertEquals(1, solution.palindromePartition("aabbc", 2));
        assertEquals(1, solution.palindromePartition("abc", 2));
        assertEquals(0, solution.palindromePartition("leetcode", 8));
        assertEquals(3, solution.palindromePartition("ifamcbfrbprx", 5));


    }
}

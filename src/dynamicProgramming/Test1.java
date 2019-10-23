package dynamicProgramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {

    /**
     * 算法导论-动态规划-钢条切割
     *
     * @param values
     * @param length
     * @return
     */
    public int slice(int[] values, int length) {
        int[] results = new int[values.length];
        int[] slicePlan = new int[values.length];
        int resultMax = 0;
        for (int i = 1; i <= length; i++) {
            int max = 0;
            if (i == 1) {
                results[i] = values[i] * values[i];
                slicePlan[i] = 1;
                resultMax = values[1];
            } else {
                slicePlan[i] = i;
                results[i] = values[i];
                for (int j = 0; j < i; j++) {
                    int tmp = results[j] + results[i - j];
                    if (tmp > max) {
                        max = tmp;
                        if (max > resultMax) resultMax = max;
                        results[i] = tmp;
                        slicePlan[i] = j;
                    }
                }
            }
        }
        return resultMax;
//        return slicePlan;
    }

    @Test
    void testSlice() {
        Test1 test = new Test1();
        assertEquals(1, test.slice(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 1));
        assertEquals(5, test.slice(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 2));
        assertEquals(8, test.slice(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 3));
        assertEquals(10, test.slice(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 4));
        assertEquals(13, test.slice(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 5));
        assertEquals(17, test.slice(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 6));
        assertEquals(18, test.slice(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 7));
        assertEquals(22, test.slice(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 8));
        assertEquals(25, test.slice(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 9));
        assertEquals(30, test.slice(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 10));
//        assertArrayEquals(new int[]{0, 1, 2, 3, 2, 2, 6, 1, 2, 3, 10}, test.slice(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 10));
    }
}

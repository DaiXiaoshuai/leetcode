package problem5270;

import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {
    public int subtractProductAndSum(int n) {
        int sum = 0;
        int ji = 1;
        while(n >= 1){
            int tmp = n%10;
            sum += tmp;
            ji *= tmp;
            n = n/10;
        }
        return  ji - sum;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        assertEquals(15, solution.subtractProductAndSum(234));
        assertEquals(21, solution.subtractProductAndSum(4421));
        assertEquals(0, solution.subtractProductAndSum(1));
    }
}

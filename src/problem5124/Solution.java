package problem5124;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1;i<9;i++){
            int result = i;
            while (result <= high){
                if(result >= low)   list.add(result);
                int tmp = result % 10;
                if(tmp == 9) break;
                result = result*10 + tmp + 1;
            }
        }
        Collections.sort(list);
        return list;
    }
}

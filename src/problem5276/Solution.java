package problem5276;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        if(tomatoSlices %2 == 1) return new ArrayList<>(0);
        if(tomatoSlices < 2* cheeseSlices) return new ArrayList<>(0);
        if((tomatoSlices - 2*cheeseSlices) % 2 == 1) return new ArrayList<>(0);
        int x = (tomatoSlices - 2*cheeseSlices) / 2;
        int y = cheeseSlices - x;
        ArrayList<Integer> result = new ArrayList<>(2);
        if(x >=0 && y>=0) {
            result.add(x);
            result.add(y);
            return result;
        }
        return result;
    }
}

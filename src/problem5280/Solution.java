package problem5280;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        List<List<Integer>> result = new LinkedList<>();
        for(int i=0;i<groupSizes.length;i++){
            if(map.containsKey(groupSizes[i])){
                ArrayList<Integer> list = map.get(groupSizes[i]);
                list.add(i);
                if(list.size() == groupSizes[i]){
                    result.add(map.remove(groupSizes[i]));
                }
            }else{
                ArrayList<Integer> list = new ArrayList<>(groupSizes[i]);
                list.add(i);
                if(list.size() == groupSizes[i]){
                    result.add(list);
                }else{
                    map.put(groupSizes[i], list);
                }
            }
        }
        return result;
    }
}

package problem5273;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>(searchWord.length());
        List<String> preResult = new ArrayList<>(products.length);
        Arrays.sort(products);
        preResult.addAll(Arrays.asList(products));

        for(int i=0;i<searchWord.length();i++){
            List<String> pre = new ArrayList<String>();
            ArrayList<String> strings = new ArrayList<>(3);

            for(String item : preResult){
                if(item.startsWith(searchWord.substring(0, i+1))){
                    pre.add(item);
                    if(strings.size()<3){
                        strings.add(item);
                    }
                }
            }
            result.add(strings);
            preResult = pre;

        }
        return result;
    }

}

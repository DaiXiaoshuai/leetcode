package problem17;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) return new ArrayList<String>();
        String[] map = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        int size = map[digits.charAt(0)-'0'].length();
        for(int i=1;i<digits.length();i++){
            size = size*map[digits.charAt(i) - '0'].length();
        }
        StringBuilder[] result = new StringBuilder[size];
        int groupSize = size;
        for(int i=0;i<digits.length();i++){
            String target = map[digits.charAt(i) - '0'];
            groupSize = groupSize/target.length();
            for(int j = 0;j<size;j++){
                if(result[j] == null) result[j] = new StringBuilder();
                result[j].append(target.charAt(j/groupSize%target.length()));
            }
        }
        ArrayList<String> strings = new ArrayList<>(size);
        for(StringBuilder sb :result){
               strings.add(sb.toString());
        }
        return strings;
    }
}
package jyrmt;

import java.util.ArrayList;
import java.util.List;

public class Problem1 {
    public static void main(String[] args) {
        Problem1 problem1 = new Problem1();
        problem1.printGroup("abc");
    }



    String fishingOrResting(int year, int month, int day){
        int dayCount = 0;
        // 计算传入日期与1990年1月1日的天数
        // 计算之前年份的总天数
        for(int i=1990;i<year;i++){
            if(isLeapYear(i)) dayCount += 366;
            else dayCount += 365;
        }
        //计算当年 当月前的天数
        int[] monthDays = new int[]{31,isLeapYear(year)?29:28,31,30,31,30,31,31,30,31,30,31}; //当前年份每月天数
        for(int i=1; i<month;i++){
            dayCount += monthDays[i -1];
        }
        // 计算当月天数
        dayCount += day;

        int result = dayCount % 5 ;
        if(result == 1 || result == 2 || result == 3) return "打渔";
        else return "晒网";
    }

    /**
     * 是否闰年
     * @param year
     * @return
     */
    boolean isLeapYear(int year){
        if((year % 4 == 0 && year % 100 !=0) || year % 400 ==0){
            return true;
        }
        return false;
    }

    void generateNum(){
        for(int i=1;i<=9;i++){ //千位
            for(int j=0;j<=9;j++){ //百位
                if((i == 1 && j== 2) || (i==2 && j==1) || j ==4 ) continue;
                else{
                    for( int k=0; k<=9;k++){ //十位
                        if((k == 1 && j== 2) || (k==2 && j==1)) continue;
                        else{
                            for(int m = 0; m<=9; m++){ //个位
                                if((k == 1 && m== 2) || (k==2 && m==1)) continue;
                                else{
                                    System.out.println(i * 1000 + j * 100 + k *10 +m);
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    void printGroup(String str){
        // 思路：动态规划，逐个加入字符  result(i+1) = 将新字符插入result(i)中每个子项（item）的每个位置，即插入下标为 0 到 len(item)
        ArrayList<StringBuilder> result = new ArrayList<StringBuilder>();
        for(int i=0;i<str.length();i++){ // 把字符串中的字符逐个加入到结果集中运算生成新的结果集
            result = compose(result, str.charAt(i));
        }
        //打印输出
        for(StringBuilder item : result){
            System.out.println(item.toString());
        }

    }

    /**
     * 将新字符插入结果集中每个子项的每个位置，并生成新的结果集返回
     * @param list
     * @param c
     * @return
     */
    ArrayList<StringBuilder> compose(ArrayList<StringBuilder> list, char c){
        if(list.size() == 0){ // 第一次，结果集为空时，直接加入
            list.add(new StringBuilder(c + ""));
            return list;
        }else{
            ArrayList<StringBuilder> resultList = new ArrayList<>();
            for(StringBuilder item : list ){
                // 在每个结果的每个可插入位置插入新的字符，加入新的结果集
                for(int index = 0;index<=item.length();index ++){
                    StringBuilder result = new StringBuilder(item);
                    result.insert(index, c);
                    resultList.add(result);
                }
            }
            return resultList;
        }
    }
}

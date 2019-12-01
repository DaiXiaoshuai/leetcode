package problem5275;

public class Solution {
    public String tictactoe(int[][] moves) {
        int[][] map = new int[3][3];
        int flag = 1;
        for(int[] item : moves){
            map[item[0]][item[1]] = flag;
            flag = -flag;
        }
        for(int i=0;i<3;i++){
            int sum1 = 0;
            int sum2 = 0;
            for(int j=0;j<3;j++){
                sum1 += map[i][j];
                sum2 += map[j][i];
            }
            if(sum1 == 3) return "A";
            if(sum2 == 3) return "A";

            if(sum1 == -3) return "B";
            if(sum2 == -3) return "B";
        }
        int sum = map[0][0] + map[1][1] + map[2][2];
        if(sum == 3) return "A";
        if(sum == -3) return "B";
        sum = 0;
        sum = map[0][2] + map[1][1] + map[2][0];
        if(sum == 3) return "A";
        if(sum == -3) return "B";
        if(moves.length == 9) return "Draw";
        return "Pending";
    }
}

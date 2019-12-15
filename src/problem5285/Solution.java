package problem5285;

public class Solution {
    int[][] mat;
    int threshold;
    public int maxSideLength(int[][] mat, int threshold) {
        this.mat = mat;
        this.threshold = threshold;
        int max = 0;
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                int tmp = checkSum(i, j);
                if(tmp > max) max = tmp;
            }
        }
        return max;
    }

    int checkSum(int rowIndex, int colIndex){
        int result = 0;
        while (true){
            if(rowIndex + result >= mat.length) return result;
            if(colIndex + result >= mat[rowIndex].length) return result;
            int sum = 0;
            for(int i=0;i<=result;i++){
                for(int j=0;j<=result;j++){
                    sum += mat[rowIndex+i][colIndex+j];
                }
            }
            if(sum > threshold) return result;
            else result++;
        }
    }
}

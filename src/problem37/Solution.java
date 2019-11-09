package problem37;

import org.junit.jupiter.api.Test;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 *
 *
 * 一个数独。
 *
 *
 *
 * 答案被标成红色。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public void solveSudoku(char[][] board) {
        int [][] board2 = new int[9][9];
        int count = 0;
        for(int i=0;i<9;i++){
            for(int j=0;j < 9;j++){
                char ch = board[i][j];
                if(ch != '.') board2[i][j] = ch - '0';
                else count++;
            }
        }
        int[] row = new int[count]; // 待填位置的行标
        int[] column = new int[count]; // 列标
        count = 0;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                char ch = board[i][j];
                if(ch == '.') {
                    row[count] = i;
                    column[count] = j;
                    count++;
                }
            }
        }

        for(int i=0;i<count;){
            if(board2[row[i]][column[i]]>=9) {
                board2[row[i]][column[i]] = 0;
                i--;
                continue;
            }
            boolean flag = false;
            for(int j = board2[row[i]][column[i]]+1;j<=9;j++){
                board2[row[i]][column[i]] = j;
                if(isValid(board2, row[i], column[i])){
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                board2[row[i]][column[i]] = 0;
                i--;
            }else{
                i++;
            }

        }
        for(int i=0;i<row.length;i++){
            board[row[i]][column[i]] = (char)('0' + board2[row[i]][column[i]]);
        }
    }

    public boolean isValid(int[][] board, int rowNum, int columnNum){
        if(board[rowNum][columnNum] == 0) return true;
        for(int i=0;i<9;i++){
            if(board[rowNum][i] == board[rowNum][columnNum] && i != columnNum) return false;
            if(board[i][columnNum] == board[rowNum][columnNum] && i != rowNum) return false;
        }
        int x = columnNum /3 * 3;
        int y = rowNum /3 * 3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(y+i == rowNum && x+j == columnNum) continue;
                if(board[y+i][x+j] == board[rowNum][columnNum]) return false;
            }
        }
        return true;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        char[][] testCase = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        solution.solveSudoku(testCase);
        System.out.println(12);
    }
}

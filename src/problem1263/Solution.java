package problem1263;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 关键词：单源最短路径、节点是否连通
 *
 * 推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。
 *
 * 游戏地图用大小为 n * m 的网格 grid 表示，其中每个元素可以是墙、地板或者是箱子。
 *
 * 现在你将作为玩家参与游戏，按规则将箱子 'B' 移动到目标位置 'T' ：
 *
 * 玩家用字符 'S' 表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。
 * 地板用字符 '.' 表示，意味着可以自由行走。
 * 墙用字符 '#' 表示，意味着障碍物，不能通行。 
 * 箱子仅有一个，用字符 'B' 表示。相应地，网格上有一个目标位置 'T'。
 * 玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。
 * 玩家无法越过箱子。
 * 返回将箱子推到目标位置的最小 推动 次数，如果无法做到，请返回 -1。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [["#","#","#","#","#","#"],
 *              ["#","T","#","#","#","#"],
 *              ["#",".",".","B",".","#"],
 *              ["#",".","#","#",".","#"],
 *              ["#",".",".",".","S","#"],
 *              ["#","#","#","#","#","#"]]
 * 输出：3
 * 解释：我们只需要返回推箱子的次数。
 * 示例 2：
 *
 * 输入：grid = [["#","#","#","#","#","#"],
 *              ["#","T","#","#","#","#"],
 *              ["#",".",".","B",".","#"],
 *              ["#","#","#","#",".","#"],
 *              ["#",".",".",".","S","#"],
 *              ["#","#","#","#","#","#"]]
 * 输出：-1
 * 示例 3：
 *
 * 输入：grid = [["#","#","#","#","#","#"],
 *              ["#","T",".",".","#","#"],
 *              ["#",".","#","B",".","#"],
 *              ["#",".",".",".",".","#"],
 *              ["#",".",".",".","S","#"],
 *              ["#","#","#","#","#","#"]]
 * 输出：5
 * 解释：向下、向左、向左、向上再向上。
 * 示例 4：
 *
 * 输入：grid = [["#","#","#","#","#","#","#"],
 *              ["#","S","#",".","B","T","#"],
 *              ["#","#","#","#","#","#","#"]]
 * 输出：-1
 *  
 *
 * 提示：
 *
 * 1 <= grid.length <= 20
 * 1 <= grid[i].length <= 20
 * grid 仅包含字符 '.', '#',  'S' , 'T', 以及 'B'。
 * grid 中 'S', 'B' 和 'T' 各只能出现一个。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-move-a-box-to-their-target-location
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public int minPushBox(char[][] grid) {
        return 0;
    }

    /**
     * 生成邻接矩阵形式表示的图
     * 0-1权重
     */
    int[][] generateMatrix(char[][] grid){
        // 符号 # 代表墙壁
        int[][] map = new int[grid.length*grid[0].length][grid.length*grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j] != '#' ){
                    if(i>0){
                        if(grid[i-1][j] != '#')
                            map[i*grid[i].length+j][(i-1)*grid[i-1].length+j] = 1;
                    }
                    if(j<grid[i].length-1){
                        if(grid[i][j+1] != '#')
                            map[i*grid[i].length + j][i*grid[i].length + j + 1] = 1;
                    }
                    if(i<grid.length-1){
                        if(grid[i+1][j] != '#'){
                            map[i*grid[i].length + j][grid[i+1].length * (i+1) +j] = 1;
                        }
                    }
                    if(j>0){
                        if(grid[i][j-1] !='#'){
                            map[i*grid[i].length + j][i*grid[i].length + j - 1] = 1;
                        }
                    }
                }
            }
        }
        return map;
    }

    /**
     * bfs 验证图中两点是否连通
     */
    boolean isAccessibleByBFS(int[][] map, int start, int target){
        int[] flag = new int[map.length]; // 标记矩阵，用于在遍历过程中记录是否被访问过, 1 为被访问过
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        while (!queue.isEmpty()  ){
            int item = queue.poll();
            flag[item] = 1;
            if(item == target) return true;
            for(int i=0;i<map[item].length;i++){
                if(map[item][i] == 1 && flag[i] == 0){
                    queue.add(i);
                    flag[i] = 1;
                }
            }
        }
        return false;
    }


    /**
     * 生成二维数组式邻接矩阵形式的图
     * 【上】【右】【下】【左 的点坐标】
     * 【】【】【】【】
     * 【】【】【】【】
     * @param grid
     * @return
     */
    public int[][] generateMap(char[][] grid){
        int[][] map = new int[grid.length * grid[0].length][4];
        for(int i = 0;i <grid.length;i++ ){
            for(int j=0;j < grid[0].length;j++){
                if(grid[i][j] == '#') {
                    continue;
                }
                if(grid[i][j] == '.' || grid[i][j] == 'S' || grid[i][j] == 'B'||  grid[i][j] == 'T'){
                    if(i>0){
                        if(grid[i-1][j] !='#') map[i*grid[0].length][0] = 1;
                    }
                    if(j!=grid[0].length-1){
                        if(grid[i][j+1] != '#') map[i*grid[0].length][1] = 1;
                    }
                    if(i!=grid.length-1){
                        if(grid[i+1][j] != '#') map[i*grid[0].length][2] = 1;
                    }
                    if(i>0){
                        if(grid[i-1][j] != '#') map[i*grid[0].length][3] = 1;
                    }
                }
            }
        }
        return map;
    }

    @Test
    void test(){
        Solution solution = new Solution();
        int[][] map = solution.generateMatrix(new char[][]{
                {'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '#', '#', '#', '#'},
                {'#', '.', '.', 'B', '.', '#'},
                {'#', '.', '#', '#', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}
        });

        assertEquals(true, solution.isAccessibleByBFS(map,14,16));
        assertEquals(false, solution.isAccessibleByBFS(map,14,17));
        assertEquals(false, solution.isAccessibleByBFS(map,14,12));
        assertEquals(true, solution.isAccessibleByBFS(map,14,26));
        assertEquals(true, solution.isAccessibleByBFS(map,14,27));
        assertEquals(true, solution.isAccessibleByBFS(map,14,28));
        assertEquals(false, solution.isAccessibleByBFS(map,14,34));
        assertEquals(false, solution.isAccessibleByBFS(map,14,35));
        assertEquals(false, solution.isAccessibleByBFS(map,14,33));

        assertEquals(false, solution.isAccessibleByBFS(map,0,33));
        assertEquals(false, solution.isAccessibleByBFS(map,0,33));

        assertEquals(false, solution.isAccessibleByBFS(map,14,8));
        assertEquals(true, solution.isAccessibleByBFS(map,14,15));
        assertEquals(false, solution.isAccessibleByBFS(map,14,20));
        assertEquals(true, solution.isAccessibleByBFS(map,14,13));

        assertEquals(false, solution.isAccessibleByBFS(map,30,24));
        assertEquals(false, solution.isAccessibleByBFS(map,30,31));

        assertEquals(false, solution.isAccessibleByBFS(map,34,28));
        assertEquals(false, solution.isAccessibleByBFS(map,34,35));
        assertEquals(false, solution.isAccessibleByBFS(map,34,33));

        assertEquals(false, solution.isAccessibleByBFS(map,35,29));
        assertEquals(false, solution.isAccessibleByBFS(map,35,34));

        assertEquals(false, solution.isAccessibleByBFS(map,0,1));
        assertEquals(false, solution.isAccessibleByBFS(map,0,6));

        assertEquals(false, solution.isAccessibleByBFS(map,1,0));
        assertEquals(false, solution.isAccessibleByBFS(map,1,3));
        assertEquals(false, solution.isAccessibleByBFS(map,1,7));

        assertEquals(false, solution.isAccessibleByBFS(map,4,3));
        assertEquals(false, solution.isAccessibleByBFS(map,4,6));
        assertEquals(false, solution.isAccessibleByBFS(map,4,10));

        assertEquals(false, solution.isAccessibleByBFS(map,5,4));
        assertEquals(false, solution.isAccessibleByBFS(map,5,11));

        assertEquals(false, solution.isAccessibleByBFS(map,6,0));
        assertEquals(false, solution.isAccessibleByBFS(map,6,7));
        assertEquals(false, solution.isAccessibleByBFS(map,6,12));

        assertEquals(false, solution.isAccessibleByBFS(map,7,1));
        assertEquals(false, solution.isAccessibleByBFS(map,7,8));
        assertEquals(true, solution.isAccessibleByBFS(map,7,13));
        assertEquals(false, solution.isAccessibleByBFS(map,7,6));



    }
}

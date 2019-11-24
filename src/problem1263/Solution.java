package problem1263;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 关键词：单源最短路径、节点是否连通
 * <p>
 * 推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。
 * <p>
 * 游戏地图用大小为 n * m 的网格 grid 表示，其中每个元素可以是墙、地板或者是箱子。
 * <p>
 * 现在你将作为玩家参与游戏，按规则将箱子 'B' 移动到目标位置 'T' ：
 * <p>
 * 玩家用字符 'S' 表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。
 * 地板用字符 '.' 表示，意味着可以自由行走。
 * 墙用字符 '#' 表示，意味着障碍物，不能通行。 
 * 箱子仅有一个，用字符 'B' 表示。相应地，网格上有一个目标位置 'T'。
 * 玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。
 * 玩家无法越过箱子。
 * 返回将箱子推到目标位置的最小 推动 次数，如果无法做到，请返回 -1。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [["#","#","#","#","#","#"],
 * ["#","T","#","#","#","#"],
 *              ["#",".",".","B",".","#"],
 *              ["#",".","#","#",".","#"],
 *              ["#",".",".",".","S","#"],
 *              ["#","#","#","#","#","#"]]
 * 输出：3
 * 解释：我们只需要返回推箱子的次数。
 * 示例 2：
 * <p>
 * 输入：grid = [["#","#","#","#","#","#"],
 * ["#","T","#","#","#","#"],
 *              ["#",".",".","B",".","#"],
 *              ["#","#","#","#",".","#"],
 *              ["#",".",".",".","S","#"],
 *              ["#","#","#","#","#","#"]]
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：grid = [["#","#","#","#","#","#"],
 *              ["#","T",".",".","#","#"],
 *              ["#",".","#","B",".","#"],
 *              ["#",".",".",".",".","#"],
 *              ["#",".",".",".","S","#"],
 *              ["#","#","#","#","#","#"]]
 * 输出：5
 * 解释：向下、向左、向左、向上再向上。
 * 示例 4：
 * <p>
 * 输入：grid = [["#","#","#","#","#","#","#"],
 *              ["#","S","#",".","B","T","#"],
 *              ["#","#","#","#","#","#","#"]]
 * 输出：-1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= grid.length <= 20
 * 1 <= grid[i].length <= 20
 * grid 仅包含字符 '.', '#',  'S' , 'T', 以及 'B'。
 * grid 中 'S', 'B' 和 'T' 各只能出现一个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-move-a-box-to-their-target-location
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    static class BoxPositionDesc {
        BoxPositionDesc() {
        }


        int boxRow = 0;
        int boxCol = 0;

        int playerRow = 0;
        int playerCol = 0;
        char[][] grid;

        BoxPositionDesc(char[][] grid, int boxRow, int boxCol) {
            this.grid = grid;
            this.boxCol = boxCol;
            this.boxRow = boxRow;
        }

        BoxPositionDesc(char[][] grid, int boxRow, int boxCol, int playerRow, int playerCol) {
            this.grid = grid;
            this.boxCol = boxCol;
            this.boxRow = boxRow;
            this.playerRow = playerRow;
            this.playerCol = playerCol;
        }

        int getBoxPosition() {
            return boxRow * this.grid[0].length + boxCol;
        }

    }

    char[][] grid;

    public int minPushBox(char[][] grid) {
        this.grid = grid;
        int[] flag = new int[grid.length * grid[0].length];// 标记是否被访问过
        int playerPosition = 0;
        BoxPositionDesc box = null;
        int targetPosition = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'S') playerPosition = i * grid[0].length + j;
                if (grid[i][j] == 'B') {
                    box = new BoxPositionDesc(grid, i, j);
                }
                if (grid[i][j] == 'T') targetPosition = i * grid[0].length + j;
            }
        }
        box.playerCol = playerPosition % grid[0].length;
        box.playerRow = playerPosition / grid[0].length;

        Queue<BoxPositionDesc> queue = new LinkedList<>();
        queue.add(box);
        flag[box.boxRow * grid[0].length + box.boxCol] = 1;
        int count = 0;
        while (!queue.isEmpty()) {
            Queue<BoxPositionDesc> tmp = new LinkedList<>();
            while (!queue.isEmpty()) {
                BoxPositionDesc currPos = queue.poll();
                if (currPos.getBoxPosition() == targetPosition) return count;
                //向上推
                if (currPos.boxRow > 0) {
                    // 上方为空
                    if (grid[currPos.boxRow - 1][currPos.boxCol] != '#') {
                        int[] visited = new int[grid.length * grid[0].length];
                        // 小人可到达推箱子位置
                        if (isAccessible(grid, currPos.boxRow, currPos.boxCol, currPos.playerRow, currPos.playerCol, currPos.boxRow + 1, currPos.boxCol, visited)) {
                            if (flag[(currPos.boxRow - 1) * grid[0].length + currPos.boxCol] == 0) {
                                tmp.add(new BoxPositionDesc(grid, currPos.boxRow - 1, currPos.boxCol, currPos.boxRow, currPos.boxCol));
                            }
                        }
                    }
                }
                //向下推
                if (currPos.boxRow < grid.length - 1) {
                    // 下方为空
                    if (grid[currPos.boxRow + 1][currPos.boxCol] != '#') {
                        int[] visited = new int[grid.length * grid[0].length];
                        // 小人可到达推箱子位置
                        if (isAccessible(grid, currPos.boxRow, currPos.boxCol, currPos.playerRow, currPos.playerCol, currPos.boxRow - 1, currPos.boxCol, visited)) {
                            if (flag[(currPos.boxRow + 1) * grid[0].length + currPos.boxCol] == 0) {
                                tmp.add(new BoxPositionDesc(grid, currPos.boxRow + 1, currPos.boxCol, currPos.boxRow, currPos.boxCol));
                            }
                        }
                    }
                }
                //向左推
                if (currPos.boxCol > 0) {
                    // 左方为空
                    if (grid[currPos.boxRow][currPos.boxCol - 1] != '#') {
                        int[] visited = new int[grid.length * grid[0].length];
                        // 小人可到达推箱子位置
                        if (isAccessible(grid, currPos.boxRow, currPos.boxCol, currPos.playerRow, currPos.playerCol, currPos.boxRow, currPos.boxCol + 1, visited)) {
                            if (flag[(currPos.boxRow) * grid[0].length + currPos.boxCol -1 ] == 0) {
                                tmp.add(new BoxPositionDesc(grid, currPos.boxRow, currPos.boxCol - 1, currPos.boxRow, currPos.boxCol));
                            }
                        }
                    }
                }
                //向右推
                if (currPos.boxCol < grid[0].length - 1) {
                    // 上方为空
                    if (grid[currPos.boxRow][currPos.boxCol + 1] != '#') {
                        int[] visited = new int[grid.length * grid[0].length];
                        // 小人可到达推箱子位置
                        if (isAccessible(grid, currPos.boxRow, currPos.boxCol, currPos.playerRow, currPos.playerCol, currPos.boxRow, currPos.boxCol - 1, visited)) {
                            if (flag[(currPos.boxRow ) * grid[0].length + currPos.boxCol + 1] == 0) {
                                tmp.add(new BoxPositionDesc(grid, currPos.boxRow, currPos.boxCol + 1, currPos.boxRow, currPos.boxCol));
                            }
                        }
                    }
                }
                flag[currPos.boxRow * grid[0].length + currPos.boxCol] = 1;
            }
            queue.addAll(tmp);
            count++;

        }
        return -1;
    }

    boolean isValid(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) return false;
        if (grid[row][col] != '#') return true;
        return false;
    }

    /**
     * 生成邻接矩阵形式表示的图
     * 0-1权重
     */
    int[][] generateMatrix(char[][] grid) {
        // 符号 # 代表墙壁
        int[][] map = new int[grid.length * grid[0].length][grid.length * grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != '#') {
                    if (i > 0) {
                        if (grid[i - 1][j] != '#')
                            map[i * grid[i].length + j][(i - 1) * grid[i - 1].length + j] = 1;
                    }
                    if (j < grid[i].length - 1) {
                        if (grid[i][j + 1] != '#')
                            map[i * grid[i].length + j][i * grid[i].length + j + 1] = 1;
                    }
                    if (i < grid.length - 1) {
                        if (grid[i + 1][j] != '#') {
                            map[i * grid[i].length + j][grid[i + 1].length * (i + 1) + j] = 1;
                        }
                    }
                    if (j > 0) {
                        if (grid[i][j - 1] != '#') {
                            map[i * grid[i].length + j][i * grid[i].length + j - 1] = 1;
                        }
                    }
                }
            }
        }
        return map;
    }

    boolean isAccessible(char[][] grid, int boxRow, int boxCol, int playerRow, int playerCol, int targetRow, int targetCol, int[] visited) {
        if(grid[playerRow][playerCol] == '#') return false;
        if (playerCol == targetCol && playerRow == targetRow) return true;

        grid[boxRow][boxCol] = '#';
        if (targetCol < 0 || targetCol >= grid[0].length || targetRow < 0 || targetRow >= grid.length) return false;
        visited[playerRow * grid[0].length + playerCol] = 1;
        boolean up = false;
        if (playerRow > 0 && grid[playerRow - 1][playerCol] != '#' && visited[(playerRow - 1) * grid[0].length + playerCol] == 0)
            up = isAccessible(grid, boxRow, boxCol, playerRow - 1, playerCol, targetRow, targetCol, visited);
        if (up) {
            grid[boxRow][boxCol] = '.';
            return true;
        }
        if (playerRow < grid.length - 1 && grid[playerRow + 1][playerCol] != '#' && visited[(playerRow + 1) * grid[0].length + playerCol] == 0)
            up = isAccessible(grid, boxRow, boxCol, playerRow + 1, playerCol, targetRow, targetCol, visited);
        if (up) {
            grid[boxRow][boxCol] = '.';
            return true;
        }
        if (playerCol > 0 && grid[playerRow][playerCol - 1] != '#' && visited[(playerRow) * grid[0].length + playerCol - 1] == 0)
            up = isAccessible(grid, boxRow, boxCol, playerRow, playerCol - 1, targetRow, targetCol, visited);
        if (up) {
            grid[boxRow][boxCol] = '.';
            return true;
        }
        if (playerCol < grid[0].length - 1 && grid[playerRow][playerCol + 1] != '#' && visited[(playerRow) * grid[0].length + playerCol + 1] == 0)
            up = isAccessible(grid, boxRow, boxCol, playerRow, playerCol + 1, targetRow, targetCol, visited);
        if (up) {
            grid[boxRow][boxCol] = '.';
            return true;
        }
        grid[boxRow][boxCol] = '.';
        return false;
    }

    /**
     * bfs 验证图中两点是否连通
     */
    boolean isAccessibleByBFS(int[][] map, int start, int target) {
        int[] flag = new int[map.length]; // 标记矩阵，用于在遍历过程中记录是否被访问过, 1 为被访问过
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int item = queue.poll();
            flag[item] = 1;
            if (item == target) return true;
            for (int i = 0; i < map[item].length; i++) {
                if (map[item][i] == 1 && flag[i] == 0) {
                    queue.add(i);
                    flag[i] = 1;
                }
            }
        }
        return false;
    }

    static class DijkstraDesc {
        public DijkstraDesc() {
        }

        public DijkstraDesc(int pos, int distance) {
            this.pos = pos;
            this.distance = distance;
        }

        public int pos;// 点在邻接矩阵中的坐标
        public int distance; // 该点离已知路径点中的最短距离
    }

    /**
     * 单源最短路径
     *
     * @param map   邻接矩阵
     * @param start 源节点的坐标
     * @return 数组形式表示的各点离源节点的最短路径
     */
    public int[] dijkstraShortestPath(int[][] map, int start) {
        LinkedList<DijkstraDesc> solvedPosition = new LinkedList<>(); // 已求解成功的点集合
        solvedPosition.add(new DijkstraDesc(start, 0));
        Deque<DijkstraDesc> unsolvedPosition = new LinkedList<>();// 待求解的点集合
        //初始化节点集合， 无法直接连通的点最短路径标位Integer.MAX_VALUE
        for (int i = 0; i < map.length; i++) {
            if (i != start) {
                // 下一轮加入的点放在队列首位
                if (unsolvedPosition.peekFirst() == null || map[start][i] < unsolvedPosition.peekFirst().distance) {
                    if (map[start][i] == 0) { // 0代表不连通
                        unsolvedPosition.addLast(new DijkstraDesc(i, Integer.MAX_VALUE));
                    } else
                        unsolvedPosition.addFirst(new DijkstraDesc(i, map[start][i]));
                } else {
                    unsolvedPosition.addLast(new DijkstraDesc(i, map[start][i]));
                }
            }
        }
        while (!unsolvedPosition.isEmpty()) {
            DijkstraDesc addedItem = unsolvedPosition.peek();
            if (addedItem.distance == Integer.MAX_VALUE) break; // 说明此节点开始无法连通至源节点
            addedItem = unsolvedPosition.pollFirst();
            solvedPosition.add(addedItem);
            LinkedList<DijkstraDesc> tmp = new LinkedList<>();
            while (!unsolvedPosition.isEmpty()) {
                DijkstraDesc item = unsolvedPosition.poll();
                if (map[addedItem.pos][item.pos] != 0) { // 连通的情况下才做比较
                    item.distance = Math.min(map[addedItem.pos][item.pos] + addedItem.distance, item.distance);
                }
                if (tmp.isEmpty() || item.distance < tmp.peekFirst().distance) {
                    tmp.addFirst(item);
                } else {
                    tmp.addLast(item);
                }
            }
            unsolvedPosition = tmp;
        }
        int[] result = new int[map.length];
        for (DijkstraDesc item : solvedPosition) {
            result[item.pos] = item.distance;
        }
        for (DijkstraDesc item : unsolvedPosition) {
            result[item.pos] = item.distance;
        }
        return result;
    }


    /**
     * 生成二维数组式邻接矩阵形式的图
     * 【上】【右】【下】【左 的点坐标】
     * 【】【】【】【】
     * 【】【】【】【】
     *
     * @param grid
     * @return
     */
    public int[][] generateMap(char[][] grid) {
        int[][] map = new int[grid.length * grid[0].length][4];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '#') {
                    continue;
                }
                if (grid[i][j] == '.' || grid[i][j] == 'S' || grid[i][j] == 'B' || grid[i][j] == 'T') {
                    if (i > 0) {
                        if (grid[i - 1][j] != '#') map[i * grid[0].length][0] = 1;
                    }
                    if (j != grid[0].length - 1) {
                        if (grid[i][j + 1] != '#') map[i * grid[0].length][1] = 1;
                    }
                    if (i != grid.length - 1) {
                        if (grid[i + 1][j] != '#') map[i * grid[0].length][2] = 1;
                    }
                    if (i > 0) {
                        if (grid[i - 1][j] != '#') map[i * grid[0].length][3] = 1;
                    }
                }
            }
        }
        return map;
    }

    @Test
    void test() {
        Solution solution = new Solution();
        int[][] map = solution.generateMatrix(new char[][]{
                {'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '#', '#', '#', '#'},
                {'#', '.', '.', 'B', '.', '#'},
                {'#', '.', '#', '#', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}
        });

        assertEquals(true, solution.isAccessibleByBFS(map, 14, 16));
        assertEquals(false, solution.isAccessibleByBFS(map, 14, 17));
        assertEquals(false, solution.isAccessibleByBFS(map, 14, 12));
        assertEquals(true, solution.isAccessibleByBFS(map, 14, 26));
        assertEquals(true, solution.isAccessibleByBFS(map, 14, 27));
        assertEquals(true, solution.isAccessibleByBFS(map, 14, 28));
        assertEquals(false, solution.isAccessibleByBFS(map, 14, 34));
        assertEquals(false, solution.isAccessibleByBFS(map, 14, 35));
        assertEquals(false, solution.isAccessibleByBFS(map, 14, 33));

        assertEquals(false, solution.isAccessibleByBFS(map, 0, 33));
        assertEquals(false, solution.isAccessibleByBFS(map, 0, 33));

        assertEquals(false, solution.isAccessibleByBFS(map, 14, 8));
        assertEquals(true, solution.isAccessibleByBFS(map, 14, 15));
        assertEquals(false, solution.isAccessibleByBFS(map, 14, 20));
        assertEquals(true, solution.isAccessibleByBFS(map, 14, 13));

        assertEquals(false, solution.isAccessibleByBFS(map, 30, 24));
        assertEquals(false, solution.isAccessibleByBFS(map, 30, 31));

        assertEquals(false, solution.isAccessibleByBFS(map, 34, 28));
        assertEquals(false, solution.isAccessibleByBFS(map, 34, 35));
        assertEquals(false, solution.isAccessibleByBFS(map, 34, 33));

        assertEquals(false, solution.isAccessibleByBFS(map, 35, 29));
        assertEquals(false, solution.isAccessibleByBFS(map, 35, 34));

        assertEquals(false, solution.isAccessibleByBFS(map, 0, 1));
        assertEquals(false, solution.isAccessibleByBFS(map, 0, 6));

        assertEquals(false, solution.isAccessibleByBFS(map, 1, 0));
        assertEquals(false, solution.isAccessibleByBFS(map, 1, 3));
        assertEquals(false, solution.isAccessibleByBFS(map, 1, 7));

        assertEquals(false, solution.isAccessibleByBFS(map, 4, 3));
        assertEquals(false, solution.isAccessibleByBFS(map, 4, 6));
        assertEquals(false, solution.isAccessibleByBFS(map, 4, 10));

        assertEquals(false, solution.isAccessibleByBFS(map, 5, 4));
        assertEquals(false, solution.isAccessibleByBFS(map, 5, 11));

        assertEquals(false, solution.isAccessibleByBFS(map, 6, 0));
        assertEquals(false, solution.isAccessibleByBFS(map, 6, 7));
        assertEquals(false, solution.isAccessibleByBFS(map, 6, 12));

        assertEquals(false, solution.isAccessibleByBFS(map, 7, 1));
        assertEquals(false, solution.isAccessibleByBFS(map, 7, 8));
        assertEquals(true, solution.isAccessibleByBFS(map, 7, 13));
        assertEquals(false, solution.isAccessibleByBFS(map, 7, 6));


    }

    @Test
    void testDijk() {
        Solution solution = new Solution();
        int[][] map = solution.generateMatrix(new char[][]{
                {'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '#', '#', '#', '#'},
                {'#', '.', '.', 'B', '.', '#'},
                {'#', '.', '#', '#', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}
        });
        assertArrayEquals(new int[]{
                Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                Integer.MAX_VALUE, 1, 2, 3, 4, Integer.MAX_VALUE,
                Integer.MAX_VALUE, 2, Integer.MAX_VALUE, Integer.MAX_VALUE, 5, Integer.MAX_VALUE,
                Integer.MAX_VALUE, 3, 4, 5, 6, Integer.MAX_VALUE,
                Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
        }, solution.dijkstraShortestPath(map, 7));

        assertArrayEquals(new int[]{
                Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE
        }, solution.dijkstraShortestPath(map, 6));

    }

    @Test
    void testIsAcess(){
        Solution solution = new Solution();

        char[][] map = new char[][]{
                {'#','.','.','#','T','#','#','#','#'},
                {'#','.','.','#','.','#','.','.','#'},
                {'#','.','.','#','.','#','.','.','#'},
                {'#','.','.','.','.','.','B','.','#'},
                {'#','.','.','.','.','#','.','.','#'},
                {'#','.','.','#','.','#','#','#','#'}};
         int[] visited = new int[map.length * map[0].length];
        assertEquals(false, solution.isAccessible(map,3,6,2,6,3,5,visited));
        assertEquals(true, solution.isAccessible(map,3,6,2,6,3,7,visited));
    }


    @Test
    void testMinPush() {
        Solution solution = new Solution();

        char[][] map1 = new char[][]{
                {'#','.','.','#','T','#','#','#','#'},
                {'#','.','.','#','.','#','.','.','#'},
                {'#','.','.','#','.','#','B','.','#'},
                {'#','.','.','.','.','.','.','.','#'},
                {'#','.','.','.','.','#','.','S','#'},
                {'#','.','.','#','.','#','#','#','#'}};
        assertEquals(8, solution.minPushBox(map1));

        char[][] map = new char[][]{
                {'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '#', '#', '#', '#'},
                {'#', '.', '.', 'B', '.', '#'},
                {'#', '.', '#', '#', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}
        };
        assertEquals(3, solution.minPushBox(map));
        map = new char[][]{
                {'.', '.', '#', '.', '.', '.', '.', '#'},
                {'.', 'B', '.', '.', '.', '.', '.', '#'},
                {'.', '.', 'S', '.', '.', '.', '.', '.'},
                {'.', '#', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'T', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '#'},
                {'.', '#', '.', '.', '.', '.', '.', '.'}};
        assertEquals(6, solution.minPushBox(map));
        
        map = new char[][]{
                {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
                {'#','.','.','.','.','.','.','.','.','.','.','.','#','#','#','#'},
                {'#','.','#','#','#','#','.','#','#','#','#','.','#','#','#','.'},
                {'#','.','.','.','.','.','.','#','T','#','.','.','#','#','#','.'},
                {'#','.','.','.','#','.','.','.','.','.','.','.','#','#','#','.'},
                {'#','.','.','.','.','.','B','.','.','.','.','.','#','#','#','.'},
                {'#','.','#','#','#','#','#','#','#','#','#','.','#','#','#','.'},
                {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'#','.','.','.','.','.','.','.','S','.','.','.','.','.','.','.'},
                {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}};
        assertEquals(-1, solution.minPushBox(map));
        


    }
}

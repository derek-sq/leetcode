package leetcode.editor.cn;

//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1: 
//
// 输入:
//[
//['1','1','1','1','0'],
//['1','1','0','1','0'],
//['1','1','0','0','0'],
//['0','0','0','0','0']
//]
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//[
//['1','1','0','0','0'],
//['1','1','0','0','0'],
//['0','0','1','0','0'],
//['0','0','0','1','1']
//]
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 744 👎 0


import java.util.LinkedList;

public class P200NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new P200NumberOfIslands().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        // 法1：dfs
        public void dfs(char[][] grid, int x, int y){
            // 将现在的位置信息替换
            grid[x][y] = '0';
            // 遍历可以移动的方向
            int nr = grid.length;
            int nc = grid[0].length;
            for (int i = 0; i < directions.length; i++) {
                int nx = x + directions[i][0];
                int ny = y + directions[i][1];
                if (nx >= 0 && nx < nr && ny >= 0 && ny < nc && grid[nx][ny] == '1') {
                    dfs(grid, nx, ny);
                }
            }
            return;
        }

        public int numIslands(char[][] grid) {
            int res = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '1') {
                        dfs(grid, i, j);
                        res += 1;
                    }
                }
            }
            return res;
        }

        //法2： bfs:主要是保存状态，首先把初始状态保存到队列里，然后从队列的最前端不断的取状态，并把
        //         该状态可以转移到的状态中尚未访问过的部分加入队列，直到队列为空或者找到解
        public int numIslandsBFS(char[][] grid) {
            int rows = grid.length, cols = grid[0].length;
            int count = 0;
            boolean[][] marked = new boolean[rows][cols];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '1' && marked[i][j] == false) {
                        count += 1;
                        LinkedList<Integer> queue = new LinkedList<>();
                        queue.addLast(i * cols + j);
                        marked[i][j] = true;
                        // 取队列最前端的状态
                        while (!queue.isEmpty()){
                            int cur = queue.removeFirst();
                            int curX = cur / cols;
                            int curY = cur % cols;
                            // 加入可以转移到的状态
                            for (int k = 0; k < directions.length; k++) {
                                int nx = curX + directions[k][0];
                                int ny = curY + directions[k][1];
                                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && grid[nx][ny] == '1' && marked[nx][ny] == false) {
                                    queue.addLast(nx * cols + ny);
                                    marked[nx][ny] = true;
                                }
                            }
                        }
                    }
                }
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}

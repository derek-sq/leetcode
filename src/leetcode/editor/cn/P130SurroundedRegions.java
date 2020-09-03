package leetcode.editor.cn;

//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 362 👎 0


public class P130SurroundedRegions {
    public static void main(String[] args) {
        Solution solution = new P130SurroundedRegions().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void solve(char[][] board) {
            int rows = board.length;
            if (rows == 0 || board == null) {
                return;
            }
            int cols = board[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i == 0 || i == board.length-1 || j == 0 || j == board[0].length-1) {
                        dfs(board, i, j);
                    }
                }
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == '0') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == 'Z') {
                        board[i][j] = '0';
                    }
                }
            }
        }
        private void dfs(char[][] board, int i, int j) {
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
                return;
            }
            if (board[i][j] == '0') {
                board[i][j] = 'Z';
                dfs(board, i, j+1);
                dfs(board, i, j-1);
                dfs(board, i+1, j);
                dfs(board, i-1, j);
            }
            return;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

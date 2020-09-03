package leetcode.editor.cn;

//给定一个二维网格和一个单词，找出该单词是否存在于网格中。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例: 
//
// board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//给定 word = "ABCCED", 返回 true
//给定 word = "SEE", 返回 true
//给定 word = "ABCB", 返回 false 
//
// 
//
// 提示： 
//
// 
// board 和 word 中只包含大写和小写英文字母。 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics 数组 回溯算法 
// 👍 543 👎 0


public class P79WordSearch {
    public static void main(String[] args) {
        Solution solution = new P79WordSearch().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean exist(char[][] board, String word) {
            if (word == "" || board == null) {
                return false;
            }
            int rows = board.length;
            if (rows == 0) {
                if (word == "") {
                    return true;
                } else {
                    return false;
                }
            }

            int cols = board[0].length;
            boolean[][] visited = new boolean[rows][cols];

            boolean flag = false;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        flag = dfs(0, i, j, word, board, visited);
                        if (flag) {
                            return flag;
                        }
                    }
                }
            }
            return flag;
        }
        private boolean dfs(int start, int i, int j, String word, char[][] board, boolean[][] visited) {
            if (start == word.length()) {
                return true;
            }
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
                return false;
            }
            if (board[i][j] == word.charAt(start) && !visited[i][j]) {
                visited[i][j] = true;
                boolean temp_flag = dfs(start+1, i+1, j, word, board, visited) ||
                        dfs(start+1, i-1, j, word, board, visited) ||
                        dfs(start+1, i, j+1, word, board, visited) ||
                        dfs(start+1, i, j-1, word, board, visited);
                if (temp_flag) {
                    return temp_flag;
                } else {
                    visited[i][j] = false;
                    return temp_flag;
                }

            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

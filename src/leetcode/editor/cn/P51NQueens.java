package leetcode.editor.cn;

//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例： 
//
// 输入：4
//输出：[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// Related Topics 回溯算法 
// 👍 553 👎 0


import java.util.*;

public class P51NQueens {
    public static void main(String[] args) {
        Solution solution = new P51NQueens().new Solution();
        // todo
        List<List<String>> res = solution.solveNQueens(4);
        System.out.println(res.toString());
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private int n;
        private boolean[] col;
        private Set<Integer> leftToRight;
        private Set<Integer> rightToLeft;
        private List<List<String>> res = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            // 如果某一行定了，那么就开始找下一行
            if (n == 0) return res;
            this.n = n;
            this.col = new boolean[n];
            this.leftToRight = new HashSet<>();
            this.rightToLeft = new HashSet<>();

            Deque<Integer> path = new ArrayDeque<>();
            dfs(0, path);
            return res;
        }
        private void dfs(int row, Deque<Integer> path) {
            if (row == n) {
                res.add(pathToString(path));
                return;
            }
            for (int j = 0; j < n; j++) {
                if (!col[j] && !leftToRight.contains(row-j) && !rightToLeft.contains(row+j)) {
                    path.addLast(j);
                    col[j] = true;
                    leftToRight.add(row-j);
                    rightToLeft.add(row+j);

                    dfs(row+1, path);

                    path.removeLast();
                    col[j] = false;
                    leftToRight.remove(row-j);
                    rightToLeft.remove(row+j);
                }
            }
        }

        private List<String> pathToString(Deque<Integer> path) {
            List<String> list = new ArrayList<>();
            for (int num: path) {
                String str = String.join("", Collections.nCopies(n, "."));
                StringBuilder sb = new StringBuilder(str);
                sb.replace(num, num+1, "Q");
                list.add(sb.toString());
            }
            return list;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}

package leetcode.editor.cn;

//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 338 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P77Combinations {
    public static void main(String[] args) {
        Solution solution = new P77Combinations().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> res = new ArrayList<>();
            if (n <= 0 || k <= 0 || k > n) {
                return res;
            }
            Deque<Integer> path = new ArrayDeque<>();
            dfs(1, n, k, path, res);
            return res;
        }
        private void dfs(int start, int n, int k, Deque<Integer> path, List<List<Integer>> res) {
            if (path.size() == k) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = start; i <= n; i++) {
                //剩余数字不够了，直接剪支
                if (i + k - path.size() - 1 > n) {
                    break;
                }
                path.addLast(i);
                dfs(i+1, n, k, path, res);
                path.removeLast();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

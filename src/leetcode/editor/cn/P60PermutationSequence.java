package leetcode.editor.cn;

//给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。 
//
// 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下： 
//
// 
// "123" 
// "132" 
// "213" 
// "231" 
// "312" 
// "321" 
// 
//
// 给定 n 和 k，返回第 k 个排列。 
//
// 说明： 
//
// 
// 给定 n 的范围是 [1, 9]。 
// 给定 k 的范围是[1, n!]。 
// 
//
// 示例 1: 
//
// 输入: n = 3, k = 3
//输出: "213"
// 
//
// 示例 2: 
//
// 输入: n = 4, k = 9
//输出: "2314"
// 
// Related Topics 数学 回溯算法 
// 👍 312 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

public class P60PermutationSequence {
    public static void main(String[] args) {
        Solution solution = new P60PermutationSequence().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String getPermutation(int n, int k) {
            StringBuffer res = new StringBuffer();
            if (n <= 0 || k <= 0) {
                return res.toString();
            }
            Deque<String> path = new ArrayDeque<>();
            boolean[] used = new boolean[n];
            dfs(0, n, k, path, used, res);
            return res.toString();
        }
        private void dfs(int start, int n, int k, Deque<String> path, boolean[] used, StringBuffer res) {
            if (path.size() == n) {
                for (String v : path) res.append(v);
                return;
            }
            int cur = factorial(n - start - 1); //要放在垂直层循环外面，表示该水平层的排列数目
            for (int i = 0; i < n; i++) {
                if (!used[i]) {
                    if (cur < k) {
                        k -= cur;
                        continue; //后续不需要撤回操作，因为没有执行前面的操作，均跳过了
                    }
                    path.addLast(i+1 + "");
                    used[i] = true;
                    dfs(start+1, n, k, path, used, res);
                }
            }
        }
        private int factorial(int n) {
            int res = 1;
            if (n == 0 || n == 1) return res;
            while (n > 0) {
                res *= n;
                n -= 1;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

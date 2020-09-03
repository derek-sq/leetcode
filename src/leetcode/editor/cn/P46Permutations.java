package leetcode.editor.cn;

//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 863 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P46Permutations {
    public static void main(String[] args) {
        Solution solution = new P46Permutations().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            int length = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            if (length == 0) {
                return res;
            }
            boolean[] used = new boolean[length];
            Deque<Integer> path = new ArrayDeque<>();

            dfs(nums, length, 0, path, used, res);
            return res;
        }
        private void dfs(int[] nums, int length, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
            if (depth == length) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < length; i++) {
                if (!used[i]) {
                    path.addLast(nums[i]);
                    used[i] = true;
                    dfs(nums, length, depth+1, path, used, res);
                    used[i] = false;
                    path.removeLast();
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

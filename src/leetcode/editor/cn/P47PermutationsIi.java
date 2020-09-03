package leetcode.editor.cn;

//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法 
// 👍 385 👎 0


import java.util.*;

public class P47PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new P47PermutationsIi().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            int length = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            if (length == 0) {
                return res;
            }

            boolean[] used = new boolean[length];
            Deque<Integer> path = new ArrayDeque<>(length);

            Arrays.sort(nums);
            dfs(nums, 0, length, used, path, res);
            return res;
        }
        private void dfs(int[] nums, int depth, int length, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
            if (depth == length) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < length; i++) {
                if (!used[i]){
                    if (i > 0 && nums[i] == nums[i-1] && used[i-1] == false) {
                        continue;
                    }
                    path.addLast(nums[i]);
                    used[i] = true;
                    dfs(nums, depth+1, length, used, path, res);
                    used[i] = false;
                    path.removeLast();
                }
            }
            return;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

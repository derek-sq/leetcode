package leetcode.editor.cn;

//给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: [1,2,2]
//输出:
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//] 
// Related Topics 数组 回溯算法 
// 👍 297 👎 0


import java.util.*;

public class P90SubsetsIi {
    public static void main(String[] args) {
        Solution solution = new P90SubsetsIi().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return res;
            }
            Deque<Integer> path = new ArrayDeque<>();
            Arrays.sort(nums);
            dfs(nums, 0, path, res);
            return res;
        }
        private void  dfs(int[] nums, int start, Deque<Integer> path, List<List<Integer>> res) {
            res.add(new ArrayList<>(path));
            if (start == nums.length) {
                return;
            }
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i-1]) {
                    continue;
                }
                path.addLast(nums[i]);
                dfs(nums, i+1, path, res);
                path.removeLast();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

package leetcode.editor.cn;

//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 说明： 
//
// 
// 所有数字（包括目标数）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//] 
// Related Topics 数组 回溯算法 
// 👍 349 👎 0


import java.util.*;

public class P40CombinationSumIi {
    public static void main(String[] args) {
        Solution solution = new P40CombinationSumIi().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            int length = candidates.length;
            List<List<Integer>> res = new ArrayList<>();
            if (length == 0) {
                return res;
            }
            Arrays.sort(candidates);
            Deque<Integer> path = new ArrayDeque<>();
            dfs(candidates, 0, length, path, target, res);
            return res;
        }
        private void dfs(int[] candidates, int start, int length, Deque<Integer> path, int target, List<List<Integer>> res) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = start; i < length; i++) {
                // 不满足条件的直接剪支
                if (target - candidates[i] < 0) {
                    break;
                }
                // 同层的遇到相同的选择，剪支
                if (i > start && candidates[i] == candidates[i-1]) {
                    continue;
                }
                path.addLast(candidates[i]);
                dfs(candidates, i+1, length, path, target-candidates[i], res);
                path.removeLast();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

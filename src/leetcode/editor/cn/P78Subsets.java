package leetcode.editor.cn;

//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics 位运算 数组 回溯算法 
// 👍 734 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P78Subsets {
    public static void main(String[] args) {
        Solution solution = new P78Subsets().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return res;
            }
            Deque<Integer> path = new ArrayDeque<>();
            dfs(nums, 0, path, res);
            return res;
        }
        private void  dfs(int[] nums, int start, Deque<Integer> path, List<List<Integer>> res) {
            res.add(new ArrayList<>(path));
            if (start == nums.length) {
                return;
            }
            for (int i = start; i < nums.length; i++) {
                path.addLast(nums[i]);
                dfs(nums, i+1, path, res);
                path.removeLast();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

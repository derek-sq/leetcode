package leetcode.editor.cn;

//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意： 
//
// 答案中不可以包含重复的四元组。 
//
// 示例： 
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics 数组 哈希表 双指针 
// 👍 554 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P18FourSum {
    public static void main(String[] args) {
        Solution solution = new P18FourSum().new Solution();
        // todo 
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            int length = nums.length;
            if (nums == null || length < 4) return res;
            Arrays.sort(nums);
            // 第一个指针循环
            for (int i = 0; i < length-3; i++) {
                // 重复情况continue
                if (i > 0 && nums[i] == nums[i-1]) {
                    continue;
                }
                // 获取当前情况能取到的最小值，如果最小值都大于target,那么return
                if (target < nums[i] + nums[i+1] + nums[i+2] + nums[i+3]){
                    break;
                }
                //获取当前情况能取到的最大值，如果最大值小于target,那么continue
                if (target > nums[i] + nums[length-1] + nums[length-2] + nums[length-3]){
                    continue;
                }
                // 第二个指针循环，其实就是3Sum了
                for (int j = i+1; j < length-2; j++) {
                    // 重复情况continue
                    if (j > i+1 && nums[j] == nums[j-1]) {
                        continue;
                    }
                    // 获取当前情况能取到的最小值，如果最小值都大于target,那么return
                    if (target < nums[i] + nums[j] + nums[j+1] + nums[j+2]){
                        break;
                    }
                    //获取当前情况能取到的最大值，如果最大值小于target,那么continue
                    if (target > nums[i] + nums[length-1] + nums[length-2] + nums[j]){
                        continue;
                    }
                    int left = j+1;
                    int right = length-1;
                    while(left < right){
                        int sum = nums[i] + nums[j] + nums[left] + nums[right];
                        if (sum == target){
                            res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                            while(left < right && nums[left] == nums[left+1]){
                                left += 1;
                            }
                            while(left < right && nums[right] == nums[right-1]){
                                right -= 1;
                            }
                            left += 1;
                            right -= 1;
                        }
                        else if (sum > target){
                            right -= 1;
                        }
                        else if (sum < target){
                            left += 1;
                        }
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}

package leetcode.editor.cn;

//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1278 👎 0


import java.util.ArrayList;
import java.util.List;

public class P22GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new P22GenerateParentheses().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            if (n == 0) {
                return res;
            }
            int left = n, right = n;
            StringBuffer path = new StringBuffer();
            dfs(left, right, path, res);
            return res;
        }
        private void dfs(int left, int right, StringBuffer path, List<String> res) {
            if (left == 0 && right == 0) {
                res.add(path.toString());
                return;
            }
            if (left < 0) {
                return;
            }else if(right < 0) {
                return;
            }
            if (left > right) {
                return;
            }
            path.append('(');
            left -= 1;
            dfs(left, right, path, res);
            path.deleteCharAt(path.length()-1);
            left += 1;

            path.append(')');
            right -= 1;
            dfs(left, right, path, res);
            path.deleteCharAt(path.length()-1);
            right += 1;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}

package leetcode.editor.cn;

//给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。 
//
// 
//
// 示例：
//输入：S = "a1b2"
//输出：["a1b2", "a1B2", "A1b2", "A1B2"]
//
//输入：S = "3z4"
//输出：["3z4", "3Z4"]
//
//输入：S = "12345"
//输出：["12345"]
// 
//
// 
//
// 提示： 
//
// 
// S 的长度不超过12。 
// S 仅由数字和字母组成。 
// 
// Related Topics 位运算 回溯算法 
// 👍 201 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P784LetterCasePermutation {
    public static void main(String[] args) {
        Solution solution = new P784LetterCasePermutation().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> letterCasePermutation(String S) {
            List<String> res = new ArrayList<>();
            int length = S.length();
            if (length == 0) {
                return res;
            }
            Deque<Character> path = new ArrayDeque<>();
            dfs(0, length, S, path, res);
            return res;
        }
        private void dfs(int start, int length, String S, Deque<Character> path, List<String> res) {
            if (start == length) {
                String s = new String();
                for (Character v: path) {
                    s += v;
                }
                res.add(s);
                return;
            }
            StringBuffer tempCh = new StringBuffer();
            char cur = S.charAt(start);
            if (cur >= 'A' && cur <= 'Z') {
                tempCh.append(cur);
                tempCh.append((char) (cur+'a'-'A'));
            }else if (cur >= 'a' && cur <= 'z') {
                tempCh.append(cur);
                tempCh.append((char) (cur-'a'+'A'));
            }else {
                tempCh.append(cur);
            }
            for (int i = 0; i < tempCh.length(); i++) {
                path.addLast(tempCh.charAt(i));
                dfs(start+1, length, S, path, res);
                path.removeLast();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

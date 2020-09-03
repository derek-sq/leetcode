package leetcode.editor.cn;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法 
// 👍 901 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P17LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new P17LetterCombinationsOfAPhoneNumber().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> letterCombinations(String digits) {
            List<String> res = new ArrayList<>();
            int length = digits.length();
            if (length == 0) {
                return res;
            }
            Map<Character, String> map = new HashMap<>();
            map.put('2', "abc");
            map.put('3', "def");
            map.put('4', "ghi");
            map.put('5', "jkl");
            map.put('6', "mno");
            map.put('7', "pqrs");
            map.put('8', "tuv");
            map.put('9', "wxyz");

            StringBuffer path = new StringBuffer();
            dfs(0, digits, length, path, map, res);
            return res;
        }
        // 23
        private void dfs(int start, String digits, int length, StringBuffer path, Map<Character, String> map, List<String> res) {
            if (path.length() == length) {
                res.add(path.toString());
                return;
            }
            String tempStr = map.get(digits.charAt(start));
            for (int i = 0; i < tempStr.length(); i++) {
                Character c = tempStr.charAt(i);
                path.append(c);
                dfs(start+1, digits, length, path, map, res);
                path.deleteCharAt(path.length()-1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

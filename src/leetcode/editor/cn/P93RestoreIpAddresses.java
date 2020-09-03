package leetcode.editor.cn;

//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。 
//
// 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312"
// 和 "192.168@1.1" 是 无效的 IP 地址。 
//
// 
//
// 示例 1： 
//
// 输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 输入：s = "1111"
//输出：["1.1.1.1"]
// 
//
// 示例 4： 
//
// 输入：s = "010010"
//输出：["0.10.0.10","0.100.1.0"]
// 
//
// 示例 5： 
//
// 输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3000 
// s 仅由数字组成 
// 
// Related Topics 字符串 回溯算法 
// 👍 403 👎 0


import java.util.ArrayList;
import java.util.List;

public class P93RestoreIpAddresses {
    public static void main(String[] args) {
        Solution solution = new P93RestoreIpAddresses().new Solution();
        // todo 
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> restoreIpAddresses(String s) {
            List<String> res = new ArrayList<>();
            int length = s.length();
            if (s == null || length < 4 || length > 12) {
                return res;
            }
            List<String> path = new ArrayList<>();
            int splitUsed = 0;
            dfs(s, 0, length, splitUsed, path, res);
            return res;
        }
        private void dfs(String s, int start, int length, int splitUsed, List<String> path, List<String> res) {
            if (splitUsed == 4) {
                if (start == length) {
                    String temp = "";
                    for (String k :path) {
                        temp = temp + k + '.';
                    }
                    temp = temp.substring(0, temp.length()-1);
                    res.add(temp);
                }else {
                    return;
                }
            }
            for (int i = 1; i <= 3; i++) {
                if (start + i > length) return;
                if ( i > 1 && s.charAt(start) == '0') return;

                String tempStr = s.substring(start, start + i);
                if (Integer.parseInt(tempStr) > 255) return;

                path.add(tempStr);
                splitUsed += 1;
                dfs(s, start + i, length, splitUsed, path, res);
                path.remove(path.size()-1);
                splitUsed -= 1;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

package leetcode.editor.cn;

//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 7
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
// 
//
// 示例 2: 
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
//
// 提示：
// 
// 1 <= prices.length <= 3 * 10 ^ 4 
// 0 <= prices[i] <= 10 ^ 4 
// 
// Related Topics 贪心算法 数组 
// 👍 836 👎 0



public class P122BestTimeToBuyAndSellStockIi {
    public static void main(String[] args) {
        Solution solution = new P122BestTimeToBuyAndSellStockIi().new Solution();
        // todo
        System.out.println(solution.maxProfit(new int[]{1}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 动态规划，两种状态，一种是持有现金，一种是持有股票, dp表示利润,
        // 0持有现金，1持有股票，状态转移：0-->1--0-->1-->0

        public int maxProfit(int[] prices) {
            int[][] dp = new int[prices.length][2];
            dp[0][0] = 0; //持有现金
            dp[0][1] = -prices[0]; //持有股票

            for (int i = 1; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
                dp[i][1] = Math.max(dp[i-1][0] - prices[i], dp[i-1][1]);
            }
            return dp[prices.length-1][0];
        }

        // 贪心法
        // 考虑到可以多次交易，并且必定是卖出价格大于买入价格时才会卖，因此直接算连续的增长值即可
        // 这道题 “贪心” 的地方在于，对于 “今天的股价 - 昨天的股价”，得到的结果有 3 种可能：（1）正数（2）0（3）负数。
        // 贪心的决策：当前每次只加正数
        public int maxProfitGreedy(int[] prices) {
            int res = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] - prices[i-1] > 0) {
                    res += prices[i] - prices[i-1];
                }
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

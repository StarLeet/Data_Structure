package 动态规划;
//  https://leetcode-cn.com/problems/min-cost-climbing-stairs/
@SuppressWarnings("all")
/**        解题思路
 *         1.确定dp数组(dp table)以及下标的含义
 *         2.确定递推公式
 *         3.dp数组如何初始化
 *         4.确定遍历顺序
 *         5.举例推导dp数组
 */
public class _746_使用最小花费爬楼梯 {
    /***
     *     1. dp[i] 到第i个阶梯的最小花费
     *     2. dp[i] = min(dp[i-1],dp[i-2]) + cost[i]   #到第i个阶梯可由i-1或者i-2个阶梯上来,并支付i阶梯费用
     *     3. 只需知道dp[0] = cost[0], dp[1] = cost[1]
     *         #因为这属于第一步可以直接支付,而无需考虑递归公式的min项
     *     4. 遍历顺序,从前往后
     *     5. dp = [1,100,2,3,3,103,4,5,104,6]
     */
    public static int minCostClimbingStairs(int[] cost) {
        if(cost.length <= 2) return Math.min(cost[0],cost[1]);
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for(int i =2;i<cost.length;i++){
            dp[i] = Math.min(dp[i-2],dp[i-1]) + cost[i];
        }
        StringBuilder sb = new StringBuilder();
        sb.append("dp = [");
        for (int i = 0;i < dp.length;i++ ) {
            if (i == 0) sb.append(dp[0]);
            else  sb.append(",").append(dp[i]);
        }
        sb.append("]");
        System.out.println(sb.toString());
        return Math.min(dp[cost.length-2],dp[cost.length-1]);
    }

    public static void main(String[] args) {
        int[] cost = new int[6];
        for (int i = 0;i < cost.length;i++) {
            cost[i] = (int)(Math.random() * 10) + 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("cost = [");
        for (int i = 0;i < cost.length;i++) {
            if (i == 0) sb.append(cost[0]);
            else  sb.append(",").append(cost[i]);
        }
        sb.append("]");
        System.out.println(sb.toString());
        System.out.println(minCostClimbingStairs(cost));
    }
}

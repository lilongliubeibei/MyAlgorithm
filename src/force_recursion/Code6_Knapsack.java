package force_recursion;

//两个数组分别表示i号物品的重量和价值，给定一个整数bag
public class Code6_Knapsack {

    // 法一
    public static int maxValue1(int[] weights, int[] values, int bag) {
        return process1(weights, values, 0, 0, bag);
    }

    //从i开始考虑，alreadyWeight表示已有重量，bag最大重量
    public static int process1(int[] weights, int[] values, int i, int
            alreadyWeight, int bag) {
        if (alreadyWeight > bag) {
            return 0;
        }
        //i越界
        if (i == weights.length) {
            return 0;
        }
        //比较下一步中的最大值, 第i个物品不取和取第i个物品
        return Math.max(
                process1(weights, values, i + 1, alreadyWeight, bag),
                values[i] + process1(weights, values, i + 1, alreadyWeight + weights[i],
                        bag));
    }

    // 法2 有错误
    public static int maxValue2(int[] c, int[] p, int bag) {
        int[][] dp = new int[c.length + 1][bag + 1];
        for (int i = c.length - 1; i >= 0; i--) {
            for (int j = bag; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (j + c[i] <= bag) {
                    dp[i][j] = Math.max(dp[i][j], p[i] + dp[i + 1][j + c[i]]);
                }
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(maxValue1(weights, values, bag));
        System.out.println(maxValue2(weights, values, bag));
    }

}

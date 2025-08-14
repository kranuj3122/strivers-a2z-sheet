
public class StockBuySell {

    static int maxProfit(int[] prices) {
        // Best Time to Buy and Sell Stock [Single buy & sell]
        int n = prices.length;
        if(n==1) {
            return 0;
        }
        int min = prices[0];
        int ans = 0;
        for(int i=1; i<n; i++) {
            ans = Math.max(ans, prices[i]-min);
            min = Math.min(min, prices[i]);
        }
        return ans;
    }


    static int maxProfit2Util(int[] p, int ind, int buy, int[][] dp) {
        if(ind==p.length) {
            return 0;
        }
        if(dp[ind][buy] != -1) {
            return dp[ind][buy];
        }

        if(buy==1) {
            return dp[ind][buy] = Math.max(-p[ind]+maxProfit2Util(p, ind+1, 0, dp), maxProfit2Util(p, ind+1, 1, dp));
        }
        return dp[ind][buy] = Math.max(p[ind]+maxProfit2Util(p, ind+1, 1, dp), maxProfit2Util(p, ind+1, 0, dp));
    }
    static int maxProfit2(int[] prices) {
        // Best Time to Buy and Sell Stock II [Any no. of buy & sell]

        int n = prices.length;
        // int[][] dp = new int[n+1][2];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return solve(prices, 0, 1, dp);

        // for(int i=n-1; i>=0; i--) {
        //     dp[i][1] = Math.max(-prices[i]+dp[i+1][0], dp[i+1][1]); // buy
        //     dp[i][0] = Math.max(prices[i]+dp[i+1][1], dp[i+1][0]); // sell
        // }
        // return dp[0][1];

        int[] ahead = new int[2];
        for(int i=n-1; i>=0; i--) {
            int[] curr = new int[2];
            curr[1] = Math.max(-prices[i]+ahead[0], ahead[1]); // buy
            curr[0] = Math.max(prices[i]+ahead[1], ahead[0]); // sell

            ahead = curr;
        }
        return ahead[1];
    }


    static int maxProfit3Util(int[] p, int ind, int buy, int cap, int[][][] dp) {
        if(cap==0 || ind==p.length) {
            return 0;
        }
        if(dp[ind][buy][cap] != -1) {
            return dp[ind][buy][cap];
        }

        if(buy==1) {
            return dp[ind][buy][cap] = Math.max(-p[ind]+maxProfit3Util(p, ind+1, 0, cap, dp), maxProfit3Util(p, ind+1, 1, cap, dp));
        }
        return dp[ind][buy][cap] = Math.max(p[ind]+maxProfit3Util(p, ind+1, 1, cap-1, dp), maxProfit3Util(p, ind+1, 0, cap, dp));
    }

    static int maxProfit3Util2(int[] p, int ind, int trans, int[][] dp) {
        if(ind==p.length || trans==4) {
            return 0;
        }
        if(dp[ind][trans] != -1) {
            return dp[ind][trans];
        }

        if(trans%2 == 0) {
            return dp[ind][trans] = Math.max(-p[ind]+maxProfit3Util2(p, ind+1, trans+1, dp), maxProfit3Util2(p, ind+1, trans, dp));
        }
        return dp[ind][trans] = Math.max(p[ind]+maxProfit3Util2(p, ind+1, trans+1, dp), maxProfit3Util2(p, ind+1, trans, dp));
    }
    static int maxProfit3(int[] prices) {
        // Best Time to Buy and Sell Stock III [maximum profit, at most two transactions]
        int n = prices.length;
        // int[][][] dp = new int[n+1][2][3];
        // for(int[][] R: dp) {
        //     for(int[] r: R) {
        //         Arrays.fill(r, -1);
        //     }
        // }
        // return maxProfit3Util(prices, 0, 1, 2, dp);

        // int[][] dp = new int[n+1][5];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return maxProfit3Util2(prices, 0, 0, dp);

        // for(int i=n-1; i>=0; i--) {
        //     for(int j=3; j>=0; j--) {
        //         if(j%2 == 0) {
        //             dp[i][j] = Math.max(-prices[i]+dp[i+1][j+1], dp[i+1][j]);
        //         }
        //         else {
        //             dp[i][j] = Math.max(prices[i]+dp[i+1][j+1], dp[i+1][j]);
        //         }
        //     }
        // }
        // return dp[0][0];

        int[] ahead = new int[5];
        for(int i=n-1; i>=0; i--) {
            int[] curr = new int[5];
            for(int j=3; j>=0; j--) {
                if(j%2 == 0) {
                    curr[j] = Math.max(-prices[i]+ahead[j+1], ahead[j]);
                }
                else {
                    curr[j] = Math.max(prices[i]+ahead[j+1], ahead[j]);
                }
            }
            ahead = curr;
        }
        return ahead[0];
    }

    static int maxProfit4(int k, int[] prices) {
        // Best Time to Buy and Sell Stock IV [maximum profit, at most k transactions]
        int n = prices.length;
        int[] ahead = new int[2*k + 1];
        for(int i=n-1; i>=0; i--) {
            int[] curr = new int[2*k + 1];
            for(int j=2*k-1; j>=0; j--) {
                if(j%2 == 0) {
                    curr[j] = Math.max(-prices[i]+ahead[j+1], ahead[j]);
                }
                else {
                    curr[j] = Math.max(prices[i]+ahead[j+1], ahead[j]);
                }
            }
            ahead = curr;
        }
        return ahead[0];
    }


    static int maxProfitWithCooldownUtil(int[] p, int ind, int buy, int[][] dp) {
        if(ind >= p.length) {
            return 0;
        }
        if(dp[ind][buy] != -1) {
            return dp[ind][buy];
        }

        if(buy==1) {
            return dp[ind][buy] = Math.max(-p[ind]+maxProfitWithCooldownUtil(p, ind+1, 0, dp), maxProfitWithCooldownUtil(p, ind+1, 1, dp));
        }
        return dp[ind][buy] = Math.max(p[ind]+maxProfitWithCooldownUtil(p, ind+2, 1, dp), maxProfitWithCooldownUtil(p, ind+1, 0, dp));
    }
    static int maxProfitWithCooldown(int[] prices) {
        int n = prices.length;
        // int[][] dp = new int[n+1][2];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return maxProfitWithCooldownUtil(prices, 0, 1, dp);

        int[][] dp = new int[n+2][2];
        for(int i=n-1; i>=0; i--) {
            dp[i][1] = Math.max(-prices[i]+dp[i+1][0], dp[i+1][1]); // buy
            dp[i][0] = Math.max(prices[i]+dp[i+2][1], dp[i+1][0]); // sell
        }
        return dp[0][1];
    }

    static int maxProfitWithFee(int[] prices, int fee) {
        int n = prices.length;
        int[] ahead = new int[2];
        for(int i=n-1; i>=0; i--) {
            int[] curr = new int[2];
            curr[1] = Math.max(-prices[i]+ahead[0], ahead[1]); // buy
            curr[0] = Math.max(prices[i]-fee+ahead[1], ahead[0]); // sell

            ahead = curr;
        }
        return ahead[1];
    }

    public static void main(String[] args) {
        // int[] prices = {7, 1, 5, 3, 6, 4};
        // System.out.println(maxProfit(prices));

        // int[] prices = {7, 1, 5, 3, 6, 4};
        // System.out.println(maxProfit2(prices));

        // int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        // System.out.println(maxProfit3(prices));

        // int k = 2;
        // int[] prices = {3, 2, 6, 5, 0, 3};
        // System.out.println(maxProfit4(k, prices));

        // int[] prices = {1, 2, 3, 0, 2};
        // System.out.println(maxProfitWithCooldown(prices));

        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(maxProfitWithFee(prices, fee));
    }
}

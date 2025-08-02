import java.util.*;

public class SubsequenceDP {
    static int knapsackHelper(int[] wt, int[] val, int w, int n, int[][] dp) {
        if(n==0 || w==0) {
            return 0;
        }
        if(dp[n][w] != -1) {
            return dp[n][w];
        }
        
        if(wt[n-1]<=w) {
            return dp[n][w] = Math.max(val[n-1]+knapsackHelper(wt, val, w-wt[n-1], n-1, dp), knapsackHelper(wt, val, w, n-1, dp));
        }
        return dp[n][w] = knapsackHelper(wt, val, w, n-1, dp);
    }
    static int knapsack(int W, int val[], int wt[]) {
        int n = val.length;
        int[][] dp = new int[n+1][W+1];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return solve(wt, val, W, n, dp);
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=W; j++) {
                if(wt[i-1]<=j) {
                    dp[i][j] = Math.max(val[i-1]+dp[i-1][j-wt[i-1]], dp[i-1][j]);
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        return dp[n][W];
    }

    static int isSubsetSumHelper(int[] arr, int sum, int n, int[][] dp) {
        if(sum==0) {
            return 1;
        }
        if(n==0) {
            return 0;
        }
        if(dp[n][sum] != -1) {
            return dp[n][sum];
        }
        
        if(arr[n-1]<=sum) {
            return dp[n][sum] = isSubsetSumHelper(arr, sum-arr[n-1], n-1, dp) | isSubsetSumHelper(arr, sum, n-1, dp);
        }
        return dp[n][sum] = isSubsetSumHelper(arr, sum, n-1, dp);
    }
    static Boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;
        // int[][] dp = new int[n+1][sum+1];
        // for(int[] row: dp) {
        //     Arrays.fill(row, -1);
        // }
        // return isSubsetSumHelper(arr, sum, n, dp)==1;
        
        boolean[][] dp = new boolean[n+1][sum+1];
        for(int i=0; i<=n; i++) {
            dp[i][0] = true;
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=sum; j++) {
                if(arr[i-1]<=j) {
                    dp[i][j] = dp[i-1][j-arr[i-1]] || dp[i-1][j];
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][sum];
    }

    static boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num: nums) {
            sum += num;
        }
        if(sum%2 == 1) {
            return false;
        }
        return isSubsetSum(nums, sum/2);
    }

    static int unboundKnapsackHelper(int[] wt, int[] val, int n, int w, int[][] dp) {
        if(n==0 || w==0) {
            return 0;
        }
        if(dp[n][w] != -1) {
            return dp[n][w];
        }
        if(wt[n-1] <= w) {
            return dp[n][w] = Math.max(val[n-1]+unboundKnapsackHelper(wt, val, n, w-wt[n-1], dp), unboundKnapsackHelper(wt, val, n-1, w, dp));
        }
        return dp[n][w] = unboundKnapsackHelper(wt, val, n-1, w, dp);
    }
    static int unboundKnapsack(int val[], int wt[], int capacity) {
        // code here
        int n = wt.length;
        int[][] dp = new int[n+1][capacity+1];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return unboundKnapsackHelper(wt, val, n, capacity, dp);
        
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=capacity; j++) {
                if(wt[i-1]<=j) {
                    dp[i][j] = Math.max(val[i-1]+dp[i][j-wt[i-1]], dp[i-1][j]);
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][capacity];
    }

    static int coinChangeHelper(int[] c, int n, int amount, int[][] dp) {
        if(amount==0) {
            return 1;
        }
        if(n==0) {
            return 0;
        }
        if(dp[n][amount] != -1) {
            return dp[n][amount];
        }
        if(c[n-1]<=amount) {
            return dp[n][amount] = coinChangeHelper(c, n, amount-c[n-1], dp) + coinChangeHelper(c, n-1, amount, dp);
        }
        return dp[n][amount] = coinChangeHelper(c, n-1, amount, dp);
    }
    static int coinChange(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        // for(int[] row: dp) {
        //     Arrays.fill(row, -1);
        // }
        // return coinChangeHelper(coins, n, amount, dp);

        for(int i=0; i<=n; i++) {
            dp[i][0] = 1;
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=amount; j++) {
                dp[i][j] = dp[i-1][j];
                if(coins[i-1] <= j) {
                    dp[i][j] += dp[i][j-coins[i-1]];
                }
            }
        }
        return dp[n][amount];
    }

    static int perfectSumHelper(int[] nums, int target, int currSum, int ind, int[][] dp) {
        if(ind==nums.length) {
            if(currSum==target) {
                return 1;
            }
            return 0;
        }
        
        if(dp[ind][currSum] != -1) {
            return dp[ind][currSum];
        }
        
        if(currSum+nums[ind] <= target) {
            return dp[ind][currSum] = perfectSumHelper(nums, target, currSum+nums[ind], ind+1, dp) + perfectSumHelper(nums, target, currSum, ind+1, dp);
        }
        return dp[ind][currSum] = perfectSumHelper(nums, target, currSum, ind+1, dp);
    }
    static int perfectSum(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n+1][target+1];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return perfectSumHelper(nums, target, 0, 0, dp);
        dp[0][0] = 1;
        for(int i=1; i<=n; i++) {
            for(int j=0; j<=target; j++) {
                dp[i][j] = dp[i-1][j];
                if(nums[i-1] <= j) {
                    dp[i][j] += dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[n][target];
    }

    static int countPartitions(int[] arr, int d) {
        int sum = 0;
        for(int a: arr) {
            sum += a;
        }
        if((sum+d)%2 == 1) {
            return 0;
        }
        int target = (sum+d)/2;

        return perfectSum(arr, target);
    }

    static int findTargetSumWaysHelper(int[] nums, int target, int currSum, int ind, Map<String, Integer> dp) {
        if(ind==nums.length) {
            if(currSum==target) {
                return 1;
            }
            return 0;
        }
        String key = ind+"*"+currSum;
        if(dp.containsKey(key)) {
            return dp.get(key);
        }
        
        if(currSum+nums[ind] <= target) {
            int val = findTargetSumWaysHelper(nums, target, currSum+nums[ind], ind+1, dp) + findTargetSumWaysHelper(nums, target, currSum, ind+1, dp);
            dp.put(key, val);
            return val;
        }


        int val = findTargetSumWaysHelper(nums, target, currSum, ind+1, dp);
        dp.put(key, val);
        return val;
    }
    static int findTargetSumWays(int[] nums, int d) {
        // d might be negative (+++, ---)
        int sum = 0;
        for(int a: nums) {
            sum += a;
        }
        if((sum+d)%2 == 1) {
            return 0;
        }
        int target = (sum+d)/2;
        Map<String, Integer> dp = new HashMap<>(); // target might be negative
        return findTargetSumWaysHelper(nums, target, 0, 0, dp);
    }

    static int minCoinChangeHelper(int[] coins, int n, int amount, int[][] dp) {
        if(n==0) {
            return (int)1e7;
        }
        if(amount==0) {
            return 0;
        }
        if(n==1) {
            if(amount%coins[n-1] == 0) {
                return amount/coins[n-1];
            }
            return (int)1e7;
        }
        if(dp[n][amount] != -1) {
            return dp[n][amount];
        }
        if(coins[n-1] <= amount) {
            int keep =  1 + minCoinChangeHelper(coins, n, amount-coins[n-1], dp);
            int skip = minCoinChangeHelper(coins, n-1, amount, dp);
            return dp[n][amount] = Math.min(keep, skip);
        }
        return dp[n][amount] = minCoinChangeHelper(coins, n-1, amount, dp);
    }
    static int minCoinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // int val = minCoinChangeHelper(coins, n, amount, dp);
        // return val==(int)1e7 ? -1 : val;
        for(int i=0; i<=amount; i++) {
            dp[0][i] = (int)1e7;
        }
        for(int i=1; i<=amount; i++) {
            dp[1][i] = i%coins[0]==0 ? i/coins[0] : (int)1e7;
        }
        for(int i=2; i<=n; i++) {
            for(int j=1; j<=amount; j++) {
                dp[i][j] = dp[i-1][j];
                if(coins[i-1]<=j) {
                    dp[i][j] = Math.min(1+dp[i][j-coins[i-1]], dp[i][j]);
                }
            }
        }
        return dp[n][amount]==(int)1e7 ? -1 : dp[n][amount];
    }

    static int cutRodHelper(int[] price, int n, int w, int[][] dp) {
        if(n==0 || w==0) {
            return 0;
        }
        if(dp[n][w] != -1) {
            return dp[n][w];
        }
        if(n<=w) {
            int keep = price[n-1]+cutRodHelper(price, n, w-n, dp);
            int skip = cutRodHelper(price, n-1, w, dp);
            return dp[n][w] = Math.max(keep, skip);
        }
        return dp[n][w] = cutRodHelper(price, n-1, w, dp);

    }
    static int cutRod(int[] price) {
        int n = price.length;
        int[][] dp = new int[n+1][n+1];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return cutRodHelper(price, n, n, dp);
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                dp[i][j] = dp[i-1][j];
                if(i<=j) {
                    dp[i][j] = Math.max(price[i-1]+dp[i][j-i], dp[i][j]);
                }
            }
        }
        return dp[n][n];
    }

    public static void main(String[] args) {
        // int W = 5;
        // int[] val = {10, 40, 30, 50};
        // int[] wt = {5, 4, 2, 3};
        // System.out.println(knapsack(W, val, wt));

        // int[] arr = {3, 34, 4, 12, 5, 2};
        // int sum = 9;
        // System.out.println(isSubsetSum(arr, sum));

        // int[] nums = {1, 5, 11, 5};
        // System.out.println(canPartition(nums));

        // int[] arr = {5, 2, 3, 10, 0, 6, 8};
        // int target = 10;
        // System.out.println(perfectSum(arr, target));

        // int[] arr = {1, 2, 1, 0, 1, 3, 3};
        // int d = 11;
        // System.out.println(countPartitions(arr, d));

        // int[] nums2 = {1, 1, 1, 1, 1};
        // int target2 = 3;
        // System.out.println(findTargetSumWays(nums2, target2));

        // int[] val = {6, 1, 7, 7};
        // int[] wt = {1, 3, 4, 5};
        // int capacity = 8;
        // System.out.println(unboundKnapsack(val, wt, capacity));

        // int amount = 5;
        // int[] coins = {1, 2, 5};
        // System.out.println(coinChange(amount, coins));

        // int[] coins2 = {1, 2, 5};
        // int amount2 = 11;
        // System.out.println(minCoinChange(coins2, amount2));

        int[] price = {1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(cutRod(price));

    }
}

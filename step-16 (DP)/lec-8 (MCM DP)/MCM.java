import java.util.*;
import java.util.Arrays;

public class MCM {
    static int matrixMultiplicationUtil(int[] arr, int i, int j, int[][] dp) {
        if(i==j) {
            return 0;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        int mn = Integer.MAX_VALUE;
        for(int k=i; k<j; k++) {
            int tmp = arr[i-1]*arr[k]*arr[j] + matrixMultiplicationUtil(arr, i, k, dp) + matrixMultiplicationUtil(arr, k+1, j, dp);
            mn = Math.min(mn, tmp);
        }
        return dp[i][j] = mn;
    }
    static int matrixMultiplication(int arr[]) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return matrixMultiplicationUtil(arr, 1, n-1, dp);
        
        for(int i=n-1; i>=1; i--) {
            for(int j=i+1; j<n; j++) {
                int mn = Integer.MAX_VALUE;
                for(int k=i; k<j; k++) {
                    int tmp = arr[i-1]*arr[k]*arr[j] + dp[i][k] + dp[k+1][j];
                    mn = Math.min(mn, tmp);
                }
                dp[i][j] = mn;
            }
        }
        return dp[1][n-1];
    }

    static int countWaysUtil(String s, int i, int j, int isTrue, Integer[][][] dp) {
        if(i>j) {
            return 0;
        }
        if(i==j) {
            if(isTrue==1) {
                return s.charAt(i)=='T' ? 1 : 0;
            }
            return s.charAt(i)=='F' ? 1 : 0;
        }
        if(dp[i][j][isTrue] != null) {
            return dp[i][j][isTrue];
        }
        int ways = 0;
        for(int k=i+1; k<j; k+=2) {
            int lf = countWaysUtil(s, i, k-1, 0, dp);
            int lt = countWaysUtil(s, i, k-1, 1, dp);
            int rf = countWaysUtil(s, k+1, j, 0, dp);
            int rt = countWaysUtil(s, k+1, j, 1, dp);
            
            switch(s.charAt(k)) {
                case '|':
                    if(isTrue==1) {
                        ways += lf*rt + lt*rf + lt*rt;
                    }
                    else {
                        ways += lf*rf;
                    }
                    break;
                case '&':
                    if(isTrue!=1) {
                        ways += lf*rt + lt*rf + lf*rf;
                    }
                    else {
                        ways += lt*rt;
                    }
                    break;
                case '^':
                    if(isTrue==1) {
                        ways += lf*rt + lt*rf;
                    }
                    else {
                        ways += lf*rf + lt*rt;
                    }
                    break;
            }
        }
        return dp[i][j][isTrue] = ways;
    }
    static int countWays(String s) {
        // Count the number of ways so that the value of expression evaluates to true.
        int n = s.length();
        // Integer[][][] dp = new Integer[n][n][2];
        // return countWaysUtil(s, 0, n-1, 1, dp);
        
        int[][][] dp = new int[n][n][2];
        for(int i=0; i<n; i++) {
            dp[i][i][0] = s.charAt(i)=='F' ? 1 : 0;
            dp[i][i][1] = s.charAt(i)=='T' ? 1 : 0;
        }
        for(int i=n-1; i>=0; i--) {
            for(int j=i+1; j<n; j++) {
                for(int isTrue=0; isTrue<=1; isTrue++) {
                    int ways = 0;
                    for(int k=i+1; k<j; k+=2) {
                        int lf = dp[i][k-1][0];
                        int lt = dp[i][k-1][1];
                        int rf = dp[k+1][j][0];
                        int rt = dp[k+1][j][1];
                        
                        switch(s.charAt(k)) {
                            case '|':
                                if(isTrue==1) {
                                    ways += lf*rt + lt*rf + lt*rt;
                                }
                                else {
                                    ways += lf*rf;
                                }
                                break;
                            case '&':
                                if(isTrue!=1) {
                                    ways += lf*rt + lt*rf + lf*rf;
                                }
                                else {
                                    ways += lt*rt;
                                }
                                break;
                            case '^':
                                if(isTrue==1) {
                                    ways += lf*rt + lt*rf;
                                }
                                else {
                                    ways += lf*rf + lt*rt;
                                }
                                break;
                        }
                    }
                    dp[i][j][isTrue] = ways;
                }
            }
        }
        
        return dp[0][n-1][1];
    }

    static boolean isPallindrome(String s, int i, int j) {
        if(i==j) {
            return true;
        }
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    static int minCutUtil(String s, int ind, int[] dp) {
        int n = s.length();
        if(ind==n) {
            return 0;
        }
        if(dp[ind] != -1) {
            return dp[ind];
        }
        int mn = Integer.MAX_VALUE;
        for(int i=ind; i<n; i++) {
            if(isPallindrome(s, ind, i)) {
                int tmp = 1 + minCutUtil(s, i+1, dp);
                mn = Math.min(mn, tmp);
            }
        }
        return dp[ind] = mn;
    }
    static int minCut(String s) {
        // Return the minimum cuts needed for a palindrome partitioning of s
        int n = s.length();
        if(isPallindrome(s, 0, n-1)) {
            return 0;
        }
        int[] dp = new int[n+1];
        // Arrays.fill(dp, -1);
        // return minsolveCutUtil(s, 0, dp)-1;

        for(int ind=n-1; ind>=0; ind--) {
            int mn = Integer.MAX_VALUE;
            for(int i=ind; i<n; i++) {
                if(isPallindrome(s, ind, i)) {
                    int tmp = 1 + dp[i+1];
                    mn = Math.min(mn, tmp);
                }
            }
            dp[ind] = mn;
        }
        return dp[0]-1;
    }

    static int maxSumAfterPartitioningUtil(int[] arr, int ind, int k, int[] dp) {
        int n = arr.length;
        if(ind==n) {
            return 0;
        }
        if(dp[ind] != -1) {
            return dp[ind];
        }
        int len = 0;
        int mx = 0;
        int ans = Integer.MIN_VALUE;
        for(int i=ind; i<Math.min(n, ind+k); i++) {
            len++;
            mx = Math.max(mx, arr[i]);
            int tmp = (len*mx) + maxSumAfterPartitioningUtil(arr, i+1, k, dp);
            ans = Math.max(ans, tmp);
        }
        return dp[ind] = ans;
    }
    static int maxSumAfterPartitioning(int[] arr, int k) {
        // After partitioning, each subarray has their values changed to become the maximum value of that subarray
        int n = arr.length;
        int[] dp = new int[n+1];
        // Arrays.fill(dp, -1);
        // return maxSumAfterPartitioningUtil(arr, 0, k, dp);
        for(int ind=n-1; ind>=0; ind--) {
            int len = 0;
            int mx = 0;
            int ans = Integer.MIN_VALUE;
            for(int i=ind; i<Math.min(n, ind+k); i++) {
                len++;
                mx = Math.max(mx, arr[i]);
                int tmp = (len*mx) + dp[i+1];
                ans = Math.max(ans, tmp);
            }
            dp[ind] = ans;
        }
        return dp[0];
    }

    static int minCostUtil(int[] cuts, int i, int j, int n, int[][] dp) {
        if(i>j) {
            return 0;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        int c = cuts.length;
        int l = i-1>=0 ? cuts[i-1] : 0;
        int r = j+1<c ? cuts[j+1] : n;
        int len = r-l;
        int ans = Integer.MAX_VALUE;
        for(int k=i; k<=j; k++) {
            int tmp = len;
            tmp += k-1>=0 && dp[i][k-1] != -1 ? dp[i][k-1] : minCostUtil(cuts, i, k-1, n, dp);
            tmp += k+1<n && dp[k+1][j] != -1 ? dp[k+1][j] : minCostUtil(cuts, k+1, j, n, dp);
            ans = Math.min(ans, tmp);
        }
        return dp[i][j] = ans;
    }
    static int minCost(int n, int[] cuts) {
        int c = cuts.length;
        Arrays.sort(cuts);
        int[][] dp = new int[c+1][c+1];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return minCostUtil(cuts, 0, c-1, n, dp);
        for(int i=c-1; i>=0; i--) {
            for(int j=0; j<c; j++) {
                if(i>j) {
                    continue;
                }
                int l = i-1>=0 ? cuts[i-1] : 0;
                int r = j+1<c ? cuts[j+1] : n;
                int len = r-l;
                int ans = Integer.MAX_VALUE;
                for(int k=i; k<=j; k++) {
                    int tmp = len;
                    tmp += k-1>=0 ? dp[i][k-1] : 0;
                    tmp += k+1<c ? dp[k+1][j] : 0;
                    ans = Math.min(ans, tmp);
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][c-1];
    }

    static int maxCoinsUtil(int[] nums, int i, int j, int[][] dp) {
        if(i>j) {
            return 0;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        int n = nums.length;
        int l = i-1>=0 ? nums[i-1] : 1;
        int r = j+1<n ? nums[j+1] : 1;
        int ans = Integer.MIN_VALUE;
        for(int k=i; k<=j; k++) {
            int cost = l*r*nums[k] + maxCoinsUtil(nums, i, k-1, dp) + maxCoinsUtil(nums, k+1, j, dp);
            ans = Math.max(ans, cost);
        }
        return dp[i][j] = ans;
    }
    static int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n+1][n+1];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return maxCoinsUtil(nums, 0, n-1, dp);

        for(int i=n-1; i>=0; i--) {
            for(int j=i; j<n; j++) {
                int l = i-1>=0 ? nums[i-1] : 1;
                int r = j+1<n ? nums[j+1] : 1;
                int ans = Integer.MIN_VALUE;
                for(int k=i; k<=j; k++) {
                    int cost = l*r*nums[k];
                    cost += k-1>=0 ? dp[i][k-1] : 0;
                    cost += k+1<n ? dp[k+1][j] : 0;
                    ans = Math.max(ans, cost);
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        // int[] arr = {1, 2, 3, 4, 3};
        // System.out.println(matrixMultiplication(arr));

        // String s = "T|T&F^T";
        // System.out.println(countWays(s));

        // String s = "aab";
        // System.out.println(minCut(s));

        // int[] arr = {1,4,1,5,7,3,6,1,9,9,3};
        // int k = 4;
        // System.out.println(maxSumAfterPartitioning(arr, k));

        // int n = 9;
        // int[] cuts = {5, 6, 1, 4, 2};
        // System.out.println(minCost(n, cuts));

        int[] nums = {3, 1, 5, 8};
        System.out.println(maxCoins(nums));

    }
}

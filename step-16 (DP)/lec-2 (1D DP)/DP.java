
public class DP {
    static int climbStairs(int n) {
        if(n<=3) {
            return n;
        }
        int prev2 = 2;
        int prev = 3;
        for(int i=4; i<=n; i++) {
            int curr = prev + prev2;
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    static int frogJump(int[] height) {
        int n = height.length;
        if(n<=1) {
            return 0;
        }
        int[] dp = new int[n];
        dp[1] = Math.abs(height[1]-height[0]);
        for(int i=2; i<n; i++) {
            dp[i] = Math.min(dp[i-1]+Math.abs(height[i-1]-height[i]), dp[i-2]+Math.abs(height[i-2]-height[i]));
        }
        return dp[n-1];
    }

    static int frogJumpWithK(int[] heights, int k) {
        int n = heights.length;
        int[] dp = new int[n];
        for(int i=1; i<n; i++) {
            int curr = Integer.MAX_VALUE;
            for(int j=1; j<=k; j++) {
                if(i-j>=0) {
                    curr = Math.min(curr, dp[i-j]+Math.abs(heights[i]-heights[i-j]));
                }
            }
            dp[i] = curr;
        }
        return dp[n-1];
    }

    static int houseRobber(int[] nums) {
        int n = nums.length;
        if(n==0) {
            return 0;
        }
        if(n==1) {
            return nums[0];
        }
        if(n==2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i=2; i<n; i++) {
            dp[i] = Math.max(nums[i]+dp[i-2], dp[i-1]);
        }
        return dp[n-1];
    }

    static int houseRobber2(int[] nums) {
        int n = nums.length;
        if(n==0) {
            return 0;
        }
        if(n==1) {
            return nums[0];
        }
        if(n==2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp1 = new int[n-1];
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);
        for(int i=2; i<n-1; i++) {
            dp1[i] = Math.max(nums[i]+dp1[i-2], dp1[i-1]);
        }

        int[] dp2 = new int[n-1];
        dp2[0] = nums[1];
        dp2[1] = Math.max(nums[2], nums[1]);
        for(int i=2; i<n-1; i++) {
            dp2[i] = Math.max(nums[i+1]+dp2[i-2], dp2[i-1]);
        }

        return Math.max(dp1[n-2], dp2[n-2]);
    }

    public static void main(String[] args) {
        // System.out.println(climbStairs(6));

        // int[] heights = {30, 20, 50, 10, 40};
        // System.out.println(frogJump(heights));

        int[] heights = {15, 4, 1, 14, 15};
        System.out.println(frogJumpWithK(heights, 3));

        // int[] nums = {2, 1, 1, 2};
        // System.out.println(houseRobber(nums));

        // int[] nums2 = {1, 2, 3, 1};
        // System.out.println(houseRobber2(nums2));
        
    }
}

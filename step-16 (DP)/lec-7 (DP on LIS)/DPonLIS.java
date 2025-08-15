import java.util.*;

public class DPonLIS {
    static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 1;
        for(int i=0; i<n; i++) {
            for(int j=i-1; j>=0; j--) {
                if(nums[j]<nums[i] && (1+dp[j])>dp[i]) {
                    dp[i] = 1+dp[j];
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    static ArrayList<Integer> printLIS(int arr[]) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] lastInd = new int[n];
        int max = 0, lastIndex = -1;
        for(int i=0; i<n; i++) {
            lastInd[i] = i;
            for(int j=0; j<i; j++) {
                if(arr[j]<arr[i] && (1+dp[j])>dp[i]) {
                    dp[i] = 1+dp[j];
                    lastInd[i] = j;
                }
            }
            if(dp[i]>max) {
                max = dp[i];
                lastIndex = i;
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(arr[lastIndex]);
        while(lastInd[lastIndex] != lastIndex) {
            lastIndex = lastInd[lastIndex];
            ans.add(arr[lastIndex]);
        }
        Collections.reverse(ans);
        return ans;
    }

    static int lowerBound(List<Integer> list, int x) {
        int l=0, r=list.size()-1;
        while(l <= r) {
            int mid = (l+r)/2;
            if(x <= list.get(mid)) {
                r = mid-1;
            }
            else {
                l = mid+1;
            }
        }
        return r+1;
    }
    static int lisWithBS(int[] nums) {
        int n = nums.length;
        List<Integer> seq = new ArrayList<>();
        for(int i=0; i<n; i++) {
            int sz = seq.size();
            if(sz==0 || seq.get(sz-1)<nums[i]) {
                seq.add(nums[i]);
            }
            else {
                int lb = lowerBound(seq, nums[i]);
                seq.set(lb, nums[i]);
            }
        }
        return seq.size();
    }

    static List<Integer> largestDivisibleSubset(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] lastInd = new int[n];
        int max = 0, lastIndex = -1;
        for(int i=0; i<n; i++) {
            lastInd[i] = i;
            for(int j=0; j<i; j++) {
                if(arr[i]%arr[j]==0 && (1+dp[j])>dp[i]) {
                    dp[i] = 1+dp[j];
                    lastInd[i] = j;
                }
            }
            if(dp[i]>max) {
                max = dp[i];
                lastIndex = i;
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        ans.add(arr[lastIndex]);
        while(lastInd[lastIndex] != lastIndex) {
            lastIndex = lastInd[lastIndex];
            ans.add(arr[lastIndex]);
        }
        Collections.reverse(ans);
        return ans;
    }

    static boolean isValid(String s1, String s2) {
        int l1=s1.length(), l2=s2.length();
        if(l1+1 != l2) {
            return false;
        }
        int i=0, j=0;
        while(i<l1 && j<l2) {
            if(s1.charAt(i)==s2.charAt(j)) {
                i++;
                j++;
            }
            else {
                j++;
            }
        }
        return i==l1 && (j==l2-1|| j==l2);
    }
    static int longestStrChain(String[] words) {
        int n = words.length;
        Arrays.sort(words, (a,b)->a.length()-b.length());
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(isValid(words[j], words[i]) && dp[i]<1+dp[j]) {
                    dp[i] = 1+dp[j];
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    static int LongestBitonicSequence(int n, int[] nums) {
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);
        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(nums[j]<nums[i] && dp1[i]<1+dp1[j]) {
                    dp1[i] = 1+dp1[j];
                }
            }
        }
        int ans = 0;
        for(int i=n-1; i>=0; i--) {
            for(int j=n-1; j>i; j--) {
                if(nums[j]<nums[i] && dp2[i]<1+dp2[j]) {
                    dp2[i] = 1+dp2[j];
                }
            }
            if(dp1[i]>1 && dp2[i]>1) {
                ans = Math.max(ans, dp1[i]+dp2[i]-1);
            }
        }
        return ans;
    }

    static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(cnt, 1);
        int mx=0, ans=0;
        for(int i=0; i<n; i++) {
            for(int j=i-1; j>=0; j--) {
                if(nums[j]<nums[i] && dp[i]<1+dp[j]) {
                    dp[i] = 1+dp[j];
                    cnt[i] = cnt[j];
                }
                else if(nums[j]<nums[i] && dp[i]==1+dp[j]) {
                    cnt[i] += cnt[j];
                }
            }
            if(dp[i] > mx) {
                mx = dp[i];
            }
        }
        for(int i=0; i<n; i++) {
            if(dp[i]==mx) {
                ans += cnt[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        // System.out.println(lengthOfLIS(nums));
        // System.out.println(lisWithBS(nums));

        // int[] arr = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        // System.out.println(printLIS(arr));

        // int[] nums = {16, 8, 2, 4, 32};
        // System.out.println(largestDivisibleSubset(nums));

        // String[] words = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
        // System.out.println(longestStrChain(words));

        // int[] nums = {1, 4, 2, 7, 9, 10};
        // int n = nums.length;
        // System.out.println(LongestBitonicSequence(n, nums));

        int[] nums = {1, 3, 5, 4, 7};
        System.out.println(findNumberOfLIS(nums));
    }
}

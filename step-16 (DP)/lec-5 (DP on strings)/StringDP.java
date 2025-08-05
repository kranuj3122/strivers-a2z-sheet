import java.util.*;

public class StringDP {
    static int longestCommonSubsequenceHelper(String s1, String s2, int m, int n, int[][] dp) {
        if(m==0 || n==0) {
            return 0;
        }
        if(dp[m][n] != -1) {
            return dp[m][n];
        }

        if(s1.charAt(m-1) == s2.charAt(n-1)) {
            return dp[m][n] = 1+longestCommonSubsequenceHelper(s1, s2, m-1, n-1, dp);
        }

        return dp[m][n] = Math.max(longestCommonSubsequenceHelper(s1, s2, m-1, n, dp), longestCommonSubsequenceHelper(s1, s2, m, n-1, dp));
    }
    static int lcs(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return longestCommonSubsequenceHelper(text1, text2, m, n, dp);
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = 1+dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[m][n];
    }

    static String printLCS(String s1, String s2){
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(s1.charAt(i-1)==s2.charAt(j-1)) {
                    dp[i][j] = 1+dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int i=n, j=m;
        StringBuilder sb = new StringBuilder();
        while(i>0 && j>0) {
            if(s1.charAt(i-1)==s2.charAt(j-1)) {
                sb.append(s1.charAt(i-1));
                i--;
                j--;
            }
            else if(dp[i-1][j] > dp[i][j-1]) {
                i--;
            }
            else {
                j--;
            }
        }

        return sb.reverse().toString();
    }

    static int longestCommonSubstrHelper(String s1, String s2, int m, int n, int[][] dp) {
        if(m==0 || n==0 || s1.charAt(m-1)!=s2.charAt(n-1)) {
            return dp[m][n] = 0;
        }
        if(dp[m][n] != -1) {
            return dp[m][n];
        }
        return dp[m][n] = 1+longestCommonSubstrHelper(s1, s2, m-1, n-1, dp);
    }
    static int longestCommonSubstr(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        int ans = 0;
        // Recursive
        for(int[] r: dp) {
            Arrays.fill(r, -1);
        }
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                ans = Math.max(ans, longestCommonSubstrHelper(s1, s2, i, j, dp));
            }
        }
        return ans;
        
        // for(int i=1; i<=m; i++) {
        //     for(int j=1; j<=n; j++) {
        //         if(s1.charAt(i-1) == s2.charAt(j-1)) {
        //             dp[i][j] = 1+dp[i-1][j-1];
        //         }
        //         ans = Math.max(ans, dp[i][j]);
        //     }
        // }
        // return ans;
    }

    static String reverse(String s) {
        String rev = "";
        for(int i=s.length()-1; i>=0; i--) {
            rev += s.charAt(i);
        }
        return rev;
    }
    static int longestPalindromeSubseq(String s) {
        String rev = reverse(s);
        return lcs(s, rev);
    }

    static int minInsertions(String s) {
        return s.length()-longestPalindromeSubseq(s);
    }

    static int minDistance(String word1, String word2) {
        return word1.length() + word2.length() - (2 * lcs(word1, word2));
    }

    static String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int i=m, j=n;
        StringBuilder sb = new StringBuilder();
        while(i>0 && j>0) {
            if(str1.charAt(i-1)==str2.charAt(j-1)) {
                sb.append(str1.charAt(i-1));
                i--;
                j--;
            }
            else if(dp[i-1][j] > dp[i][j-1]) {
                sb.append(str1.charAt(i-1));
                i--;
            }
            else {
                sb.append(str2.charAt(j-1));
                j--;
            }
        }
        while(i > 0) {
            sb.append(str1.charAt(i-1));
            i--;
        }
        while(j > 0) {
            sb.append(str2.charAt(j-1));
            j--;
        }

        return sb.reverse().toString();
    }

    static int numDistinctHelper(String s, String t, int m, int n, int[][] dp) {
        if(n==0) {
            return 1;
        }
        if(m==0) {
            return 0;
        }
        if(dp[m][n] != -1) {
            return dp[m][n];
        }

        if(s.charAt(m-1) == t.charAt(n-1)) {
            return dp[m][n] = numDistinctHelper(s, t, m-1, n-1, dp) + numDistinctHelper(s, t, m-1, n, dp);
        }
        return dp[m][n] = numDistinctHelper(s, t, m-1, n, dp);
    }
    static int numDistinct(String s, String t) {
        // no. of distinct subsequences of t in s
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m+1][n+1];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return numDistinctHelper(s, t, m, n, dp);
        for(int i=0; i<=m; i++) {
            dp[i][0] = 1;
        }
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                dp[i][j] = dp[i-1][j];
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] += dp[i-1][j-1];
                }
            }
        }
        return dp[m][n];
    }

    static int minEditDistanceHelper(String s, String t, int m, int n, int[][] dp) {
        if(m==0) {
            return n;
        }
        if(n==0) {
            return m;
        }
        if(dp[m][n] != -1) {
            return dp[m][n];
        }
        if(s.charAt(m-1) == t.charAt(n-1)) {
            return dp[m][n] = minEditDistanceHelper(s, t, m-1, n-1, dp);
        }

        return dp[m][n] = 1 + Math.min(minEditDistanceHelper(s,t,m, n-1, dp), Math.min(minEditDistanceHelper(s,t,m-1, n, dp), minEditDistanceHelper(s,t,m-1, n-1, dp)));
    }
    static int minEditDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return solve(word1, word2, m, n, dp);
        for(int i=0; i<=m; i++) {
            dp[i][0] = i;
        }
        for(int j=0; j<=n; j++) {
            dp[0][j] = j;
        }

        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(word1.charAt(i-1)==word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = 1+Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1]));
                }
            }
        }
        return dp[m][n];
    }

    static int isMatchHelper(String s, String p, int m, int n, int[][] dp) {
        if(m==0) {
            while(n > 0) {
                if(p.charAt(n-1)!='*') {
                    return 0;
                }
                n--;
            }
            return 1;
        }
        if(n==0) {
            return 0;
        }
        if(dp[m][n] != -1) {
            return dp[m][n];
        }
        if(s.charAt(m-1)==p.charAt(n-1) || p.charAt(n-1)=='?') {
            return dp[m][n] = isMatchHelper(s, p, m-1, n-1, dp);
        }
        if(p.charAt(n-1)=='*') {
            return dp[m][n] = isMatchHelper(s, p, m, n-1, dp) | isMatchHelper(s, p, m-1, n, dp); 
        }
        return dp[m][n] = 0;
    }
    static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // int[][] dp = new int[m+1][n+1];
        // for(int[] r: dp) {
        //     Arrays.fill(r, -1);
        // }
        // return isMatchHelper(s, p, m, n, dp)==1;

        boolean[][] dp = new boolean[m+1][n+1];
        for(int i=0; i<=n; i++) {
            dp[0][i] = true;
            int j = i;
            while(j >= 1) {
                if(p.charAt(j-1) != '*') {
                    dp[0][i] = false;
                    break;
                }
                j--;
            }
        }

        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='?') {
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(p.charAt(j-1)=='*') {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j]; 
                }
                else {
                    dp[m][n] = false;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        // String text1 = "abcde";
        // String text2 = "acbd";
        // System.out.println(lcs(text1, text2));
        // System.out.println(printLCS(text1, text2));

        // String s1 = "ABCDGH";
        // String s2 = "ACDGHR";
        // System.out.println(longestCommonSubstr(s1, s2));

        // String s = "bbbab";
        // System.out.println(longestPalindromeSubseq(s));

        // String s = "leetcode";
        // System.out.println(minInsertions(s));

        // String word1 = "sea";
        // String word2 = "eat";
        // System.out.println(minDistance(word1, word2));

        // String str1 = "abac";
        // String str2 = "cab";
        // System.out.println(shortestCommonSupersequence(str1, str2));

        // String s = "babgbag";
        // String t = "bag";
        // System.out.println(numDistinct(s, t));

        // String word1 = "intention";
        // String word2 = "execution";
        // System.out.println(minEditDistance(word1, word2));

        String str = "xaylmz";
        String pat = "x?y*z";
        System.out.println(isMatch(str, pat));
    }
}

import java.util.*;

public class EasyProblem {
    static int findContentChildren(int[] g, int[] s) {
        // 455 - Assign Cookies (https://leetcode.com/problems/assign-cookies/)
        /**
         * Approach:
         * 1. Sort both arrays: g (children's greed factors) and s (cookie sizes).
         * 2. Find the largest index j in g such that g[j] <= largest cookie size.
         * 3. Iterate from g[j] down to g[0], and for each, try to assign the largest available cookie.
         * 4. For each match, decrement the cookie pointer and increment the answer.
         */
        if(s.length==0) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int j = -1;
        int ans = 0;
        for(int i=0; i<g.length; i++) {
            if(g[i]<=s[s.length-1]) {
                j=i;
            }
        }
        int last = s.length-1;
        for(int i=j; i>=0; i--) {
            if(last>=0 && g[i]<=s[last]) {
                ans++;
                last--;
            }
        }
        return ans;
    }

    static double fractionalKnapsack(int[] values, int[] weights, int W) {
        /*
         * Approach:
         * 1. Sort the items based on their value-to-weight ratio (values[i]/weights[i]) in descending order.
         * 2. Iterate through the sorted items:
         *    - If the remaining knapsack capacity W is greater than or equal to the item's weight, take the whole item:
         *          ans += values[idx[i]];
         *          W -= weights[idx[i]];
         *    - Otherwise, take the fractional part of the item that fits:
         *          ans += (values[idx[i]]/(double)weights[idx[i]]) * W;
         *          break;
         * 3. Return the total value accumulated in ans.
         */
        int n = values.length;
        Integer[] idx = new Integer[n];
        for(int i=0; i<n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i,j)->Double.compare(values[i]/(double)weights[i], values[j]/(double)weights[j]));
        double ans = 0.0;
        for(int i=n-1; i>=0; i--) {
            if(W>=weights[idx[i]]) {
                ans += values[idx[i]];
                W -= weights[idx[i]];
            }
            else {
                ans += (values[idx[i]]/(double)weights[idx[i]])*W;
                break;
            }
            
        }
        return ans;
    }

    static List<Integer> minPartition(int N) {
        /*
         * Approach: (Coin change - Min no. of coins, Greedy)
         * 1. Create a list to store the result.
         * 2. Iterate over the currency denominations from largest to smallest:
         *      - For each denomination, while it fits into N, add it to the result and subtract from N.
         *      - Continue until N becomes 0.
         * 3. Return the list containing the minimum number of coins/notes.
         */
        int[] arr = {1,2,5,10,20,50,100,200,500,2000};
        List<Integer> list = new ArrayList<>();
        for(int i=arr.length-1; i>=0; i--) {
            if(N >= arr[i]) {
                int cnt = N/arr[i];
                while(cnt-- > 0) {
                    list.add(arr[i]);
                }
                N %= arr[i];
            }
        }
        return list;
    }

    static boolean lemonadeChange(int[] bills) {
        // 860 - Lemonade Change (https://leetcode.com/problems/lemonade-change/description/)

        /*
         * Approach:
         * 1. Maintain counters for $5 and $10 bills.
         * 2. For each bill:
         *    - If it's $5, increment the $5 counter.
         *    - If it's $10, check for a $5 bill for change; if available, decrement $5 and increment $10.
         *    - If it's $20, prefer giving one $10 and one $5 as change if possible; otherwise, give three $5 bills.
         * 3. If at any point change cannot be given, return false.
         */
        int five=0, ten=0;
        for(int n: bills) {
            switch(n) {
                case 5:
                    five++;
                    break;
                case 10:
                    if(five==0) {
                        return false;
                    }
                    five--;
                    ten++;
                    break;
                case 20:
                    if(ten>0 && five>0) {
                        ten--;
                        five--;
                    }
                    else if(five>=3) {
                        five -= 3;
                    }
                    else {
                        return false;
                    }
            }
        }
        return true;
    }

    static boolean solve(String s, int ind, int cnt, Map<String, Boolean> mp) {
        if(cnt < 0) {
            return false;
        }
        if(ind == s.length()) {
            return cnt==0;
        }
        String key = ind+"*"+cnt;
        if(mp.containsKey(key)) {
            return mp.get(key);
        }
        char c = s.charAt(ind);
        if(c=='(') {
            boolean val = solve(s, ind+1, cnt+1, mp);
            mp.put(key, val);
            return val;
        }
        else if(c==')') {
            boolean val = solve(s, ind+1, cnt-1, mp);
            mp.put(key, val);
            return val;
        }
        boolean val = solve(s, ind+1, cnt-1, mp) || solve(s, ind+1, cnt+1, mp) || solve(s, ind+1, cnt, mp);
        mp.put(key, val);
        return val;
    }
    static boolean checkValidString(String s) {
        /*
         * Approach:
         * 1. Use recursion with memoization to check if the string can be a valid parenthesis string.
         * 2. At each character:
         *    - If '(', increment the count of open brackets.
         *    - If ')', decrement the count.
         *    - If '*', try all possibilities: treat as '(', ')', or empty.
         * 3. Use a map to memoize results for (index, count) pairs to avoid recomputation.
         * 4. The string is valid if, at the end, the `count of open brackets` is *zero*.
         */
        Map<String, Boolean> mp = new HashMap<>();
        return solve(s, 0, 0, mp);
    }


    public static void main(String[] args) {
        // int[] g = {10, 9, 8, 7};
        // int[] s = {5, 6, 7, 8};
        // System.out.println(findContentChildren(g, s));

        // int[] val = {60, 100, 120};
        // int[] wt = {10, 20, 30};
        // int capacity = 50;
        // System.out.println(fractionalKnapsack(val, wt, capacity));

        // int N = 43;
        // System.out.println(minPartition(N));

        // int[] bills = {5, 5, 5, 10, 20};
        // System.out.println(lemonadeChange(bills));

        String s = "((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()";
        System.out.println(checkValidString(s));
    }
}

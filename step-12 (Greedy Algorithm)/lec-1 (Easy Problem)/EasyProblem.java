import java.util.*;
import java.util.Arrays;

public class EasyProblem {
    static int findContentChildren(int[] g, int[] s) {
        // 455 - Assign Cookies
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
        int n = values.length;
        Integer[] idx = new Integer[n];
        for(int i=0; i<n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i,j)->Double.compare(values[i]/(double)weights[i], values[j]/(double)weights[j]));
        double ans = 0.0;
        for(int i=n-1; i>=0; i--) {
            // System.out.println(values[idx[i]]+", "+weights[idx[i]]);
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

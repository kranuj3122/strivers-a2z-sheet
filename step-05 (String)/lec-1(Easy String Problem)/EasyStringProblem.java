import java.util.*;

public class EasyStringProblem {
    static String removeOuterParentheses(String s) {
        String ans="";
        int leftCount=0;
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c=='(') {
                if(leftCount>=1) {
                    ans += '(';
                }
                leftCount++;
            }
            else {
                if(leftCount>1) {
                    ans += ')';
                }
                leftCount--;
            }
        }
        return ans;
    }

    static String reverseWords(String s) {
        s = s.trim();
        int n=s.length();
        String ans="";
        int last = n;
        for(int i=n-1; i>=0; i--) {
            if(s.charAt(i)==' ') {
                ans += s.substring(i+1, last)+" ";
                while(i>0 && s.charAt(i-1)==' ') {
                    i--;
                }
                last = i;
            }
        }
        ans += s.substring(0, last);
        return ans;
    }

    static String largestOddNumber(String num) {
        int oddIdx = -1;
        for(int i=0; i<num.length(); i++) {
            if((num.charAt(i)-'0')%2 == 1) {
                oddIdx = i;
            }
        }
        return oddIdx==-1 ? "" : num.substring(0, oddIdx+1);
    }

    static String prefixLength(String s1, String s2) {
        int n1=s1.length(), n2=s2.length();
        int n=Math.min(n1, n2);
        String str = "";
        for(int i=0; i<n; i++) {
            if(s1.charAt(i)!=s2.charAt(i)) {
                return str;
            }
            str += s1.charAt(i);
        }
        return str;
    }
    static String longestCommonPrefix(String[] strs) {
        int n=strs.length;
        if(n==1) {
            return strs[0];
        }
        String ans = prefixLength(strs[0], strs[1]);
        for(int i=2; i<n; i++) {
            String tmp = prefixLength(strs[i], strs[i-1]);
            if(tmp.length()<ans.length()) {
                ans = tmp;
            }
        }
        return ans;
    }

    static boolean isIsomorphic(String s, String t) {
        Map<Character, Character> mp = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            char c1=s.charAt(i), c2=t.charAt(i);
            if(mp.containsKey(c1)) {
                if(mp.get(c1)!=c2) {
                    return false;
                }
            }
            else if(mp.containsValue(c2)) {
                return false;
            }
            else {
                mp.put(c1, c2);
            }
        }
        return true;
    }

    static boolean rotateString(String s, String goal) {
        int n1=s.length(), n2=goal.length();
        if(n1!=n2) {
            return false;
        }
        if(s.equals(goal)) {
            return true;
        }
        for(int i=0; i<=n1-2; i++) {
            String temp = s.substring(i+1, n1)+s.substring(0, i+1);
            if(temp.equals(goal)) {
                return true;
            }
        }
        return false;
    }

    static boolean isAnagram(String s, String t) {
        int n1=s.length(), n2=t.length();
        if(n1!=n2) {
            return false;
        }
        int[] freq = new int[26];
        for(int i=0; i<n1; i++) {
            freq[s.charAt(i)-'a']++;
            freq[t.charAt(i)-'a']--;
        }
        for(int f: freq) {
            if(f!=0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // String s = "(()())(())(()(()))";
        // System.out.println(removeOuterParentheses(s));

        // String s = "  a good     example  ";
        // System.out.println(reverseWords(s));

        // System.out.println(largestOddNumber("3542720"));

        // String[] strs = {"flower", "flow", "flight"};
        // System.out.println(longestCommonPrefix(strs));

        // String s = "paper", t = "title";
        // System.out.println(isIsomorphic(s, t));

        // String s = "abcde", goal = "cdeab";
        // System.out.println(rotateString(s, goal));

        String s = "anagram", t = "nagaram";
        System.out.println(isAnagram(s, t));
        
    }
}

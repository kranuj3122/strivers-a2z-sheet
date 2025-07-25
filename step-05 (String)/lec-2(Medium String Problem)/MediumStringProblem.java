import java.util.*;

public class MediumStringProblem {

    static String frequencySort(String s) {
        int[][] mp = new int[128][2];
        for(int i=0; i<128; i++) {
            mp[i][1]=i;
        }
        int n = s.length();
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            mp[c-'0'][0]++;
        }
        Arrays.sort(mp, (a,b)->b[0]-a[0]);
        String ans="";
        for(int[] m: mp) {
            if(m[0]==0) {
                break;
            }
            while(m[0]-- > 0) {
                ans += (char)(m[1]+'0');
            }
        }
        return ans;
    }

    static int maxDepth(String s) {
        int leftCount=0;
        int ans=0;
        for(char c: s.toCharArray()) {
            if(c=='(') {
                leftCount++;
            }
            else if(c==')') {
                leftCount--;
            }
            ans = Math.max(ans, leftCount);
        }
        return ans;
    }

    static int countSubstring(String s) {
        int[] minIndex = new int[3];
        Arrays.fill(minIndex, -1);
        int n=s.length();
        int j=0;
        int ans=0;
        while(j<n) {
            minIndex[s.charAt(j)-'a'] = j;
            int mnIdx = Math.min(minIndex[0], Math.min(minIndex[1], minIndex[2]));
            if(mnIdx != -1) {
                ans += (mnIdx+1);
            }
            j++;
        }
        return ans;
    }

    static int romanToInt(String s) {
        Map<Character, Integer> mp = new HashMap<>();
        mp.put('I', 1);
        mp.put('V', 5);
        mp.put('X', 10);
        mp.put('L', 50);
        mp.put('C', 100);
        mp.put('D', 500);
        mp.put('M', 1000);
        int ans = 0;
        int n = s.length();
        for(int i=n-1; i>=0; i--) {
            int val = mp.get(s.charAt(i));
            int r = i<n-1 ? mp.get(s.charAt(i+1)) : 0;
            if(r>val) {
                ans -= val;
            }
            else {
                ans += val;
            }
        }
        return ans;
    }

    static String intToRoman(int num) {
        int[] val = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] sym = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        String ans = "";
        for(int i=12; i>=0; i--) {
            while(num >= val[i]) {
                ans += sym[i];
                num -= val[i];
            }
        }
        return ans;
    }

    static int myAtoi(String s) {
        s = s.trim();
        int n=s.length();
        long l = 0L;
        char sign = '+';
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            if(i==0 && c=='-') {
                sign = '-';
            }
            else if(i==0 && c=='+') {
                continue;
            }
            else if(c<'0' || c>'9') {
                break;
            }
            else {
                l = 10L*l + (c-'0');
            }
            if(l>Integer.MAX_VALUE) {
                return sign=='-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }
        if(sign=='-') {
            l *= -1;
        }
        return (int)l;
    }

    static int beautySum(String s) {
        int n = s.length();
        int ans = 0;
        for(int i=0; i<n; i++) {
            int[] freq = new int[26];
            for(int j=i; j<n; j++) {
                char c = s.charAt(j);
                freq[c-'a']++;
                int mx=0, mn=1001;
                for(int f: freq) {
                    if(f>0) {
                        mx = Math.max(mx, f);
                        mn = Math.min(mn, f);
                    }
                }
                ans += (mx-mn);
            }
        }
        return ans;
    }

    static boolean isPalindromic(String s) {
        int i=0, j=s.length()-1;
        while(i<j) {
            if(s.charAt(i)!=s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    static String longestPalindrome(String s) {
        String ans="";
        int mxLen=0;
        int n=s.length();
        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {
                String subStr = s.substring(i, j+1);
                int n1=j-i+1;
                if(isPalindromic(subStr) && n1>mxLen) {
                    mxLen = n1;
                    ans = subStr;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // System.out.println(frequencySort("tree"));

        // System.out.println(maxDepth("(1)+((2))+(((3)))"));

        // System.out.println(countSubstring("abcabc"));

        // System.out.println(romanToInt("MCMXCIV"));

        // System.out.println(intToRoman(3749));

        // System.out.println(myAtoi("-91283472332"));

        // System.out.println(beautySum("aabcbaa"));

        System.out.println(longestPalindrome("babad"));
    }
    
}

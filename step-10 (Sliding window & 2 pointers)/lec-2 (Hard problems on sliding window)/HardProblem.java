import java.util.*;

public class HardProblem {
    static int longestKSubstr(String s, int k) {
        // Longest Substring with K Uniques character
        int n = s.length();
        int l=0, r=0;
        int ans = -1;
        Map<Character, Integer> mp = new HashMap<>();
        while(r < n) {
            char c = s.charAt(r);
            mp.put(c, mp.getOrDefault(c, 0)+1);
            if(mp.size() == k) {
                ans = Math.max(ans, r-l+1);
            }
            while(mp.size() > k) {
                char c2 = s.charAt(l);
                mp.put(c2, mp.get(c2)-1);
                if(mp.get(c2)==0) {
                    mp.remove(c2);
                }
                l++;
            }
            r++;
        }
        return ans;
    }

    static int numberOfSubArrayWithAtMostKDistinct(int[] nums, int k) {
        int n = nums.length;
        int l=0, r=0;
        int cnt = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        while(r < n) {
            mp.put(nums[r], mp.getOrDefault(nums[r], 0)+1);
            while(mp.size() > k) {
                mp.put(nums[l], mp.get(nums[l])-1);
                if(mp.get(nums[l])==0) {
                    mp.remove(nums[l]);
                }
                l++;
            }
            cnt += (r-l+1);
            r++;
        }
        return cnt;
    }
    static int subarraysWithKDistinct(int[] nums, int k) {
        return numberOfSubArrayWithAtMostKDistinct(nums, k) - numberOfSubArrayWithAtMostKDistinct(nums, k-1);
    }

    static String minWindow(String s, String t) {
        int[] freq = new int[256];
        for(char c: t.toCharArray()) {
            freq[c]++;
        }
        int m = t.length();
        int n = s.length();
        int l=0, r=0, cnt=0;
        int ans = (int)1e6;
        int startInd = -1;
        while(r < n) {
            if(freq[s.charAt(r)]>0) {
                cnt++;
            }
            freq[s.charAt(r)]--;
            while(cnt==m) {
                int len = r-l+1;
                if(ans > len) {
                    ans = len;
                    startInd = l;
                }
                freq[s.charAt(l)]++;
                if(freq[s.charAt(l)]>0) {
                    cnt--;
                }
                l++;
            }
            r++;
        }
        if(startInd == -1) {
            return "";
        }
        return s.substring(startInd, startInd+ans);
    }

    static String minWindowSubsequence(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int minLen = (int)1e5;
        String ans = "";
        
        for(int i=0; i<n; i++) {
            if(s1.charAt(i)==s2.charAt(0)) {
                int ind1=i, ind2=0;
                while(ind1<n && ind2<m) {
                    if(s1.charAt(ind1)==s2.charAt(ind2)) {
                        ind2++;
                    }
                    ind1++;
                }
                
                if(ind2==m) {
                    ind1--;
                    ind2--;
                    int end=ind1;
                    while(ind2>=0) {
                        if(s1.charAt(ind1)==s2.charAt(ind2)) {
                            ind2--;
                        }
                        ind1--;
                    }
                    
                    int start = ind1+1;
                    int len = end-start+1;
                    if(len < minLen) {
                        minLen = len;
                        ans = s1.substring(start, start+len);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // String s = "aabaaab";
        // System.out.println(longestKSubstr(s, 2));

        // int[] nums = {1, 2, 1, 2, 3};
        // System.out.println(subarraysWithKDistinct(nums, 2));

        // String s = "ADOBECODEBANC";
        // String t = "ABC";
        // System.out.println(minWindow(s, t));

        String s1 = "geeksforgeeks";
        String s2 = "eksrg";
        System.out.println(minWindowSubsequence(s1, s2));
    }
}

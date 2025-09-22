import java.util.*;

public class HardProblem {
    /**
     * Approach:
     * 1. Use sliding window technique with two pointers (l, r).
     * 2. Use a HashMap to track the frequency of characters in the current window.
     * 3. Expand the window by moving 'r' pointer and add characters to the map.
     * 4. When the map size equals k (exactly k unique characters), update the answer.
     * 5. When the map size exceeds k, shrink the window by moving 'l' pointer:
     *    - Remove characters from the left until we have at most k unique characters.
     *    - Decrement frequency and remove from map when frequency becomes 0.
     * 6. Return the maximum length of substring with exactly k unique characters.
     * 7. Time Complexity: O(n)
     * 8. Space Complexity: O(k) - at most k+1 entries in map
     * Leetcode Link: https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
     */
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

    /**
     * Approach:
     * 1. Use sliding window technique with two pointers (l, r).
     * 2. Use a HashMap to track the frequency of elements in the current window.
     * 3. Expand the window by moving 'r' pointer and add elements to the map.
     * 4. When the map size exceeds k, shrink the window by moving 'l' pointer:
     *    - Remove elements from the left until we have at most k distinct elements.
     *    - Decrement frequency and remove from map when frequency becomes 0.
     * 5. For each valid window, add (r-l+1) to count - this represents all subarrays ending at r.
     * 6. Key insight: For a window [l, r], there are (r-l+1) subarrays ending at r with at most k distinct elements.
     * 7. Return the total count of subarrays with at most k distinct elements.
     * 8. Time Complexity: O(n)
     * 9. Space Complexity: O(k) - at most k+1 entries in map
     * Leetcode Link: https://leetcode.com/problems/subarrays-with-k-different-integers/
     */
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
    /**
     * Approach:
     * 1. Use the inclusion-exclusion principle with sliding window.
     * 2. Key insight: Count of subarrays with exactly k distinct elements = 
     *    (Count of subarrays with at most k distinct) - (Count of subarrays with at most k-1 distinct).
     * 3. Time Complexity: O(n) - calls helper function twice
     * 4. Space Complexity: O(k)
     * Leetcode Link: https://leetcode.com/problems/subarrays-with-k-different-integers/
     */
    static int subarraysWithKDistinct(int[] nums, int k) {
        return numberOfSubArrayWithAtMostKDistinct(nums, k) - numberOfSubArrayWithAtMostKDistinct(nums, k-1);
    }

    /**
     * Approach:
     * 1. Use sliding window technique with two pointers (l, r).
     * 2. Use a frequency array to track characters needed from string t.
     * 3. Initialize frequency array with positive counts for characters in t.
     * 4. Expand the window by moving 'r' pointer:
     *    - Decrement frequency for each character encountered.
     *    - Increment counter when we find a character from t (freq > 0).
     * 5. When counter equals t.length (all characters found), try to shrink window:
     *    - Update minimum window if current window is smaller.
     *    - Move 'l' pointer and increment frequency back.
     *    - Decrement counter when we lose a character from t.
     * 6. Return the minimum window substring containing all characters of t.
     * 7. Time Complexity: O(|s| + |t|)
     * 8. Space Complexity: O(256) = O(1)
     * Leetcode Link: https://leetcode.com/problems/minimum-window-substring/
     */
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

    /**
     * Approach:
     * 1. Use a two-pass technique to find the minimum window subsequence.
     * 2. For each possible starting position in s1 that matches s2[0]:
     *    - First pass (forward): Find the end position where s2 is found as subsequence.
     *    - Second pass (backward): Find the optimal start position for minimum window.
     * 3. Implementation:
     *    - Traverse s1 from left to right, matching characters of s2.
     *    - If s2 is completely matched, store the end position.
     *    - Traverse backward from end to find the minimum start position.
     *    - Calculate window length and update minimum if smaller.
     * 4. Key insight: We need to find the shortest contiguous subsequence that contains s2.
     * 5. Return the minimum window substring that contains s2 as a subsequence.
     * 6. Time Complexity: O(n * m) where n = |s1|, m = |s2|
     * 7. Space Complexity: O(1)
     * Leetcode Link: https://leetcode.com/problems/minimum-window-subsequence/
     */
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
                    // decrement ind1 and ind2 to get the last index of s2 in s1
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

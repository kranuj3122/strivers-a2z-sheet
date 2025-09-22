import java.util.*;

public class MediumProblem {
    /**
     * Approach:
     * 1. Use a frequency array to keep track of the characters in the current window.
     * 2. Use two pointers:
     *    a. i: start of the current window.
     *    b. j: end of the current window.
     * 3. If the character at index j is already in the window, move the start of the window to the right of the first occurrence of the character.
     * 4. Update the answer with the maximum length of the substring.
     * 5. Return the answer.
     * 6. Time Complexity: O(n)
     * 7. Space Complexity: O(256) = O(1)
     * Leetcode Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/
     */
    static int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256];
        int i=0, j=0, n=s.length();
        int ans = 0;
        while(j<n) {
            char c = s.charAt(j);
            freq[c]++;
            while(i<j && freq[c]>1) {
                freq[s.charAt(i)]--;
                i++;
            }
            ans = Math.max(ans, j-i+1);
            j++;
        }
        return ans;
    }

    /**
     * Approach:
     * 1. Use a HashMap to store the frequency of prefix sums encountered so far.
     * 2. Initialize the map with {0: 1} to handle the case where the subarray starts from index 0.
     * 3. Traverse through the array and maintain a running sum (currSum).
     * 4. For each element, check if (currSum - goal) exists in the map.
     *    - If it exists, it means there are subarrays ending at current index with sum = goal.
     *    - Add the frequency of (currSum - goal) to the answer.
     * 5. Update the map with the current sum frequency.
     * 6. Return the total count of subarrays with sum = goal.
     * 7. Time Complexity: O(n)
     * 8. Space Complexity: O(n) for the HashMap
     * Leetcode Link: https://leetcode.com/problems/binary-subarrays-with-sum/
     */
    static int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        int currSum = 0;
        int ans = 0;
        for(int num: nums) {
            currSum += num;
            int t = currSum-goal;
            if(mp.containsKey(t)) {
                ans += mp.get(t);
            }
            mp.put(currSum, mp.getOrDefault(currSum, 0)+1);
        }
        return ans;
    }

    /**
     * Approach:
     * 1. Use sliding window technique with two pointers (i, j).
     * 2. Maintain a counter 'z' to track the number of zeros in the current window.
     * 3. Expand the window by moving 'j' pointer:
     *    - If current element is 1, just move j forward.
     *    - If current element is 0 and we can still flip (z < k), increment z and move j.
     *    - If current element is 0 and we've reached the limit (z == k), update answer and shrink window.
     * 4. Shrink window by moving 'i' pointer until z <= k.
     * 5. Return the maximum length of subarray with at most k zeros. [ans = Math.max(ans, j-i)]
     * 6. Time Complexity: O(n)
     * 7. Space Complexity: O(1)
     * Leetcode Link: https://leetcode.com/problems/max-consecutive-ones-iii/
     */
    static int longestOnesIII(int[] nums, int k) {
        int n = nums.length;
        int i=0, j=0;
        int z=0;
        int ans = 0;
        while(j<n) {
            if(nums[j]==1) {
                j++;
                continue;
            }
            if(z<k) {
                j++;
                z++;
                continue;
            }
            z++;
            ans = Math.max(ans, j-i);
            while(i<=j && z>k) {
                if(nums[i]==0) {
                    z--;
                }
                i++;
            }
            j++;
        }
        return Math.max(ans, j-i);
    }
    /**
     * Approach:
     * 1. Use sliding window technique with two pointers (l, r).
     * 2. Expand the window by moving 'r' pointer and decrement k when encountering 0.
     * 3. When k becomes negative, shrink the window by moving 'l' pointer:
     *    - If the element at 'l' is 0, increment k back.
     *    - Move 'l' forward.
     * 4. Update the maximum window size at each valid position.
     * 5. Return the maximum length of subarray with at most k zeros.
     * 6. Time Complexity: O(n)
     * 7. Space Complexity: O(1)
     * Leetcode Link: https://leetcode.com/problems/max-consecutive-ones-iii/
     */
    static int longestOnes(int[] nums, int k) {
        int n=nums.length;
        int l=0, r=0;
        int ans = 0;
        while(r < n) {
            if(nums[r]==0) {
                k--;
            }
            if(k<0) {
                if(nums[l]==0) {
                    k++;
                }
                l++;
            }
            ans = Math.max(ans, r-l+1);
            r++;
        }
        return ans;
    }

    /**
     * Approach:
     * 1. Use sliding window technique with two pointers (l, r).
     * 2. Use a HashMap to track the frequency of fruits in the current window.
     * 3. Expand the window by moving 'r' pointer and add fruits to the map.
     * 4. When the map size exceeds 2 (more than 2 types of fruits):
     *    - Remove fruits from the left by moving 'l' pointer.
     *    - Decrement frequency and remove from map when frequency becomes 0.
     * 5. Update the maximum window size when we have at most 2 types of fruits.
     * 6. Return the maximum number of fruits that can be collected.
     * 7. Time Complexity: O(n)
     * 8. Space Complexity: O(1) - at most 3 entries in map
     * Leetcode Link: https://leetcode.com/problems/fruit-into-baskets/
     */
    static int totalFruit(int[] fruits) {
        int n=fruits.length;
        int l=0, r=0;
        int ans = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        while(r<n) {
            mp.put(fruits[r], mp.getOrDefault(fruits[r], 0)+1);
            if(mp.size() > 2) {
                mp.put(fruits[l], mp.get(fruits[l])-1);
                if(mp.get(fruits[l])==0) {
                    mp.remove(fruits[l]);
                }
                l++;
            }
            if(mp.size()<=2) {
                ans = Math.max(ans, r-l+1);
            }
            r++;
        }
        return ans;
    }

    static int maxElement(int[] nums) {
        int ans = 0;
        for(int n: nums) {
            ans = Math.max(ans, n);
        }
        return ans;
    }
    /**
     * Approach:
     * 1. Use sliding window technique with two pointers (l, r).
     * 2. Use a frequency array to track character counts in the current window.
     * 3. Maintain the maximum frequency of any character in the current window.
     * 4. Expand the window by moving 'r' pointer and update character frequency.
     * 5. Calculate the number of changes needed: (window_size - max_frequency).
     * 6. If changes exceed k, shrink the window by moving 'l' pointer.
     * 7. Update the maximum window size when changes <= k.
     * 8. Return the length of the longest substring with same character after k replacements.
     * 9. Time Complexity: O(n)
     * 10. Space Complexity: O(26) = O(1)
     * Leetcode Link: https://leetcode.com/problems/longest-repeating-character-replacement/
     */
    static int characterReplacement(String s, int k) {
        int n = s.length();
        int l=0, r=0;
        int[] freq = new int[26];
        int maxFreq = 0;
        int ans = 0;
        while(r < n) {
            freq[s.charAt(r)-65]++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(r)-65]);
            int changes = (r-l+1)-maxFreq;
            if(changes > k) {
                freq[s.charAt(l)-65]--;
                maxFreq = maxElement(freq);
                l++;
            }
            if(changes <= k) {
                ans = Math.max(ans, r-l+1);
            }
            r++;
        }
        return ans;
    }

    /**
     * Approach:
     * 1. Use sliding window technique with two pointers (l, r).
     * 2. Maintain a running sum to count odd numbers in the current window.
     * 3. Expand the window by moving 'r' pointer and add nums[r] % 2 to sum.
     * 4. When sum exceeds k, shrink the window by moving 'l' pointer.
     * 5. For each valid window, add (r-l+1) to count - this represents all subarrays ending at r.
     * 6. Return the total count of subarrays with at most k odd numbers.
     * 7. Time Complexity: O(n)
     * 8. Space Complexity: O(1)
     * Leetcode Link: https://leetcode.com/problems/count-number-of-nice-subarrays/
     */
    static int numberOfSubarraysWithAtMostKOdds(int[] nums, int k) {
        int n=nums.length;
        int l=0, r=0;
        int cnt = 0;
        int sum = 0;
        while(r < n) {
            sum += nums[r]%2;
            while(sum > k) {
                sum -= nums[l]%2;
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
     * 2. Count subarrays with exactly k odd numbers = (subarrays with at most k odds) - (subarrays with at most k-1 odds).
     * 3. Time Complexity: O(n)
     * 4. Space Complexity: O(1)
     * Leetcode Link: https://leetcode.com/problems/count-number-of-nice-subarrays/
     */
    static int numberOfSubarrays(int[] nums, int k) {
        // Count Number of Nice Subarrays (subarray is called nice if there are k odd numbers on it)
        return numberOfSubarraysWithAtMostKOdds(nums, k)-numberOfSubarraysWithAtMostKOdds(nums, k-1);
    }

    /**
     * Approach:
     * 1. Use a single pass with tracking of last seen positions.
     * 2. Maintain an array 'seen' to track the last occurrence of each character (a, b, c).
     * 3. For each character in the string, update its last seen position.
     * 4. When all three characters (a, b, c) have been seen at least once:
     *    - Calculate the minimum last seen position among all three.
     *    - Add (1 + min_position) to the count - this represents all valid substrings ending at current position.
     * 5. The key insight: for a substring ending at position i, it's valid if it starts at any position ≤ min(last_a, last_b, last_c).
     * 6. Return the total count of substrings containing at least one of each character.
     * 7. Time Complexity: O(n)
     * 8. Space Complexity: O(1)
     * Leetcode Link: https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
     */
    static int numberOfSubstrings(String s) {
        // substrings containing at least one occurrence of all these characters a, b and c.
        int[] seen = new int[3];
        Arrays.fill(seen, -1);
        int n = s.length();
        int cnt = 0;
        for(int i=0; i<n; i++) {
            seen[s.charAt(i)-'a'] = i;
            if(seen[0]!=-1 && seen[1]!=-1 && seen[2]!=-1) {
                cnt += 1 + Math.min(seen[0], Math.min(seen[1], seen[2]));
            }
        }
        return cnt;
    }

    /**
     * Approach:
     * 1. Key insight: We can only take from the beginning or end, so we try all combinations of:
     *    - Taking i cards from left and (k-i) cards from right, where i ranges from 0 to k.
     * 2. Implementation:
     *    - Start with sum of first k cards (all from left).
     *    - Edge case: If k >= n, return sum of all cards.
     *    - Use sliding window to try other combinations:
     *      * Remove one card from left end and add one card from right end.
     *      * This gives us all possible combinations efficiently.
     * 3. Keep track of maximum sum encountered across all combinations.
     * 4. Return the maximum possible score.
     * 5. Time Complexity: O(k) - we try k different combinations
     * 6. Space Complexity: O(1)
     * Leetcode Link: https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
     */
    static int maxScore(int[] cardPoints, int k) {
        // take 1 card from the beginning or from the end of the row. take exactly k cards.
        int n = cardPoints.length;
        int sum = 0;
        for(int i=0; i<k; i++) {
            sum += cardPoints[i];
        }
        if(k>=n) {
            return sum;
        }
        int ans = sum;
        int r = n-1;
        for(int i=k-1; i>=0; i--) {
            sum -= cardPoints[i];
            sum += cardPoints[r--];
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        // System.out.println(lengthOfLongestSubstring("abcabcbb"));

        // int[] nums = {1, 0, 1, 0, 1};
        // System.out.println(numSubarraysWithSum(nums, 2));

        // int[] nums = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        // System.out.println(longestOnesIII(nums, 3));
        // System.out.println(longestOnes(nums, 3));

        // int[] fruits = {1, 2, 3, 2, 2};
        // System.out.println(totalFruit(fruits));

        // String s = "AABABBA";
        // System.out.println(characterReplacement(s, 1));

        // int[] nums = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        // System.out.println(numberOfSubarrays(nums, 2));

        // String s = "abcabc";
        // System.out.println(numberOfSubstrings(s));

        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        System.out.println(maxScore(cardPoints, 3));
    }
}

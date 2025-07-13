import java.util.*;

public class MediumProblem {
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
    static int numberOfSubarrays(int[] nums, int k) {
        // Count Number of Nice Subarrays (subarray is called nice if there are k odd numbers on it)
        return numberOfSubarraysWithAtMostKOdds(nums, k)-numberOfSubarraysWithAtMostKOdds(nums, k-1);
    }

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

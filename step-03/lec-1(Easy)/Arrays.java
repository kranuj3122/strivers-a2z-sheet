import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Arrays {
    static int largestElement(int[] arr) {
        int ans = Integer.MIN_VALUE;
        for(int num: arr) {
            ans = Math.max(ans, num);
        }
        return ans;
    }

    static int secondLargestElement(int[] arr) {
        int max = Integer.MIN_VALUE;
        int secondMax = max;
        for(int num: arr) {
            if(num > max) {
                secondMax = max;
                max = num;
            }
            else if(num > secondMax) {
                secondMax = num;
            }
        }
        return secondMax;
    }

    static boolean isSortedArray(int[] arr) {
        int n = arr.length;
        for(int i=1; i<n; i++) {
            if(arr[i]<arr[i-1]) {
                return false;
            }
        }
        return true;
    }

    // from sorted array
    static int removeDuplicates(int[] nums) {
        int k = 1;
        for(int i=1; i<nums.length; i++) {
            if(nums[i]!=nums[i-1]) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }

    static void rotateArrayByOne(int[] arr) {
        int n = arr.length;
        int first = arr[0];
        for(int i=0; i<=n-2; i++) {
            arr[i] = arr[i+1];
        }
        arr[n-1] = first;
    }

    // left rotate
    static void rotateArray(int nums[], int k) {
        int n = nums.length;
        k %= n;
        // line 64 should be here for right rotate [reverse(nums, 0, n-1);]
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
        reverse(nums, 0, n-1);
    }
    static void reverse(int[] nums, int i, int j) {
        while(i<j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    static void moveZeroes(int[] nums) {
        int k=0;
        int n = nums.length;
        for(int i=0; i<n; i++) {
            if(nums[i]!=0) {
                nums[k++] = nums[i];
            }
        }
        for(int i=k; i<n; i++) {
            nums[i] = 0;
        }
    }

    static int linearSearch(int nums[], int target) {
        for(int i=0; i<nums.length; i++) {
            if(nums[i]==target) {
                return i;
            }
        }
		return -1;
    }

    static List<Integer> findUnion(int a[], int b[]) {
        // add your code here
        List<Integer> list = new ArrayList<>();
        int i=0, j=0;
        int m=a.length, n=b.length;
        
        while(i<m && j<n) {
            int val = 0;
            if(a[i]<=b[j]) {
                val = a[i++];
            }
            else {
                val = b[j++];
            }
            
            int sz = list.size();
            if(sz==0 || list.get(sz-1)!=val) {
                list.add(val);
            }
        }
        
        while(i<m) {
            int sz = list.size();
            if(sz==0 || list.get(sz-1)!=a[i]) {
                list.add(a[i]);
            }
            i++;
        }
        
        while(j<n) {
            int sz = list.size();
            if(sz==0 || list.get(sz-1)!=b[j]) {
                list.add(b[j]);
            }
            j++;
        }
        
        return list;
    }

    static int missingNumber(int[] nums) {
        int n = nums.length;
        int ans = n;
        for(int i=0; i<n; i++) {
            ans += (i-nums[i]);
        }
        return ans;
    }

    static int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0;
        int curr = 0;
        for(int num: nums) {
            if(num==1) {
                curr++;
                continue;
            }
            ans = Math.max(ans, curr);
            curr = 0;
        }
        return Math.max(ans, curr);
    }

    static int singleNumber(int[] nums) {
        int ans = 0;
        for(int num: nums) {
            ans ^= num;
        }
        return ans;
    }

    // Array contains only positive integers
    static int longestSubarrayWithSumK(int []a, long k) {
        long currSum = 0;
        int ans = 0;
        int n = a.length;
        int i=0, j=0;
        while(j<n) {
            currSum += a[j];
            while(i<j && currSum>k) {
                currSum -= a[i++];
            }
            if(currSum==k) {
                ans = Math.max(ans, j-i+1);
            }
            j++;
        }
        return ans;
    }

    // Array elements contains positive & negative integer
    static int longestSubarray(int[] arr, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0,-1);
        int n = arr.length;
        int currSum = 0;
        int ans = 0;
        for(int i=0; i<n; i++) {
            currSum += arr[i];
            int target = currSum-k;
            if(mp.containsKey(target)) {
                ans = Math.max(ans, i-mp.get(target));
            }
            if(!mp.containsKey(currSum)) {
                mp.put(currSum, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {12, 47, 5, 29, 34, 1, 18, 42, 8, 25};

        System.out.println(largestElement(arr));

        // System.out.println(secondLargestElement(arr));

        // System.out.println(isSortedArray(arr));

        // int[] brr = new int[] {0,0,1,1,1,2,2,3,3,4};
        // System.out.println(removeDuplicates(brr));
        // System.out.println(java.util.Arrays.toString(brr));

        // rotateArrayByOne(arr);
        // System.out.println(java.util.Arrays.toString(arr));

        // rotateArray(arr, 2);
        // System.out.println(java.util.Arrays.toString(arr));

        // int[] brr = new int[] {0,0,1,12,2,21,0,13,42,40};
        // moveZeroes(brr);
        // System.out.println(java.util.Arrays.toString(brr));

        // System.out.println(linearSearch(arr, 29));

        // int[] a = new int[] {1, 2, 3, 4, 5};
        // int[] b = new int[] {1, 2, 3, 6, 7};
        // System.out.println(findUnion(a, b));

        // int[] a = new int[] {0, 2, 3, 1, 4};
        // System.out.println(missingNumber(a));

        // int[] a = new int[] {1,1,0,1,1,1};
        // System.out.println(findMaxConsecutiveOnes(a));

        // int[] a = new int[] {4,1,2,1,2};
        // System.out.println(singleNumber(a));

        // int[] a = new int[] {1, 2, 3, 1, 1, 1, 1};
        // System.out.println(longestSubarrayWithSumK(a, 3));

        
        // int[] a = new int[] {94,-33,-13,40,-82,94,-33,-13,40,-82};
        // System.out.println(longestSubarray(a, 52));


    }
}

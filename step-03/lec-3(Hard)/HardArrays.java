import java.util.*;
import java.util.Arrays;

public class HardArrays {

    static List<List<Integer>> pascalTriangle(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(Arrays.asList(1));
        for(int i=1; i<numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            List<Integer> prevList = ans.get(i-1);
            for(int j=0; j<=i; j++) {
                if(j==0 || j==i) {
                    temp.add(1);
                    continue;
                }
                temp.add(prevList.get(j-1)+prevList.get(j));
            }
            ans.add(temp);
        }
        return ans;
    }

    static List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        for(int num: nums) {
            mp.put(num, mp.getOrDefault(num, 0)+1);
        }
        List<Integer> ans = new ArrayList<>();
        for(int num: mp.keySet()) {
            if(mp.get(num)>(nums.length/3)) {
                ans.add(num);
            }
        }
        return ans;
    }
    static List<Integer> majorityElementInO1Space(int[] nums) {
        int cnt1=0, cnt2=0;
        int el1=Integer.MIN_VALUE, el2=Integer.MIN_VALUE;
        for(int num: nums) {
            if(cnt1==0 && num!=el2) {
                el1 = num;
                cnt1 = 1;
            }
            else if(cnt2==0 && num!=el1) {
                cnt2 = 1;
                el2 = num;
            }
            else if(num==el1) {
                cnt1++;
            }
            else if(num==el2) {
                cnt2++;
            }
            else {
                cnt1--;
                cnt2--;
            }
        }
        cnt1=0;
        cnt2=0;
        for(int num: nums) {
            if(num==el1) {
                cnt1++;
            }
            else if(num==el2) {
                cnt2++;
            }
        }
        int cnt = nums.length/3;
        List<Integer> ans = new ArrayList<>();
        if(cnt1>cnt) {
            ans.add(el1);
        }
        if(cnt2>cnt) {
            ans.add(el2);
        }
        return ans;
    }

    // No duplicate triplets allowed
    static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0; i<n-2; i++) {
            if(i>0 && nums[i]==nums[i-1]) {
                continue;
            }
            int target = -nums[i];
            int l=i+1, r=n-1;
            while(l<r) {
                if(nums[l]+nums[r]==target) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    while(l<r && nums[l]==nums[l-1]) {
                        l++;
                    }
                    r--;
                    while(l<r && nums[r]==nums[r+1]) {
                        r--;
                    }
                }
                else if(nums[l]+nums[r]<target) {
                    l++;
                }
                else {
                    r--;
                }
            }

        }
        return ans;
    }

    // No duplicate quadruplets allowed
    // Handled integer overflow
    static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0; i<n-3; i++) {
            if(i>0 && nums[i]==nums[i-1]) {
                continue;
            }
            for(int j=i+1; j<n-2; j++) {
                if(j>i+1 && nums[j]==nums[j-1]) {
                    continue;
                }
                long t = target-((long)nums[i]+nums[j]);
                int l=j+1, r=n-1;
                while(l<r) {
                    if((long)nums[l]+nums[r]==t) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        l++;
                        r--;
                        while(l<r && nums[l]==nums[l-1]) {
                            l++;
                        }
                        while(l<r && nums[r]==nums[r+1]) {
                            r--;
                        }
                    }
                    else if(nums[l]+nums[r]<t) {
                        l++;
                    }
                    else {
                        r--;
                    }
                }
            }
        }
        return ans;
    }

    static int largestSubarrayWithSum0(int arr[]) {
        int n = arr.length;
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);
        int currSum = 0;
        int ans = 0;
        for(int i=0; i<n; i++) {
            currSum += arr[i];
            if(mp.containsKey(currSum)) {
                ans = Math.max(ans, i-mp.get(currSum));
                continue;
            }
            mp.put(currSum, i);
        }
        return ans;
    }

    static long subarrayXorCount(int arr[], int k) {
        int currXor = 0;
        long ans = 0;
        Map<Integer, Long> mp = new HashMap<>();
        mp.put(0, 1L);
        for(int num: arr) {
            currXor ^= num;
            int target = currXor^k;
            if(mp.containsKey(target)) {
                ans += mp.get(target);
            }
            mp.put(currXor, mp.getOrDefault(currXor, 0L)+1);
        }
        return ans;
    }

    static int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> ans = new ArrayList<>();
        int l=intervals[0][0], r=intervals[0][1];
        for(int i=1; i<intervals.length; i++) {
            if(r<intervals[i][0]) {
                ans.add(new int[] {l, r});
                l=intervals[i][0];
                r=intervals[i][1];
                continue;
            }
            r=Math.max(r, intervals[i][1]);
        }
        ans.add(new int[] {l, r});

        return ans.toArray(new int[0][]);
    }

    static void mergeTwoSortedArray(int[] nums1, int m, int[] nums2, int n) {
        int k = m+n-1;
        int i=m-1, j=n-1;
        while(i>=0 && j>=0) {
            if(nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            }
            else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        while(i>=0) {
            nums1[k--] = nums1[i--];
        }
        while(j>=0) {
            nums1[k--] = nums2[j--];
        }
    }

    static ArrayList<Integer> findMissingAndRepeatingElement(int arr[]) {
        int a = 0; // will store (missing-repeating)
        long b = 0; // will store (missing^2-repeating^2)
        for(int i=0; i<arr.length; i++) {
            a += (i+1-arr[i]);
            b += (long)(Math.pow(i+1, 2)-(long)Math.pow(arr[i], 2));
        }
        int c = (int)(b/a); // will store (missing+repeating)
        int missing = (a+c)/2;
        int repeating = (c-a)/2;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(repeating);
        list.add(missing);
        return list;
    }

    static int merge(int[] arr, int l, int mid, int r) {
        int i=l, j=mid+1, k=0;
        int[] merged = new int[r-l+1];
        int cnt = 0;
        while(i<=mid && j<=r) {
            if(arr[i]>arr[j]) {
                cnt += (mid-i+1);
                merged[k] = arr[j];
                j++;
            }
            else {
                merged[k] = arr[i];
                i++;
            }
            k++;
        }
        while(i<=mid) {
            merged[k++] = arr[i++];
        }
        while(j<=r) {
            merged[k++] = arr[j++];
        }
        for(int m=0; m<merged.length; m++) {
            arr[m+l] = merged[m];
        }
        return cnt;
    }
    static int mergeSort(int[] arr, int l, int r) {
        if(l>=r) {
            return 0;
        }
        int cnt = 0;
        int mid = l + (r-l)/2;
        cnt += mergeSort(arr, l, mid);
        cnt += mergeSort(arr, mid+1, r);
        cnt += merge(arr, l, mid, r);
        return cnt;
    }
    static int inversionCount(int arr[]) {
        return mergeSort(arr, 0, arr.length-1);
    }

    static int countPairs(int[] arr, int l, int m, int r) {
        int j=m+1;
        int cnt=0;
        for(int k=l; k<=m; k++) {
            while(j<=r && arr[k]>2L*arr[j]) {
                j++;
            }
            cnt += (j-m-1);
        }
        return cnt;
    }
    static int mergeReversePair(int[] arr, int l, int mid, int r) {
        int i=l, j=mid+1, k=0;
        int[] merged = new int[r-l+1];
        int cnt = countPairs(arr, l, mid, r);
        while(i<=mid && j<=r) {
            if(arr[i]>arr[j]) {
                merged[k] = arr[j];
                j++;
            }
            else {
                merged[k] = arr[i];
                i++;
            }
            k++;
        }
        while(i<=mid) {
            merged[k++] = arr[i++];
        }
        while(j<=r) {
            merged[k++] = arr[j++];
        }
        for(int m=0; m<merged.length; m++) {
            arr[m+l] = merged[m];
        }
        return cnt;
    }
    static int mergeSortReversePair(int[] arr, int l, int r) {
        if(l>=r) {
            return 0;
        }
        int cnt = 0;
        int mid = l + (r-l)/2;
        cnt += mergeSortReversePair(arr, l, mid);
        cnt += mergeSortReversePair(arr, mid+1, r);
        cnt += mergeReversePair(arr, l, mid, r);
        return cnt;
    }
    static int reversePairs(int[] nums) {
        return mergeSortReversePair(nums, 0, nums.length-1);
    }

    static int maxProduct(int[] nums) {
        int pre=1, suf=1;
        int n = nums.length;
        int ans = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            if(pre==0) {
                pre = 1;
            }
            if(suf==0) {
                suf = 1;
            }
            pre *= nums[i];
            suf *= nums[n-1-i];
            ans = Math.max(ans, Math.max(pre, suf));
        }
        return ans;
    }

    public static void main(String[] args) {
        // List<List<Integer>> list = pascalTriangle(5);
        // for(List<Integer> l: list) {
        //     System.out.println(l);
        // }

        // int[] nums = {1, 2, 1, 1, 3, 2, 2};
        // // List<Integer> result = majorityElementInO1Space(nums);
        // List<Integer> result = majorityElement(nums);
        // System.out.println(result);

        // int[] nums = {2, -2, 0, 3, -3, 5};
        // List<List<Integer>> triplets = threeSum(nums);
        // for(List<Integer> l: triplets) {
        //     System.out.println(l);
        // }

        // int[] nums = {1, 0, -1, 0, -2, 2};
        // int target = 0;
        // List<List<Integer>> quadruplets = fourSum(nums, target);
        // for (List<Integer> quad : quadruplets) {
        //     System.out.println(quad);
        // }

        // int[] arr = {15, -2, 2, -8, 1, 7, 10, 23};
        // System.out.println(largestSubarrayWithSum0(arr));

        // int[] arr = {4, 2, 2, 6, 4};
        // System.out.println(subarrayXorCount(arr, 6));

        // int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18},{18, 20}};
        // int[][] merged = mergeIntervals(intervals);
        // for(int[] interval: merged) {
        //     System.out.println(Arrays.toString(interval));
        // }

        // int[] nums1 = {1, 2, 3, 0, 0, 0};
        // int m = 3;
        // int[] nums2 = {2, 5, 6};
        // int n = 3;
        // mergeTwoSortedArray(nums1, m, nums2, n);
        // System.out.println(Arrays.toString(nums1));

        // int[] arr = {4, 3, 6, 2, 1, 1};
        // System.out.println(findMissingAndRepeatingElement(arr));

        // int[] arr = {2, 3, 7, 1, 3, 5};
        // System.out.println(inversionCount(arr));

        // int[] nums = {6, 4, 1, 2, 7};
        // System.out.println(reversePairs(nums));

        int[] nums = {2, 3, -2, 4};
        System.out.println(maxProduct(nums));

    }
}

import java.util.Arrays;

public class BinarySearch {
    static int floorSqrt(int n) {
        int l=1, r=n;
        while(l<=r) {
            int mid = l + (r-l)/2;
            int sq = mid*mid;
            if(sq == n) {
                return mid;
            }
            
            if(sq<n) {
                l = mid+1;
            }
            else {
                r = mid-1;
            }
        }
        return r;
    }

    static int helper(int mid, int n, int m) {
        long pow = 1;
        for(int i=1; i<=n; i++) {
            pow *= mid;
            if(pow > m) {
                return 2;
            }
        }
        if(pow==m) {
            return 0;
        }
        return 1;
    }
    static int nthRoot(int n, int m) {
        int l=1, r=m;
        while(l<=r) {
            int mid = l+(r-l)/2;
            int check = helper(mid, n, m);
            if(check==0) {
                return mid;
            }
            if(check==1) {
                l = mid+1;
            }
            else {
                r = mid-1;
            }
        }
        return -1;
    }

    static int max(int[] nums) {
        int m = Integer.MIN_VALUE;
        for(int num: nums) {
            m = Math.max(m, num);
        }
        return m;
    }
    static boolean isValidEatingSpeed(int[] piles, int x, int h){
        long hrs = 0;
        for(int num: piles) {
            hrs += num/x;
            if(num%x > 0) {
                hrs++;
            }
        }
        return hrs<=h;
    }
    static int minEatingSpeed(int[] piles, int h) {
        int l=1, r=max(piles); // r can be piles upper limit e.g. r=(int)1e9
        // int ans = (int)1e9;
        while(l<=r) {
            int mid = l + (r-l)/2;
            boolean isValid = isValidEatingSpeed(piles, mid, h);
            if(isValid) {
                // ans = Math.min(ans, mid);
                r = mid-1;
            }
            else {
                l = mid+1;
            }
        }
        // return ans;
        return r+1;
    }

    static int countBouquets(int[] b, int x, int k) {
        int bCount = 0;
        int adj = 0;
        for(int num: b) {
            if(num<=x) {
                adj++;
            }
            else {
                adj = 0;
            }
            
            if(adj==k) {
                bCount++;
                adj = 0;
            }
        }
        return bCount;
    }
    static int minDays(int[] bloomDay, int m, int k) {
        int l=1, r=(int)1e9+1;
        while(l<=r) {
            int mid = l+(r-l)/2;
            int total = countBouquets(bloomDay, mid, k);
            if(total>=m) {
                r = mid-1;
            }
            else {
                l = mid+1;
            }
        }
        return r==(int)1e9+1 ? -1 : r+1;
    }

    static long quotientSum(int[] nums, int x) {
        long c=0;
        for(int num: nums) {
            c += num/x;
            if(num%x > 0) {
                c++;
            }
        }
        return c;
    }
    static int smallestDivisor(int[] nums, int threshold) {
        int l=1, r=(int)1e6;
        while(l<=r) {
            int mid = l+(r-l)/2;
            long totalSum = quotientSum(nums, mid);
            if(totalSum <= threshold) {
                r = mid-1;
            }
            else {
                l = mid+1;
            }
        }
        return r+1;
    }

    static int countDays(int[] nums, int x) {
        int c = 0;
        int currSum = 0;
        for(int num: nums) {
            if(num>x) {
                return Integer.MAX_VALUE;
            }
            currSum += num;
            if(currSum > x) {
                c++;
                currSum = num;
            }
        }
        if(currSum>0) {
            c++;
        }
        return c;
    }
    static int shipWithinDays(int[] weights, int days) {
        int l=1, r=(int)1e8;
        while(l<=r) {
            int mid = (l+r)/2;
            int d = countDays(weights, mid);
            if(d<=days) {
                r = mid-1;
            }
            else {
                l = mid+1;
            }
        }
        return r+1;
    }

    static int findKthPositive(int[] arr, int k) {
        int n=arr.length;
        int l=0, r=n-1;
        while(l<=r) {
            int mid = l+(r-l)/2;
            int totalMissing = arr[mid]-(mid+1);
            if(totalMissing < k) {
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        /**
        * our answer will be arr[r] + (k-totalMissing at idx r) [total remaining missing]
        * arr[r] + k - (arr[r]-(r+1))
        * arr[r] + k - arr[r] + r + 1
        * r+1+k (OR l+k)
        */
        return l+k; // OR return r+1+k;
    }

    static boolean canBePlaced(int[] nums, int x, int k) {
        int cow=1, last=nums[0];
        for(int i=1; i<nums.length; i++) {
            if(nums[i]-last >= x) {
                cow++;
                last = nums[i];
            }
            if(cow==k) {
                return true;
            }
        }
        return false;
    }
    static int aggressiveCows(int[] stalls, int k) {
        int n=stalls.length;
        Arrays.sort(stalls);
        int l=1, r=stalls[n-1]-stalls[0];
        while(l<=r) {
            int mid = l+(r-l)/2;
            boolean valid = canBePlaced(stalls, mid, k);
            if(valid) {
                l = mid+1;
            }
            else {
                r = mid-1;
            }
        }
        return r;
    }

    static int allocatePage(int[] arr, int x) {
        int c=0;
        int currSum=0;
        for(int num: arr) {
            currSum += num;
            if(currSum > x) {
                c++;
                currSum=num;
            }
        }
        if(currSum>0) {
            c++;
        }
        return c;
    }
    static int findPages(int[] arr, int k) {
        int n = arr.length;
        if(n<k) {
            return -1;
        }
        int l=1, r=0;
        for(int num: arr) {
            l = Math.max(l, num);
            r += num;
        }
        while(l<=r) {
            int mid = l+(r-l)/2;
            int students = allocatePage(arr, mid);
            if(students > k) {
                l = mid+1;
            }
            else {
                r = mid-1;
            }
        }
        return l;
    }

    static int splitCount(int[] nums, int mid) {
        int c=0, currSum=0;
        for(int num: nums) {
            currSum += num;
            if(currSum>mid) {
                c++;
                currSum = num;
            }
        }
        if(currSum>0) {
            c++;
        }
        return c;
    }
    static int splitArray(int[] nums, int k) {
        int l=0, r=0;
        for(int num: nums) {
            l=Math.max(l, num);
            r+=num;
        }
        while(l<=r) {
            int mid = l+(r-l)/2;
            int splits = splitCount(nums, mid);
            if(splits>k) {
                l = mid+1;
            }
            else {
                r = mid-1;
            }
        }
        return r+1; // OR return l;
    }

    static boolean isWorkDone(int[] arr, long x, int k) {
        int c=0, currSum=0;
        for(int num: arr) {
            currSum += num;
            if(currSum > x) {
                c++;
                currSum = num;
            }
        }
        if(currSum > 0) {
            c++;
        }
        return c<=k;
    }
    static int minTime(int[] arr, int k) {
        long l=0, r=0;
        for(int num: arr) {
            l = Math.max(l, num);
            r += num;
        }
        while(l<=r) {
            long mid = l+(r-l)/2;
            if(isWorkDone(arr, mid, k)) {
                r = mid-1;
            }
            else {
                l = mid+1;
            }
        }
        return (int)l;
    }

    static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1=nums1.length;
        int n2=nums2.length;
        if(n1>n2) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int n=n1+n2;
        int leftSide=(n+1)/2; // max no. of element I will store in left part
        int l=0, r=n1; // we can take 0 or all from first array
        while(l<=r) {
            int mid = (l+r)/2; // mid1
            int remaining = leftSide-mid; // mid2
            int l1=mid>0 ? nums1[mid-1] : Integer.MIN_VALUE;
            int l2=remaining>0 ? nums2[remaining-1] : Integer.MIN_VALUE;
            int r1=mid<n1 ? nums1[mid] : Integer.MAX_VALUE;
            int r2=remaining<n2 ? nums2[remaining] : Integer.MAX_VALUE;

            if(Math.max(l1, l2)<=Math.min(r1, r2)) {
                if(n%2==1) {
                    return Math.max(l1, l2);
                }
                return (Math.max(l1,l2)+Math.min(r1,r2))/2.0;
            }
            if(l1 > r2) {
                r=mid-1;
            }
            else {
                l=mid+1;
            }
        }
        return 0;
    }

    static int kthElement(int a[], int b[], int k) {
        int n1=a.length;
        int n2=b.length;
        if(n1>n2) {
            return kthElement(b, a, k);
        }
        int l=Math.max(0, k-n2), r=Math.min(n1, k);
        while(l<=r) {
            int mid1=l+(r-l)/2;
            int mid2=k-mid1;
            
            int l1=mid1>0 ? a[mid1-1] : Integer.MIN_VALUE;
            int l2=mid2>0 ? b[mid2-1] : Integer.MIN_VALUE;
            int r1=mid1<n1 ? a[mid1] : Integer.MAX_VALUE;
            int r2=mid2<n2 ? b[mid2] : Integer.MAX_VALUE;
            
            if(Math.max(l1, l2) <= Math.min(r1, r2)) {
                return Math.max(l1, l2);
            }
            
            if(l1>r2) {
                r = mid1-1;
            }
            else {
                l = mid1+1;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        // System.out.println(floorSqrt(11));

        // System.out.println(nthRoot(2, 9));

        // int[] piles = {30, 11, 23, 4, 20};
        // System.out.println(minEatingSpeed(piles, 5));

        // int[] bloomDay = {7, 7, 7, 7, 12, 7, 7};
        // System.out.println(minDays(bloomDay, 2, 3));

        // int[] nums = {1, 2, 5, 9};
        // System.out.println(smallestDivisor(nums, 6));

        // int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // System.out.println(shipWithinDays(weights, 5));

        // int[] arr = {2, 3, 4, 7, 11};
        // System.out.println(findKthPositive(arr, 5));

        // int[] stalls = {10, 1, 2, 7, 5};
        // System.out.println(aggressiveCows(stalls, 3));

        // int[] arr = {12, 34, 67, 90};
        // System.out.println(findPages(arr, 2));

        // int[] nums = {7, 2, 5, 10, 8};
        // System.out.println(splitArray(nums, 2));

        // int[] arr = {5, 10, 30, 20, 15};
        // System.out.println(minTime(arr, 3));

        // int[] nums1 = {2, 4, 6};
        // int[] nums2 = {1, 3, 5};
        // System.out.println(findMedianSortedArrays(nums1, nums2));

        int[] a = {2, 3, 6, 7, 9};
        int[] b = {1, 4, 8, 10};
        System.out.println(kthElement(a, b, 5));
    }
}

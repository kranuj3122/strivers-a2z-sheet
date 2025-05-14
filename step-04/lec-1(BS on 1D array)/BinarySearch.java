
import java.util.Arrays;
import java.util.List;

public class BinarySearch {

    static int search(int[] nums, int target) {
        int l=0, r=nums.length-1;
        while(l<=r) {
            int mid = l + (r-l)/2;
            if(nums[mid]==target) {
                return mid;
            }
            else if(nums[mid]<target) {
                l = mid+1;
            }
            else {
                r = mid-1;
            }
        }
        return -1;
    }

    static int lowerBound(int[] arr, int target) {
        int l=0, r=arr.length-1;
        while(l<=r) {
            int mid = l + (r-l)/2;
            if(arr[mid]<target) {
                l = mid+1;
            }
            else {
                r=mid-1;
            }
        }
        return r+1;
    }

    static int upperBound(int[] arr, int target) {
        int l=0, r=arr.length-1;
        while(l<=r) {
            int mid = l + (r-l)/2;
            if(arr[mid]>target) {
                r = mid-1;
            }
            else {
                l = mid+1;
            }
        }
        return l;
    }

    static int searchInsert(int[] nums, int target) {
        // nums contain distinct sorted integer
        return lowerBound(nums, target);
    }

    static int[] getFloorAndCeil(int x, int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        // System.out.println(Arrays.toString(arr));
        int l = lowerBound(arr, x);
        int u = upperBound(arr, x)-1;
        // System.out.println(l+", "+u);
        int floor = u==-1 ? -1 : arr[u];
        int ceil = l==n ? -1 : arr[l];
        return new int[] {floor, ceil};
    }

    static int[] firstAndLastOccurence(int[] nums, int target) {
        int n = nums.length;
        int l = lowerBound(nums, target);
        if(l==n || nums[l]!=target) {
            return new int[] {-1,-1};
        }
        int u = upperBound(nums, target)-1;
        return new int[] {l, u};
    }

    static int countFrequency(int[] nums, int target) {
        int n = nums.length;
        int l = lowerBound(nums, target);
        if(l==n || nums[l]!=target) {
            return 0;
        }
        int u = upperBound(nums, target)-1;
        return u-l+1;
    }

    static int searchInRotatedSortedArray1(int[] nums, int target) {
        int l=0, r=nums.length-1;
        while(l<=r) {
            int mid = (l+r)/2;
            if(nums[mid]==target) {
                return mid;
            }
            else if(nums[mid]<nums[r]) {
                // right side sorted
                if(nums[mid]<target && target<=nums[r]) {
                    l = mid+1;
                }
                else {
                    r = mid-1;
                }
            }
            else {
                // nums[l]<nums[mid] (left side sorted)
                if(nums[l]<=target && target<nums[mid]) {
                    r = mid-1;
                }
                else {
                    l = mid+1;
                }
            }
        }
        return -1;
    }

    static boolean searchInRotatedSortedArray2(int[] nums, int target) {
        int n = nums.length;
        if(n==1) {
            return nums[0]==target;
        }
        int l=0, r=n-1;
        while(l<=r) {
            int mid = (l+r)/2;
            if(nums[mid]==target) {
                return true;
            }
            else if(nums[l]==nums[mid] && nums[mid]==nums[r]) {
                // edge case in case of duplicacy
                l++;
                r--;
            }
            else if(nums[mid]<=nums[r]) {
                // right side sorted
                if(nums[mid]<target && target<=nums[r]) {
                    l = mid+1;
                }
                else {
                    r = mid-1;
                }
            }
            else {
                // nums[l]<nums[mid] (left side sorted)
                if(nums[l]<=target && target<nums[mid]) {
                    r = mid-1;
                }
                else {
                    l = mid+1;
                }
            }
        }
        return false;
    }

    static int findMin(int[] nums) {
        int n=nums.length;
        int ans=Integer.MAX_VALUE;
        int l=0, r=n-1;
        while(l<=r) {
            int mid = (l+r)/2;
            if(nums[l]<=nums[r]) {
                ans = Math.min(ans, nums[l]);
                break;
            }

            if(nums[l]<=nums[mid]) {
                ans = Math.min(ans, nums[l]);
                l = mid+1;
            }
            else {
                ans = Math.min(ans, nums[mid]);
                r = mid-1;
            }
        }
        return ans;
    }

    static int findKRotation(int[] nums) {
        int n=nums.length;
        int minVal=Integer.MAX_VALUE;
        int ans = -1;
        int l=0, r=n-1;
        while(l<=r) {
            int mid = (l+r)/2;
            if(nums[l]<=nums[r]) {
                if(nums[l] < minVal) {
                    minVal = nums[l];
                    ans = l;
                }
                break;
            }

            if(nums[l]<=nums[mid]) {
                if(nums[l] < minVal) {
                    minVal = nums[l];
                    ans = l;
                }
                l = mid+1;
            }
            else {
                if(nums[mid] < minVal) {
                    minVal = nums[mid];
                    ans = mid;
                }
                r = mid-1;
            }
        }
        return ans; 
    }

    static int singleNonDuplicate(int[] nums) {
        return -1;
    }

    static int findPeakElement(int[] nums) {
        int n = nums.length;
        if(n==1 || nums[0]>nums[1]) {
            return 0;
        }
        if(nums[n-1]>nums[n-2]) {
            return n-1;
        }

        int l=1, r=n-2;
        while(l<=r) {
            int mid = (l+r)/2;
            if(nums[mid-1]<nums[mid] && nums[mid]>nums[mid+1]) {
                return mid;
            }
            
            if(nums[mid-1]<=nums[mid]) {
                l = mid+1;
            }
            else {
                r = mid-1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // int[] nums = {-1, 0, 3, 5, 9, 12};
        // System.out.println(search(nums, 9));

        // int[] arr = {2, 3, 7, 10, 11, 11, 25};
        // System.out.println(lowerBound(arr, 11));
        // System.out.println(upperBound(arr, 11));
        
        // int[] nums = {1, 3, 5, 6};
        // System.out.println(searchInsert(nums, 2));

        // int[] arr = {5, 6, 8, 9, 6, 5, 5, 6};
        // System.out.println(Arrays.toString(getFloorAndCeil(7, arr)));

        // int[] nums = {5, 7, 7, 8, 8, 8, 10};
        // System.out.println(Arrays.toString(firstAndLastOccurence(nums, 8)));
        // System.out.println(countFrequency(nums, 8));

        // int[] nums = {4,5,6,7,0,1,2};
        // System.out.println(searchInRotatedSortedArray1(nums, 0));

        // int[] nums = {2,5,6,0,0,1,2};
        // System.out.println(searchInRotatedSortedArray2(nums, 0));

        // int[] nums = {4,5,6,7,0,1,2};
        // System.out.println(findMin(nums));
        // System.out.println(findKRotation(nums)); // valuate idx of min element

        // singleNonDuplicate(nums); To-Do

        // int[] nums = {1,2,5,4,3,2,1};
        // System.out.println(findPeakElement(nums));

    }
}

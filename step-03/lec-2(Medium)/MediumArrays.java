import java.util.*;
import java.util.Arrays;;

public class MediumArrays {

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> mp = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            if(mp.containsKey(target-nums[i])) {
                return new int[] {mp.get(target-nums[i]), i};
            }
            mp.put(nums[i], i);
        }
        return new int[] {-1, -1};
    }

    static void sortZeroOneTwo(int[] nums) {
        int n = nums.length;
        int i=0, j=0, k=n-1;
        while(j<=k) {
            if(nums[j]==0) {
                swap(nums, i, j);
                i++;
                j++;
            }
            else if(nums[j]==2) {
                swap(nums, j, k);
                k--;
            }
            else {
                j++;
            }
        }
    }

    public int majorityElement(int[] nums) {
        int ans = nums[0];
        int count = 1;
        for(int i=1; i<nums.length; i++) {
            if(count==0) {
                ans = nums[i];
            }

            if(nums[i]==ans) {
                count++;
            }
            else {
                count--;
            }
        }

        return ans;
    }

    // Kadane's Algorithm
    static int maxSubArray(int[] nums) {
        int n = nums.length;
        int currSum = nums[0];
        int maxSumSoFar = nums[0];

        for(int i=1; i<n; i++) {
            currSum = Math.max(currSum+nums[i], nums[i]);
            maxSumSoFar = Math.max(currSum, maxSumSoFar);
        }

        return maxSumSoFar;
    }
    static void printMaxSubArray(int[] nums) {
        int n = nums.length;
        int currSum = nums[0];
        int maxSumSoFar = nums[0];
        int x=0, y=0, t=0;

        for(int i=1; i<n; i++) {
            if(currSum+nums[i] > nums[i]) {
                currSum = currSum+nums[i];
            }
            else {
                currSum = nums[i];
                t = i;
            }
            if(currSum > maxSumSoFar) {
                maxSumSoFar = currSum;
                x = t;
                y = i;
            }
        }

        // int ans = 0; // used to verify correct maxSum
        for(int i=x; i<=y; i++) {
            // ans += nums[i];
            System.out.print(nums[i]+", ");
        }

        // return ans;
    }

    // Best time to buy and sell stock
    static int maxProfit(int[] prices) {
        int n = prices.length;
        int[] maxArr = new int[n];
        maxArr[n-1] = prices[n-1];
        for(int i=n-2; i>=0; i--) {
            maxArr[i] = Math.max(maxArr[i+1], prices[i]);
        }
        int min = prices[0];
        int ans = 0;
        for(int i=1; i<n; i++) {
            ans = Math.max(ans, maxArr[i]-min);
            min = Math.min(min, prices[i]);
        }
        return ans;
    }

    static int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int pos=0, neg=1;
        for(int i=0; i<n; i++) {
            if(nums[i]<0) {
                ans[neg] = nums[i];
                neg += 2;
            }
            else {
                ans[pos] = nums[i];
                pos += 2;
            }
        }
        return ans;
    }

    static void reverse(int[] nums, int i, int j) {
        while(i<j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
    static void nextPermutation(int[] nums) {
        int ind = -1;
        int n = nums.length;
        // find break-point from backward where nums[i]<nums[i+1] 
        for(int i=n-2; i>=0; i--) {
            if(nums[i] < nums[i+1]) {
                ind = i;
                break;
            }
        }

        // if didn't found break-point, it means it sorted in descending order.
        // simply reverse it and return.
        if(ind==-1) {
            reverse(nums, 0, n-1);
            return;
        }

        // find smallest(i) > nums[break-point] on the right side
        // swap(ind, i)
        // reverse(ind+1, n-1) [to make it sorted]
        for(int i=n-1; i>ind; i--) {
            if(nums[i] > nums[ind]) {
                swap(nums, ind, i);
                reverse(nums, ind+1, n-1);
                break;
            }
        }
    }

    static ArrayList<Integer> leaders(int arr[]) {
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(arr[n-1]);
        int max = arr[n-1];
        for(int i=n-2; i>=0; i--) {
            if(arr[i]>=max) {
                max = arr[i];
                ans.add(arr[i]);
            }
        }
        Collections.reverse(ans);
        return ans;
    }

    static int longestConsecutive(int[] arr) {
        int n = arr.length;
        if(n <= 1) {
            return n;
        }
        Arrays.sort(arr);
        int currAns=1, ans=1;
        for(int i=1; i<n; i++) {
            if(arr[i]==arr[i-1]) {
                continue;
            }
            if(arr[i]-arr[i-1]==1) {
                currAns++;
            }
            else {
                ans = Math.max(ans, currAns);
                currAns = 1;
            }
        }
        
        return Math.max(ans, currAns);
    }

    static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] topRow = new int[n];
        int[] leftCol = new int[m];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j]==0) {
                    leftCol[i] = -1;
                    topRow[j] = -1;
                }
            }
        }
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(leftCol[i]==-1 || topRow[j]==-1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    static void verticallyReverseMatrix(int[][] arr) {
        int n = arr.length;
        for(int c=0; c<n; c++) {
            int i=0, j=n-1;
            while(i<j) {
                int temp = arr[i][c];
                arr[i][c] = arr[j][c];
                arr[j][c] = temp;
                i++;
                j--;
            }
        }
    }
    static void reverseArr(int[] arr) {
        int i=0, j=arr.length-1;
        while(i<j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    /*
     * clockwise rotation by 90deg
     * 1st vertically reverse the matrix
     * take transpose of it --> swap(mat[i][j], mat[j][i])
     */
    static void rotateBy90(int[][] matrix) {
        int n = matrix.length;
        verticallyReverseMatrix(matrix);
        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /*
     * anti-clockwise rotaion by 90deg
     * 1st reverse each row
     * take transpose of it --> swap(mat[i][j], mat[j][i])
     */
    static void rotateby90(int arr[][]) {
        for(int[] a: arr) {
            reverseArr(a);
        }
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<i; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
    }

    /*
     * anti-clockwise rotation by 180deg
     * 1st vertically reverse the matrix
     * reverse each row
     */
    static void rotateMatrixBy180(int[][] mat) {
        verticallyReverseMatrix(mat);
        for(int[] m: mat) {
            reverseArr(m);
        }
    }

    static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int rs=0, re=m-1, cs=0, ce=n-1;
        List<Integer> list = new ArrayList<>();
        while(rs<=re && cs<=ce) {
            //topmost row
            for(int i=cs; i<=ce; i++) {
                list.add(matrix[rs][i]);
            }
            rs++;
            //rightmost col
            for(int i=rs; i<=re; i++) {
                list.add(matrix[i][ce]);
            }
            ce--;
            if(rs<=re && cs<=ce) {
                //bottommost row
                for(int i=ce; i>=cs; i--) {
                    list.add(matrix[re][i]);
                }
                re--;
                //leftmost col
                for(int i=re; i>=rs; i--) {
                    list.add(matrix[i][cs]);
                }
                cs++;
            }
        }
        return list;
    }

    static int subarraySumCount(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        int n = nums.length;
        int currSum = 0;
        int ans = 0;
        for(int i=0; i<n; i++) {
            currSum += nums[i];
            int target = currSum - k;
            if(mp.containsKey(target)) {
                ans += mp.get(target);
            }
            mp.put(currSum, mp.getOrDefault(currSum, 0)+1);
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] arr = new int[] {1, 3, 5, -7, 6, -3};
        // int[] ans = twoSum(arr, 0);
        // System.out.println(Arrays.toString(ans));

        // int[] arr = new int[] {2,0,2,1,1,0};
        // sortZeroOneTwo(arr);
        // System.out.println(Arrays.toString(arr));

        // int[] arr = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        // System.out.println(maxSubArray(arr));
        // printMaxSubArray(arr);

        // int[] arr = new int[] {7,1,5,3,6,4};
        // System.out.println(maxProfit(arr));

        // Rearrange +ve and -ve elements in alternate positions while maintaining the input order
        // int[] arr = new int[] {3,1,-2,-5,2,-4};
        // System.out.println(Arrays.toString(rearrangeArray(arr)));

        int[] arr = {2,1,3};
        nextPermutation(arr);
        System.out.println(Arrays.toString(arr));

        // int[] arr = new int[] {-3, 4, 5, 1, -4, -5};
        // System.out.println(leaders(arr));

        // int[] arr = new int[] {2, 6, 1, 9, 4, 5, 3};
        // System.out.println(longestConsecutive(arr));

        // int[][] matrix = {
        //     {1, 1, 1},
        //     {1, 0, 1},
        //     {1, 1, 1}
        // };
        // setZeroes(matrix);
        // for (int[] row : matrix) {
        //     System.out.println(Arrays.toString(row));
        // }

        // int[][] matrix = {
        //     {1, 2, 3},
        //     {4, 5, 6},
        //     {7, 8, 9}
        // };
        // rotateBy90(matrix); // clockwise
        // rotateby90(matrix); // anti-clockwise
        // rotateMatrixBy180(matrix); // anti-clockwise
        // for (int[] row : matrix) {
        //     System.out.println(Arrays.toString(row));
        // }

        // int[][] matrix = {
        //     {1, 2, 3, 4},
        //     {5, 6, 7, 8},
        //     {9, 10, 11, 12}
        // };
        // List<Integer> ans = spiralOrder(matrix);
        // System.out.println(ans);

        // int[] a = new int[] {0,0,0,0};
        // System.out.println(subarraySumCount(a, 0));
        
    }
}

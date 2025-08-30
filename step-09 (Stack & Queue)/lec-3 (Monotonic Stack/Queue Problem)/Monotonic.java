import java.util.*;

public class Monotonic {

    static ArrayList<Integer> nextLargerElement(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stk = new Stack<>();
        int n = arr.length;

        for(int i=n-1; i>=0; i--) {
            while(!stk.empty() && stk.peek()<=arr[i]) {
                stk.pop();
            }
            int nxtGreater = stk.empty() ? -1 : stk.peek();
            list.add(0, nxtGreater);
            stk.push(arr[i]);
        }
        
        return list;
    }

    static int[] nextGreaterElements2(int[] nums) {
        Stack<Integer> stk = new Stack<>();
        int n = nums.length;
        int[] ans = new int[n];

        for(int i=2*n-1; i>=0; i--) {
            int num = nums[i%n];
            while(!stk.empty() && stk.peek()<=num) {
                stk.pop();
            }
            if(i<n) {
                ans[i] = stk.empty() ? -1 : stk.peek();
            }
            stk.push(num);
        }
        
        return ans;
    }

    static ArrayList<Integer> nextSmallerElement(ArrayList<Integer> arr, int n){
        Stack<Integer> stk = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=n-1; i>=0; i--) {
            while(!stk.empty() && stk.peek()>=arr.get(i)) {
                stk.pop();
            }
            int nxtSmaller = stk.empty() ? -1 : stk.peek();
            ans.add(0, nxtSmaller);
            stk.push(arr.get(i));
        }
        return ans;
    }

    static int trapWater(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        left[0] = height[0];
        for(int i=1; i<n; i++) {
            left[i] = Math.max(left[i-1], height[i]);
        }
        int[] right = new int[n];
        right[n-1] = height[n-1];
        for(int i=n-2; i>=0; i--) {
            right[i] = Math.max(right[i+1], height[i]);
        }
        int ans = 0;
        for(int i=1; i<n-1; i++) {
            ans += Math.max(0, Math.min(left[i-1], right[i+1])-height[i]);
        }
        return ans;
    }
    static int trapWaterM2(int[] height) {
        int n = height.length;
        int ans = 0;
        int i=0, j=n-1;
        int leftMax=-1, rightMax=-1;
        while(i<=j) {
            if(height[i]<=height[j]) {
                if(height[i]>leftMax) {
                    leftMax = height[i];
                }
                else {
                    ans += leftMax-height[i];
                }
                i++;
            }
            else {
                if(height[j]>rightMax) {
                    rightMax = height[j];
                }
                else {
                    ans += rightMax-height[j];
                }
                j--;
            }
        }
        return ans;
    }

    static int sumSubarrayMins(int[] arr) {
        int mod = (int)1e9 + 7;
        int n = arr.length;
        int[] lse = new int[n]; // number of subarrays ending at i where arr[i] is min
        int[] rse = new int[n]; // number of subarrays starting at i where arr[i] is min
        Stack<Integer> stk = new Stack<>();
        for(int i=0; i<n; i++) {
            while(!stk.empty() && arr[stk.peek()]>arr[i]) {
                stk.pop();
            }
            lse[i] = stk.empty() ? i+1 : i-stk.peek();
            stk.push(i);
        }
        stk.clear();
        for(int i=n-1; i>=0; i--) {
            while(!stk.empty() && arr[stk.peek()]>=arr[i]) {
                stk.pop();
            }
            rse[i] = stk.empty() ? n-i : stk.peek()-i;
            stk.push(i);
        }
        int ans = 0;
        for(int i=0; i<n; i++) {
            ans += (int)((1L*lse[i]*rse[i]*arr[i])%mod);
            ans %= mod;
        }
        return ans;
    }

    static int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        Stack<Integer> stk = new Stack<>();
        for(int i=0; i<n; i++) {
            if(asteroids[i] > 0) {
                stk.push(asteroids[i]);
            }
            else {
                while(!stk.empty() && stk.peek()>0 && stk.peek()<Math.abs(asteroids[i])) {
                    stk.pop();
                }
                if(!stk.empty() && stk.peek()==Math.abs(asteroids[i])) {
                    stk.pop();
                }
                else if (stk.empty() || stk.peek()<0){
                    stk.push(asteroids[i]);
                }
            }
        }
        int n1 = stk.size();
        int[] ans = new int[n1];
        for(int i=n1-1; i>=0; i--) {
            ans[i] = stk.pop();
        }
        return ans;
    }


    static long sumSubarrayMaxs(int[] arr) {
        int n = arr.length;
        int[] lge = new int[n]; // number of subarrays ending at i where arr[i] is max
        int[] rge = new int[n]; // number of subarrays starting at i where arr[i] is max
        Stack<Integer> stk = new Stack<>();
        for(int i=0; i<n; i++) {
            while(!stk.empty() && arr[stk.peek()]<arr[i]) {
                stk.pop();
            }
            lge[i] = stk.empty() ? i+1 : i-stk.peek();
            stk.push(i);
        }
        stk.clear();
        for(int i=n-1; i>=0; i--) {
            while(!stk.empty() && arr[stk.peek()]<=arr[i]) {
                stk.pop();
            }
            rge[i] = stk.empty() ? n-i : stk.peek()-i;
            stk.push(i);
        }
        long ans = 0;
        for(int i=0; i<n; i++) {
            ans += (1L*lge[i]*rge[i]*arr[i]);
        }
        return ans;
    }
    static long subArrayRanges(int[] nums) {
        return sumSubarrayMaxs(nums)-sumSubarrayMins(nums);
    }

    static String removeKdigits(String num, int k) {
        StringBuilder sb = new StringBuilder();
        int len = 0;
        for(char c: num.toCharArray()) {
            while(len>0 && k>0 && (sb.charAt(len-1)-'0')>(c-'0')) {
                sb.deleteCharAt(len-1);
                len--;
                k--;
            }
            sb.append(c);
            len++;
        }
        while(len>0 && k>0) {
            sb.deleteCharAt(len-1);
            k--;
            len--;
        }
        int start = 0;
        while(start<len && sb.charAt(start)=='0') {
            start++;
        }

        if(start==len) {
            return "0";
        }

        return sb.substring(start);
    }

    static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] lse = new int[n];
        int[] rse = new int[n];
        Stack<Integer> stk = new Stack<>();
        for(int i=0; i<n; i++) {
            while(!stk.empty() && heights[stk.peek()]>=heights[i]) {
                stk.pop();
            }
            lse[i] = stk.empty() ? -1 : stk.peek();
            stk.push(i);
        }
        stk.clear();
        for(int i=n-1; i>=0; i--) {
            while(!stk.empty() && heights[stk.peek()]>=heights[i]) {
                stk.pop();
            }
            rse[i] = stk.empty() ? n : stk.peek();
            stk.push(i);
        }

        int ans=0;
        for(int i=0; i<n; i++) {
            ans = Math.max(ans, heights[i]*(rse[i]-lse[i]-1));
        }
        return ans;
    }
    static int largestRectangleAreaM2(int[] heights) {
        int n = heights.length;
        int[] lse = new int[n];
        Stack<Integer> stk = new Stack<>();
        int ans=0;
        for(int i=0; i<n; i++) {
            while(!stk.empty() && heights[stk.peek()]>=heights[i]) {
                int nse = i;
                int ind = stk.pop();
                int element = heights[ind];
                ans = Math.max(ans, element*(nse-lse[ind]-1));
            }
            lse[i] = stk.empty() ? -1 : stk.peek();
            stk.push(i);
        }

        while(!stk.empty()) {
            int nse = n;
            int ind = stk.pop();
            int element = heights[ind];
            ans = Math.max(ans, element*(nse-lse[ind]-1));
        }

        return ans;
    }

    static int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] row = new int[n];
        int ans = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j] == '0') {
                    row[j] = 0;
                }
                else {
                    row[j] += 1;
                }
            }
            ans = Math.max(ans, largestRectangleAreaM2(row));
        }
        return ans;
    }



    public static void main(String[] args) {
        // int[] arr = {6, 8, 0, 1, 3};
        // System.out.println(nextLargerElement(arr));

        // int[] nums = {1, 8, -1, -100, -1, 222, 1111111, -111111};
        // int[] res = nextGreaterElements2(nums);
        // System.out.println(Arrays.toString(res));

        // ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(2, 1, 4, 3));
        // System.out.println(nextSmallerElement(arr, arr.size()));

        // int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        // System.out.println(trapWater(height));
        // System.out.println(trapWaterM2(height));

        // int[] arr = {3, 1, 2, 4};
        // System.out.println(sumSubarrayMins(arr));

        // int[] asteroids = {5, 10, -5};
        // int[] result = asteroidCollision(asteroids);
        // System.out.println(Arrays.toString(result));

        // int[] nums = {4, -2, -3, 4, 1};
        // System.out.println(subArrayRanges(nums));

        // String num = "1432219";
        // int k = 3;
        // System.out.println(removeKdigits(num, k));

        // int[] heights = {2,1,5,6,2,3};
        // System.out.println(largestRectangleArea(heights));
        // System.out.println(largestRectangleAreaM2(heights));

        char[][] matrix = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        System.out.println(maximalRectangle(matrix));
    }
}

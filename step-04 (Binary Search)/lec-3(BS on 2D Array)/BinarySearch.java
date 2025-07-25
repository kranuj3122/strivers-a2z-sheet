public class BinarySearch {
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
    static int rowWithMax1s(int arr[][]) {
        int n=arr.length;
        int m=arr[0].length;
        int ans=-1;
        int maxCount=0;
        for(int i=0; i<n; i++) {
            int cnt1 = m-lowerBound(arr[i], 1);
            if(cnt1>maxCount) {
                maxCount = cnt1;
                ans = i;
            }
            
        }
        return ans;
    }

    static boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length;
        int n=matrix[0].length;
        int l=0, r=m*n-1;
        while(l<=r) {
            int mid=l+(r-l)/2;
            int row=mid/n;
            int col=mid%n;
            if(matrix[row][col]==target) {
                return true;
            }
            if(target > matrix[row][col]) {
                l = mid+1;
            }
            else {
                r = mid-1;
            }
            
        }
        return false;
    }

    static boolean searchMatrix2(int[][] matrix, int target) {
        int m=matrix.length;
        int n=matrix[0].length;
        int r=0, c=n-1;
        while(r<m && c>=0) {
            if(matrix[r][c]==target) {
                return true;
            }
            if(matrix[r][c]<target) {
                r++;
            }
            else {
                c--;
            }
        }
        while(c>=0) {
            if(matrix[m-1][c]==target) {
                return true;
            }
            c--;
        }
        while(r<m) {
            if(matrix[r][0]==target) {
                return true;
            }
            r++;
        }
        return false;
    }

    static int maxIndex(int[] arr) {
        int ans=-1, mx=0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] > mx) {
                mx = arr[i];
                ans = i;
            }
        }
        return ans;
    }
    static int[] findPeakGrid(int[][] mat) {
        int m=mat.length;
        int l=0, r=m-1; // BS on rows
        while(l<=r) {
            int mid = (l+r)/2; // row
            int ind = maxIndex(mat[mid]); // col

            int top = mid>0 ? mat[mid-1][ind] : -1;
            int bottom = mid<m-1 ? mat[mid+1][ind] : -1;

            if(mat[mid][ind]>top && mat[mid][ind]>bottom) {
                return new int[] {mid, ind};
            }
            else if(top>mat[mid][ind]) {
                r = mid-1;
            }
            else {
                l = mid+1;
            }
        }
        return new int[] {-1, -1};
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
    static int countSmallerAndEqual(int[][] mat, int x) {
        int cnt=0;
        for(int i=0; i<mat.length; i++) {
            cnt += upperBound(mat[i], x);
        }
        return cnt;
    }
    static int median(int mat[][]) {
        int m=mat.length;
        int n=mat[0].length;
        int req=(m*n + 1)/2;
        int l=2001, r=-1;
        for(int i=0; i<m; i++) {
            l=Math.min(l,mat[i][0]);
            r=Math.max(r,mat[i][n-1]);
        }
        while(l<=r) {
            int mid=(l+r)/2;
            int cnt=countSmallerAndEqual(mat, mid);
            if(cnt>=req) {
                r = mid-1;
            }
            else {
                l = mid+1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        // int[][] arr = {
        //     {0, 1, 1, 1},
        //     {0, 0, 1, 1},
        //     {1, 1, 1, 1},
        //     {0, 0, 0, 0}
        // };
        // System.out.println(rowWithMax1s(arr));

        // int[][] matrix = {
        //     {1, 3, 5, 7},
        //     {10, 11, 16, 20},
        //     {23, 30, 34, 60}
        // };
        // System.out.println(searchMatrix(matrix, 3));

        // int[][] matrix = {
        //     {1, 4, 7, 11, 15},
        //     {2, 5, 8, 12, 19},
        //     {3, 6, 9, 16, 22},
        //     {10, 13, 14, 17, 24},
        //     {18, 21, 23, 26, 30}
        // };
        // System.out.println(searchMatrix2(matrix, 5));

        // int[][] mat = {
        //     {10, 20, 15},
        //     {21, 30, 14},
        //     {7, 16, 32}
        // };
        // int[] peak = findPeakGrid(mat);
        // System.out.println("[" + peak[0] + ", " + peak[1] + "]");

        int[][] mat = {
            {15, 34, 53},
            {24, 36, 84},
            {56, 81, 87}
        };
        System.out.println(median(mat));
    }
}
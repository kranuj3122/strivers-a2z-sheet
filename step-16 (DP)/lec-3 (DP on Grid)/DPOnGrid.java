import java.util.*;

public class DPOnGrid {
    static int maximumPointsHelper(int[][] mat, int ind, int skill, int[][] dp) {
        if(ind==0) {
            return 0;
        }
        if(dp[ind][skill] != -1) {
            return dp[ind][skill];
        }
        int a = skill!=0 ? mat[ind-1][0]+maximumPointsHelper(mat, ind-1, 0, dp) : 0;
        int b = skill!=1 ? mat[ind-1][1]+maximumPointsHelper(mat, ind-1, 1, dp) : 0;
        int c = skill!=2 ? mat[ind-1][2]+maximumPointsHelper(mat, ind-1, 2, dp) : 0;

        return dp[ind][skill] = Math.max(a, Math.max(b, c));
    }
    static int maximumPoints(int arr[][]) {
        int n = arr.length;
        // int[][] dp = new int[n+1][4];
        // for(int[] d: dp) {
        //     Arrays.fill(d, -1);
        // }
        // return maximumPointsHelper(arr, n, 3, dp);
        
        int[][] dp = new int[n][3];
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];
        for(int i=1; i<n; i++) {
            dp[i][0] = arr[i][0]+Math.max(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = arr[i][1]+Math.max(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = arr[i][2]+Math.max(dp[i-1][1], dp[i-1][0]);
        }
        return Math.max(dp[n-1][0], Math.max(dp[n-1][1], dp[n-1][2]));
    }

    static int uniquePathsHelper(int m, int n, int[][] dp) {
        if(m<1 || n<1) {
            return 0;
        }
        if(m==1 && n==1) {
            return 1;
        }
        if(dp[m][n] != -1) {
            return dp[m][n];
        }

        return dp[m][n] = uniquePathsHelper(m, n-1, dp) + uniquePathsHelper(m-1, n, dp);
    }
    static int uniquePaths(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        // for(int[] arr: dp) {
        //     Arrays.fill(arr, -1);
        // }
        // return solve(m, n, dp);
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                dp[i][j] = i==1 && j==1 ? 1 : dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m][n];
    }

    static int uniquePathsWithObstaclesHelper(int[][] grid, int m, int n, int[][] dp) {
        if(m<1 || n<1) {
            return 0;
        }
        if(grid[m-1][n-1]==1) {
            return dp[m][n] = 0;
        }
        if(m==1 && n==1) {
            return 1;
        }
        if(dp[m][n] != -1) {
            return dp[m][n];
        }

        return dp[m][n] = uniquePathsWithObstaclesHelper(grid, m, n-1, dp) + uniquePathsWithObstaclesHelper(grid, m-1, n, dp);
    }
    static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m+1][n+1];
        // for(int[] arr: dp) {
        //     Arrays.fill(arr, -1);
        // }
        // return uniquePathsWithObstaclesHelper(obstacleGrid, m, n, dp);

        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                dp[i][j] = obstacleGrid[i-1][j-1]==1 ? 0 : (i==1 && j==1 ? 1 : dp[i][j-1]+dp[i-1][j]);
            }
        }
        return dp[m][n];
    }

    static int minPathSumHelper(int[][] g, int m, int n, int[][] dp) {
        if(m==0 || n==0) {
            return Integer.MAX_VALUE;
        }
        if(m==1 && n==1) {
            return g[m-1][n-1];
        }
        if(dp[m][n] != -1) {
            return dp[m][n];
        }

        return dp[m][n] = g[m-1][n-1]+Math.min(minPathSumHelper(g, m-1, n, dp), minPathSumHelper(g, m, n-1, dp));
    }
    static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m+1][n+1];
        // for(int[] arr: dp) {
        //     Arrays.fill(arr, -1);
        // }
        // return solve(grid, m, n, dp);

        for(int i=0; i<=n; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        for(int i=0; i<=m; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        dp[1][1] = grid[0][0];
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(i==1 && j==1) {
                    continue;
                }
                dp[i][j] = grid[i-1][j-1]+Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m][n];
    }

    static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for(int i=0; i<n; i++) {
            dp[i] = triangle.get(n-1).get(i);
        }

        for(int i=n-2; i>=0; i--) {
            int m = triangle.get(i).size();
            for(int j=0; j<m; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
            }
        }

        return dp[0];
    }

    static int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        for(int i=0; i<m; i++) {
            dp[0][i] = matrix[0][i];
        }
        for(int i=1; i<n; i++) {
            for(int j=0; j<m; j++) {
                int last = dp[i-1][j];
                if(j-1>=0) {
                    last = Math.min(last, dp[i-1][j-1]);
                }
                if(j+1<m) {
                    last = Math.min(last, dp[i-1][j+1]);
                }
                dp[i][j] = matrix[i][j] + last;
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i=0; i<m; i++) {
            ans = Math.min(ans, dp[n-1][i]);
        }

        return ans;
    }

    static int cherryPickupHelper(int[][] g, int i, int j1, int j2, int[][][] dp) {
        int r = g.length;
        int c = g[0].length;
        if(j1<0 || j2<0 || j1>=c || j2>=c) {
            return (int)-1e7;
        }
        if(i==r-1) {
            if(j1==j2) {
                return g[i][j1];
            }
            return g[i][j1]+g[i][j2];
        }
        if(dp[i][j1][j2] != -1) {
            return dp[i][j1][j2];
        }

        int value = j1==j2 ? g[i][j1] : g[i][j1]+g[i][j2];
        int max = 0;
        for(int dj1=-1; dj1<=1; dj1++) {
            for(int dj2=-1; dj2<=1; dj2++) {
                max = Math.max(max, cherryPickupHelper(g, i+1, j1+dj1, j2+dj2, dp));
            }
        }
        return dp[i][j1][j2] = value+max;
    }
    static int cherryPickup(int[][] grid) {
        int n = grid.length;
        int c = grid[0].length;
        int[][][] dp = new int[n][c][c];
        // for(int[][] a: dp) {
        //     for(int[] b: a) {
        //         Arrays.fill(b, -1);
        //     }
        // }
        // return cherryPickupHelper(grid, 0, 0, c-1, dp);

        for(int j1=0; j1<c; j1++) {
            for(int j2=0; j2<c; j2++) {
                dp[n-1][j1][j2] = j1==j2 ? grid[n-1][j1] : grid[n-1][j1]+grid[n-1][j2];
            }
        }

        for(int i=n-2; i>=0; i--) {
            for(int j1=0; j1<c; j1++) {
                for(int j2=0; j2<c; j2++) {
                    int value = j1==j2 ? grid[i][j1] : grid[i][j1]+grid[i][j2];
                    int max = 0;
                    for(int dj1=-1; dj1<=1; dj1++) {
                        for(int dj2=-1; dj2<=1; dj2++) {
                            if(j1+dj1>=0 && j2+dj2>=0 && j1+dj1<c && j2+dj2<c) {
                                max = Math.max(max, dp[i+1][j1+dj1][j2+dj2]);
                            }
                        }
                    }
                    dp[i][j1][j2] = value+max;
                }
            }
        }

        return dp[0][0][c-1];
    }

    public static void main(String[] args) {
        // int[][] matrix = {
        //     {70, 40, 10},
        //     {180, 20, 5},
        //     {200, 60, 30}
        // };
        // System.out.println(maximumPoints(matrix));

        // int m = 3, n = 7;
        // System.out.println(uniquePaths(m, n));

        // int[][] obstacleGrid = {
        //     {0, 0, 0},
        //     {0, 1, 0},
        //     {0, 0, 0}
        // };
        // System.out.println(uniquePathsWithObstacles(obstacleGrid));

        // int[][] grid = {
        //     {1, 3, 1},
        //     {1, 5, 1},
        //     {4, 2, 1}
        // };
        // System.out.println(minPathSum(grid));

        // List<List<Integer>> triangle = new ArrayList<>();
        // triangle.add(Arrays.asList(2));
        // triangle.add(Arrays.asList(3, 4));
        // triangle.add(Arrays.asList(6, 5, 7));
        // triangle.add(Arrays.asList(4, 1, 8, 3));
        // System.out.println(minimumTotal(triangle));

        // int[][] matrix = {
        //     {2, 1, 3},
        //     {6, 5, 4},
        //     {7, 8, 9}
        // };
        // System.out.println(minFallingPathSum(matrix));

        int[][] grid = {
            {3, 1, 1},
            {2, 5, 1},
            {1, 5, 5},
            {2, 1, 1}
        };
        System.out.println(cherryPickup(grid));
    }
}

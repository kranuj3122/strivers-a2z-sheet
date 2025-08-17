
public class SquareDP {
    static int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j]==1) {
                    int left = j-1>=0 ? dp[i][j-1] : 0;
                    int top = i-1>=0 ? dp[i-1][j] : 0;
                    int topLeft = i-1>=0 && j-1>=0 ? dp[i-1][j-1] : 0;
                    dp[i][j] = 1 + Math.min(topLeft, Math.min(left, top));
                }
            }
        }
        int ans = 0;
        for(int[] r: dp) {
            for(int c: r) {
                ans += c;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {0, 1, 1, 1},
            {1, 1, 1, 1},
            {0, 1, 1, 1}
        };
        System.out.println(countSquares(matrix));
    }
}

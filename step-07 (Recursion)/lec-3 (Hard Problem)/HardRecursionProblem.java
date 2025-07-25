import java.util.*;

public class HardRecursionProblem {
    static boolean solve(char[][] grid, String str, int i, int j, int ind, boolean[][] used) {
        int m = grid.length;
        int n = grid[0].length;
        if(ind==str.length()) {
            return true;
        }
        if(i<0 || j<0 || i==m || j==n || used[i][j] || grid[i][j]!=str.charAt(ind)) {
            return false;
        }
        used[i][j] = true;
        boolean ans = false;
        ans |=  solve(grid, str, i+1, j, ind+1, used);
        ans |=  solve(grid, str, i-1, j, ind+1, used);
        ans |=  solve(grid, str, i, j+1, ind+1, used);
        ans |=  solve(grid, str, i, j-1, ind+1, used);
        used[i][j] = false;
        return ans;
    }
    static boolean wordSearch(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(word.charAt(0)==board[i][j]) {
                    boolean[][] used = new boolean[m][n];
                    if(solve(board, word, i, j, 0, used)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean isPallindrome(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i)!=s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    static void partitionHelper(String s, List<String> curr, List<List<String>> ans) {
        if(s.length() == 0) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        for(int i=0; i<s.length(); i++) {
            if(isPallindrome(s, 0, i)) {
                curr.add(s.substring(0, i+1));
                partitionHelper(s.substring(i+1), curr, ans);
                curr.remove(curr.size()-1);
            }
        }
    }
    static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        partitionHelper(s, new ArrayList<>(), ans);
        return ans;
    }


    static boolean[] leftVis;
    static boolean[] topLeftVis, bottomLeftVis;
    static boolean isSafe(char[][] b, int i, int j) {
        int r=i, c=j;
        // checking left side
        while(j>=0) {
            if(b[i][j]=='Q') {
                return false;
            }
            j--;
        }
        j=c;
        // checking top-left diagonal
        while(i>=0 && j>=0) {
            if(b[i][j]=='Q') {
                return false;
            }
            i--;
            j--;
        }
        i=r;
        j=c;
        // checking bottom-left diagonal
        while(i<b.length && j>=0) {
            if(b[i][j]=='Q') {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    static void solveNQueensHelper(char[][] b, int col, int n, List<List<String>> ans) {
        if(col==n) {
            List<String> temp = new ArrayList<>();
            for(char[] row: b) {
                temp.add(String.valueOf(row));
            }
            ans.add(temp);
            return;
        }
        for(int i=0; i<n; i++) {
            // if(isSafe(b, i, col)) {
                // b[i][col] = 'Q';
                // solve(b, col+1, n, ans);
                // b[i][col] = '.';
            // }
            if(!leftVis[i] && !bottomLeftVis[i+col] && !topLeftVis[n-1 + col-i]) {
                leftVis[i] = true;
                bottomLeftVis[i+col] = true;
                topLeftVis[n-1 + col-i] = true;
                b[i][col] = 'Q';
                solveNQueensHelper(b, col+1, n, ans);
                b[i][col] = '.';
                leftVis[i] = false;
                bottomLeftVis[i+col] = false;
                topLeftVis[n-1 + col-i] = false;
            }
        }
    }
    static List<List<String>> solveNQueens(int n) {
        leftVis = new boolean[n];
        topLeftVis = new boolean[2*n - 1];
        bottomLeftVis = new boolean[2*n -1];

        char[][] board = new char[n][n];
        for(char[] b: board) {
            Arrays.fill(b, '.');
        }
        List<List<String>> ans = new ArrayList<>();
        solveNQueensHelper(board, 0, n, ans);
        return ans;
    }

    static void ratInMazeHelper(int[][] m, int i, int j, String s, ArrayList<String> ans, boolean[][] vis) {
        int n = m.length;
        if(i==n-1 && j==n-1) {
            ans.add(s);
            return;
        }
        if(i<0 || j<0 || i==n || j==n || vis[i][j] || m[i][j]==0) {
            return;
        }
        
        vis[i][j] = true;
        
        ratInMazeHelper(m, i+1, j, s+"D", ans, vis); // down
        
        ratInMazeHelper(m, i, j-1, s+"L", ans, vis); // left
        
        ratInMazeHelper(m, i, j+1, s+"R", ans, vis); // right
        
        ratInMazeHelper(m, i-1, j, s+"U", ans, vis); // up
        
        vis[i][j] = false;
    }
    static ArrayList<String> ratInMaze(int[][] maze) {
        int n = maze.length;
        ArrayList<String> ans = new ArrayList<>();
        boolean[][] vis = new boolean[n][n];
        ratInMazeHelper(maze, 0, 0, "", ans, vis);
        return ans;
    }

    static boolean wordBreakHelper(String s, List<String> wordDict, Set<String> set) {
        int n = s.length();
        if(n==0) {
            return true;
        }

        for(int i=0; i<n; i++) {
            if(wordDict.contains(s.substring(0, i+1)) && !set.contains(s.substring(i+1))) {
                if(wordBreakHelper(s.substring(i+1), wordDict, set)) {
                    return true;
                }
                set.add(s.substring(i+1));
            }
        }
        return false;
    }
    static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        return wordBreakHelper(s, wordDict, set);
    }

    static void addOperatorsHelper(String s, long curr, String exp, List<String> ans, int target, long prevCal, int ind) {
        if(ind==s.length()) {
            if(curr==target) {
                ans.add(exp);
            }
            return;
        }
        for(int i=ind; i<s.length(); i++) {
            if(i>ind && s.charAt(ind) == '0') {
                break;
            }
            long n = Long.parseLong(s.substring(ind, i+1));
            if(ind!=0) {
                addOperatorsHelper(s, curr+n, exp+"+"+n, ans, target, n, i+1);
                addOperatorsHelper(s, curr-n, exp+"-"+n, ans, target, -n, i+1);
                addOperatorsHelper(s, curr-prevCal+(prevCal*n), exp+"*"+n, ans, target, prevCal*n, i+1);
            }
            else {
                addOperatorsHelper(s, curr+n, exp+n, ans, target, n, i+1);
            }
        }
    }
    static List<String> addOperators(String num, int target) {
        List<String> ans  = new ArrayList<>();
        addOperatorsHelper(num, 0L, "", ans, target, 0L, 0);
        return ans;
    }

    public static void main(String[] args) {
        // char[][] board = {
        //     {'A', 'B', 'C', 'E'},
        //     {'S', 'F', 'C', 'S'},
        //     {'A', 'D', 'E', 'E'}
        // };
        // String word = "ABCCED";
        // System.out.println(wordSearch(board, word));

        // System.out.println(partition("aab"));

        // System.out.println(solveNQueens(4));

        // int[][] maze = {
        //     {1, 0, 0, 0},
        //     {1, 1, 0, 1},
        //     {1, 1, 0, 0},
        //     {0, 1, 1, 1}
        // };
        // System.out.println(ratInMaze(maze));

        // String s = "applepenapple";
        // List<String> wordDict = Arrays.asList("apple", "pen");
        // System.out.println(wordBreak(s, wordDict));

        String num = "232";
        int target = 8;
        System.out.println(addOperators(num, target));
    }
}

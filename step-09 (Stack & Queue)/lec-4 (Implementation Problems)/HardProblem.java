import java.util.*;

public class HardProblem {
    static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n-k+1];
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=0; i<n; i++) {
            while(!dq.isEmpty() && nums[i]>=nums[dq.getLast()]) {
                dq.removeLast();
            }
            dq.addLast(i);
            if(i>=k-1) {
                int ind = i-k+1;
                ans[ind] = nums[dq.getFirst()];
                if(dq.getFirst()==ind) {
                    dq.removeFirst();
                }
            }
        }
        return ans;
    }

    static int celebrity(int mat[][]) {
        int n = mat.length;
        int[] iKnowOther = new int[n];
        int[] otherKnowMe = new int[n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(mat[i][j]==1) {
                    iKnowOther[i]++;
                    otherKnowMe[j]++;
                }
            }
        }
        for(int i=0; i<n; i++) {
            if(iKnowOther[i]==1 && otherKnowMe[i]==n) {
                return i;
            }
        }
        
        return -1;
    }


    public static void main(String[] args) {
        // int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        // int k = 3;
        // int[] result = maxSlidingWindow(nums, k);
        // System.out.println(Arrays.toString(result));

        int[][] mat = {
            {1, 1, 0},
            {0, 1, 0},
            {0, 1, 1}
        };
        System.out.println(celebrity(mat));
    }
}

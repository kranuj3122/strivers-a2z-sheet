import java.util.*;

public class MediumHard {
    static int maxMeetings(int start[], int end[]) {
        int n = end.length;
        Integer[] idx = new Integer[n];
        for(int i=0; i<n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j)->end[i]-end[j]);
        int ans = 1;
        int last = end[idx[0]];
        for(int i=1; i<n; i++) {
            if(start[idx[i]] > last) {
                ans++;
                last = end[idx[i]];
            }
        }
        return ans;
    }

    static boolean canJump(int[] nums) {
        // Jump Game
        int n = nums.length;
        int last = n-1;
        for(int i=n-2; i>=0; i--) {
            if(i+nums[i] >= last) {
                last = i;
            }
        }
        return last==0;
    }

    static int jump(int[] nums) {
        // Jump Game II
        int n = nums.length;
        // int[] dp = new int[n];
        // Arrays.fill(dp, 10001);
        // dp[0] = 0;
        // for(int i=0; i<n; i++) {
        //     for(int j=i+1; j<=Math.min(n-1, i+nums[i]); j++) {
        //         dp[j] = Math.min(dp[j], 1+dp[i]);
        //     }
        // }
        // return dp[n-1];
        int l=0, r=0;
        int jump = 0;
        while(r < n-1) {
            int newR = 0; // This is the maximum index I can reach
            for(int i=l; i<=r; i++) {
                newR = Math.max(newR, i+nums[i]);
            }
            l = r+1;
            r = newR;
            jump++;
        }
        return jump;
    }

    static int calculateMinPatforms(int arr[], int dep[], int n) {
        Arrays.sort(arr);
        Arrays.sort(dep);
        int i=0, j=0;
        int cnt = 0;
        int mxCnt = 0;
        while(i<n) {
            if(arr[i]<=dep[j]) {
                cnt++;
                i++;
            }
            else {
                j++;
                cnt--;
            }
            mxCnt = Math.max(mxCnt, cnt);
        }
        return mxCnt;
    }

    static int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        left[0] = 1;
        for(int i=1; i<n; i++) {
            if(ratings[i]>ratings[i-1]) {
                left[i] = left[i-1]+1;
            }
            else {
                left[i] = 1;
            }
        }
        int[] right = new int[n];
        right[n-1] = 1;
        for(int i=n-2; i>=0; i--) {
            if(ratings[i]>ratings[i+1]) {
                right[i] = right[i+1]+1;
            }
            else {
                right[i] = 1;
            }
        }
        int ans = 0;
        for(int i=0; i<n; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }

    static ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        int n = profit.length;
        int mxDeadline = 0;
        for(int d: deadline) {
            mxDeadline = Math.max(mxDeadline, d);
        }
        int[] vis = new int[mxDeadline+1];
        
        Integer[] idx = new Integer[n];
        for(int i=0; i<n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (a, b)->profit[b]-profit[a]);
        
        int count=0, profitEarn=0;
        for(int i=0; i<n; i++) {
            for(int j=deadline[idx[i]]; j>0; j--) {
                if(vis[j]==0) {
                    vis[j] = 1;
                    count++;
                    profitEarn += profit[idx[i]];
                    break;
                }
            }
        }
        
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(count);
        list.add(profitEarn);
        return list;
    }

    static int sjf(int bt[]) {
        // Shortest Job first (calculate the average waiting time)
        Arrays.sort(bt);
        int n = bt.length;
        int waitTime = 0;
        int t = 0;
        for(int i=0; i<n-1; i++) {
            t += bt[i];
            waitTime += t;
        }
        return waitTime/n;
    }

    static int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b)->a[1]-b[1]);
        int nonOverlap = 1;
        int last = intervals[0][1];
        for(int i=1; i<n; i++) {
            if(intervals[i][0]>=last) {
                nonOverlap++;
                last = intervals[i][1];
            }
        }
        return n-nonOverlap;
    }

    static int[][] mergeInterval(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);
        int lastStart=-1, lastEnd=-1;
        List<int[]> list = new ArrayList<>();
        for(int i=0; i<n; i++) {
            if(intervals[i][0]>lastEnd) {
                if(lastEnd != -1) {
                    list.add(new int[] {lastStart, lastEnd});
                }
                lastStart = intervals[i][0];
                lastEnd = intervals[i][1];
            }
            else {
                lastStart = Math.min(lastStart, intervals[i][0]);
                lastEnd = Math.max(lastEnd, intervals[i][1]);
            }
        }
        list.add(new int[] {lastStart, lastEnd});
        return list.toArray(new int[list.size()][]);
    }

    static int[][] insertInterval(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int n = intervals.length;
        int i=0;
        while(i<n && intervals[i][1]<newInterval[0]) {
            list.add(new int[] {intervals[i][0], intervals[i][1]});
            i++;
        }

        int mn = newInterval[0];
        int mx = newInterval[1];
        while(i<n && mx>=intervals[i][0]) {
            mn = Math.min(intervals[i][0], mn);
            mx = Math.max(intervals[i][1], mx);
            i++;
        }
        list.add(new int[] {mn, mx});

        while(i<n) {
            list.add(new int[] {intervals[i][0], intervals[i][1]});
            i++;
        }

        return list.toArray(new int[list.size()][]);
    }


    public static void main(String[] args) {
        // int[] start = {1, 3, 0, 5, 8, 5};
        // int[] end = {2, 4, 6, 7, 9, 9};
        // System.out.println(maxMeetings(start, end));

        // int[] nums = {2, 3, 1, 1, 4};
        // System.out.println(canJump(nums));
        // System.out.println(jump(nums));

        // int n = 6;
        // int[] arr = {900, 940, 950, 1100, 1500, 1800};
        // int[] dep = {910, 1200, 1120, 1130, 1900, 2000};
        // System.out.println(calculateMinPatforms(arr, dep, n));

        // int[] ratings = {1, 0, 2};
        // System.out.println(candy(ratings));

        // int[] deadline = {2, 1, 2, 1, 1};
        // int[] profit = {100, 19, 27, 25, 15};
        // ArrayList<Integer> res = jobSequencing(deadline, profit);
        // System.out.println("Jobs done: " + res.get(0) + ", Profit: " + res.get(1));

        // int[] bt = {4, 3, 7, 1, 2};
        // System.out.println(sjf(bt));

        // int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        // System.out.println(eraseOverlapIntervals(intervals));

        // int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        // int[][] merged = mergeInterval(intervals);
        // for (int[] interval : merged) {
        //     System.out.println(Arrays.toString(interval));
        // }

        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4,8};
        int[][] result = insertInterval(intervals, newInterval);
        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }

    }
}

import java.util.*;

public class MediumHard {
    static int maxMeetings(int start[], int end[]) {
        /**
         * Approach:
         * 1. Sort meetings by their end times.
         * 2. Select meetings as follows:
         *    a. Select the first meeting (with the earliest end time).
         *    b. For each subsequent meeting:
         *       i. Check if its start time is after the end time of the last selected meeting.
         *       ii. If yes, include it in the count.
         *       iii. Update the last end time to this meeting's end time.
         * 3. Return the total count of selected meetings.
         */
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
        // 55 - Jump Game (https://leetcode.com/problems/jump-game/description/)

        /**
         * Approach:
         * 1. Start from the end of the array and move backwards.
         * 2. Keep track of the leftmost position (last) from which the end can be reached.
         * 3. For each index i, if i + nums[i] >= last, update last to i.
         * 4. After processing, if last == 0, it means we can reach the end from the start.
         */
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
        // 45 - Jump Game II (https://leetcode.com/problems/jump-game-ii/description/)

        int n = nums.length;
        /**
         * Approach: Dynamic Programming (DP)
         * 
         * 1. We use a DP array where dp[i] represents the minimum number of jumps needed to reach index i.
         * 2. Initialize all values in dp[] to a large number (10001), except dp[0] = 0 (starting point).
         * 3. For each position i, iterate through all reachable positions j (from i+1 to i+nums[i]).
         * 4. For each j, update dp[j] to the minimum of its current value and dp[i] + 1 (one more jump from i).
         * 5. The answer is dp[n-1], the minimum jumps needed to reach the last index.
         */
        // int[] dp = new int[n];
        // Arrays.fill(dp, 10001);
        // dp[0] = 0;
        // for(int i=0; i<n; i++) {
        //     for(int j=i+1; j<=Math.min(n-1, i+nums[i]); j++) {
        //         dp[j] = Math.min(dp[j], 1+dp[i]);
        //     }
        // }
        // return dp[n-1];

        /**
         * Approach: Greedy Algorithm
         * 1. Use two pointers:
         *    a. l: start of the current window.
         *    b. r: end of the current window (indices reachable with current jumps).
         * 2. At each step, find the farthest index (newR) we can reach from the current window [l, r].
         * 3. Move to the next window [r+1, newR] and increment the jump count.
         * 4. Repeat until we reach or pass the last index.
         * 5. Return the total number of jumps needed.
         */
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
        /**
         * Approach:
         * 1. Sort both arrival (arr) and departure (dep) arrays.
         * 2. Use two pointers:
         *    - i for arrivals, j for departures.
         * 3. Traverse both arrays:
         *    - If arr[i] <= dep[j]:
         *        - Increment platform count (cnt++).
         *        - Move to next arrival (i++).
         *    - Else:
         *        - Decrement platform count (cnt--).
         *        - Move to next departure (j++).
         * 4. Track the maximum platform count (mxCnt).
         * 5. Return mxCnt.
         */
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
        // 135 - Candy (https://leetcode.com/problems/candy/description/)

        /**
         * Approach:
         * 1. Use two arrays, left and right:
         *    - left[i]: minimum candies for child i when scanning from left to right.
         *    - right[i]: minimum candies for child i when scanning from right to left.
         * 2. Traverse from left to right:
         *    - If ratings[i] > ratings[i-1], set left[i] = left[i-1] + 1; else left[i] = 1.
         * 3. Traverse from right to left:
         *    - If ratings[i] > ratings[i+1], set right[i] = right[i+1] + 1; else right[i] = 1.
         * 4. For each child, take the maximum of left[i] and right[i] as the candies to give.
         * 5. Sum up the candies for all children and return the total.
         */
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
        /**
         * Approach:
         * 1. Find the maximum deadline among all jobs.
         * 2. Create a visited array (vis) of size maxDeadline + 1 to track occupied time slots.
         * 3. Pair each job's profit and deadline, and sort jobs in descending order of profit.
         * 4. For each job (starting from the highest profit):
         *    a. Try to schedule it at its latest possible slot (from its deadline down to 1).
         *    b. If a free slot is found, mark it as occupied, increment the job count, and add its profit.
         * 5. Return the total number of jobs done and the total profit earned.
         */
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

        /**
         * Approach:
         * 1. Sort the burst time array (bt) in ascending order.
         * 2. For each job (except the last), accumulate the burst times to calculate waiting time:
         *      - Add bt[i] to a running total (timer).
         *      - Add timer to total waiting time.
         * 3. Return the average waiting time: totalWaitTime / number of jobs.
         */
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
        /**
         * Approach:
         * 1. Sort intervals in ascending order by their end time.
         * 2. Count the maximum number of non-overlapping intervals.
         * 3. Return the total number of intervals minus the count of non-overlapping intervals.
         */
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
        /**
         * Approach:
         * 1. Sort intervals in ascending order by their start time.
         * 2. Initialize variables to track the start and end of the current merged interval:
         *    a. lastStart: start of the current merged interval.
         *    b. lastEnd: end of the current merged interval.
         * 3. Iterate through the intervals:
         *    a. If the current interval does not overlap with the previous (intervals[i][0] > lastEnd):
         *        i. If lastEnd is not -1, add the previous merged interval [lastStart, lastEnd] to the result list.
         *        ii. Start a new merge with lastStart = intervals[i][0], lastEnd = intervals[i][1].
         *    b. If the current interval overlaps with the previous:
         *        i. Update lastStart to min(lastStart, intervals[i][0]).
         *        ii. Update lastEnd to max(lastEnd, intervals[i][1]).
         * 4. After the loop, add the last merged interval [lastStart, lastEnd] to the result list.
         * 5. Return the list of merged intervals as an array.
         */
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
        /**
         * Approach:
         * 1. The intervals array is sorted by start time.
         * 2. Use a list to store the merged intervals.
         * 3. Add all intervals ending before newInterval starts.
         * 4. Merge all overlapping intervals with newInterval by updating its start and end.
         * 5. Add the merged newInterval.
         * 6. Add all remaining intervals.
         * 7. Return the result as an array.
         */
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

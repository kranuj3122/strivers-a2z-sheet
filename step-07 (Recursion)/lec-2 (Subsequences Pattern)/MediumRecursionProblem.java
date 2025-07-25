import java.util.*;

public class MediumRecursionProblem {
    static void solve(int n, String curr, List<String> list) {
        if(n==0) {
            list.add(curr);
            return;
        }
        solve(n-1, curr+"0", list);
        int len = curr.length();
        if(len==0 || curr.charAt(len-1)!='1') {
            solve(n-1, curr+"1", list);
        }
    }
    static List<String> generateBinaryStrings(int n) {
        // string which do not contain consecutive 1s.
        List<String> list = new ArrayList<>();
        solve(n, "", list);
        return list;
    }

    static void solveGenerateParenthesis(int open, int close, String curr, List<String> list) {
        if(open==0 && close==0) {
            list.add(curr);
            return;
        }
        if(open>0) {
            solveGenerateParenthesis(open-1, close, curr+"(", list);
        }
        if(close>open) {
            solveGenerateParenthesis(open, close-1, curr+")", list);
        }
    }
    static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        solveGenerateParenthesis(n, n, "", list);
        return list;
    }

    static void buildSubset(int[] nums, int ind, List<Integer> curr, List<List<Integer>> ans) {
        if(ind==nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        curr.add(nums[ind]);
        buildSubset(nums, ind+1, curr, ans);
        curr.remove(curr.size()-1);
        buildSubset(nums, ind+1, curr, ans);
    }
    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        buildSubset(nums, 0, new ArrayList<Integer>(), ans);
        return ans;
    }

    static void subsequenceWithSum(int[] nums, int ind, int k, List<Integer> curr, List<List<Integer>> ans) {
        if(ind==nums.length) {
            if(k==0) {
                ans.add(new ArrayList<>(curr));
            }
            return;
        }

        curr.add(nums[ind]);
        k -= nums[ind];
        subsequenceWithSum(nums, ind+1, k, curr, ans);

        curr.remove(curr.size()-1);
        k += nums[ind];
        subsequenceWithSum(nums, ind+1, k, curr, ans);
    }
    static List<List<Integer>> subsequenceWithTargetSum(int[] nums, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        subsequenceWithSum(nums, 0, k, new ArrayList<>(), ans);
        return ans;
    }

    static int countSubsequenceWithSum(int[] nums, int ind, int k) {
        if(ind==nums.length) {
            if(k==0) {
                return 1;
            }
            return 0;
        }

        k -= nums[ind];
        int keep = countSubsequenceWithSum(nums, ind+1, k);

        k += nums[ind];
        int skip = countSubsequenceWithSum(nums, ind+1, k);

        return keep+skip;
    }
    static int countSubsequenceWithTargetSum(int[] nums, int k) {
        return countSubsequenceWithSum(nums, 0, k);
    }

    static boolean checkSubsequence(int[] nums, int ind, int k) {
        if(ind==nums.length) {
            if(k==0) {
                return true;
            }
            return false;
        }

        k -= nums[ind];
        if(checkSubsequence(nums, ind+1, k)) {
            return true;
        }

        k += nums[ind];
        if(checkSubsequence(nums, ind+1, k)) {
            return true;
        }

        return false;
    }
    static boolean checkSubsequenceSum(int[] nums, int k) {
        return checkSubsequence(nums, 0, k);
    }


    static void solve(int[] nums, int k, int ind, List<Integer> curr, List<List<Integer>> ans) {
        if(k<0) {
            return;
        }
        if(ind==nums.length) {
            if(k==0) {
                ans.add(new ArrayList<>(curr));
            }
            return;
        }

        curr.add(nums[ind]);
        solve(nums, k-nums[ind], ind, curr, ans);
        curr.remove(curr.size()-1);
        solve(nums, k, ind+1, curr, ans);

    }
    static void solve2(int[] nums, int k, int ind, List<Integer> curr, List<List<Integer>> ans) {
        if(k<0) {
            return;
        }
        if(k==0) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for(int i=ind; i<nums.length; i++) {
            curr.add(nums[i]);
            solve2(nums, k-nums[i], i, curr, ans);
            curr.remove(curr.size()-1);
        }
    }
    static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        // solve(candidates, target, 0, new ArrayList<>(), ans);
        solve2(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }


    static void combinationSum2Helper(int[] nums, int ind, int k, List<Integer> curr, List<List<Integer>> ans) {
        if(k<0) {
            return;
        }
        if(k==0) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        for(int i=ind; i<nums.length; i++) {
            if(i>ind && nums[i]==nums[i-1]) {
                continue;
            }
            if(nums[i]>k) {
                break;
            }
            curr.add(nums[i]);
            combinationSum2Helper(nums, i+1, k-nums[i], curr, ans);
            curr.remove(curr.size()-1);
        }
    }
    static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum2Helper(candidates, 0, target, new ArrayList<>(), ans);
        return ans;
    }

    static void subsetSumsHelper(int[] nums, int ind, int sum, List<Integer> ans) {
        if(ind==nums.length) {
            ans.add(sum);
            return;
        }
        subsetSumsHelper(nums, ind+1, sum+nums[ind], ans);
        subsetSumsHelper(nums, ind+1, sum, ans);
    }
    static List<Integer> subsetSums(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        subsetSumsHelper(nums, 0, 0, ans);
        return ans;
    }

    static void subsetsWithDupHelper(int[] nums, int ind, List<Integer> curr, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(curr));
        if(ind==nums.length) {
            return;
        }
        for(int i=ind; i<nums.length; i++) {
            if(i>ind && nums[i]==nums[i-1]) {
                continue;
            }
            curr.add(nums[i]);
            subsetsWithDupHelper(nums, i+1, curr, ans);
            curr.remove(curr.size()-1);
        }
    }
    static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDupHelper(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    static void combinationSum3Helper(int k, int ind, int target, List<Integer> curr, List<List<Integer>> ans) {
        if(target < 0) {
            return;
        }
        if(target==0) {
            if(curr.size()==k) {
                ans.add(new ArrayList<>(curr));
            }
            return;
        }
        if(ind > 9) {
            return;
        }

        curr.add(ind);
        combinationSum3Helper(k, ind+1, target-ind, curr, ans);
        curr.remove(curr.size()-1);
        combinationSum3Helper(k, ind+1, target, curr, ans);
    }
    static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum3Helper(k, 1, n, new ArrayList<>(), ans);
        return ans;
    }

    static List<String> letterCombinationsHelper(String str, Map<Integer, List<String>> mp) {
        if(str.length() == 0) {
            List<String> list = new ArrayList<>();
            list.add("");
            return list;
        }

        List<String> a = mp.get(str.charAt(0)-'0');
        List<String> b = letterCombinationsHelper(str.substring(1), mp);

        List<String> ans = new ArrayList<>();
        for(String a1: a) {
            for(String b1: b) {
                ans.add(a1+b1);
            }
        }
        return ans;
    }
    static List<String> letterCombinations(String digits) {
        if(digits.length()==0) {
            return new ArrayList<>();
        }
        Map<Integer, List<String>> mp = new HashMap<>();
        mp.put(2, Arrays.asList("a", "b", "c"));
        mp.put(3, Arrays.asList("d", "e", "f"));
        mp.put(4, Arrays.asList("g", "h", "i"));
        mp.put(5, Arrays.asList("j", "k", "l"));
        mp.put(6, Arrays.asList("m", "n", "o"));
        mp.put(7, Arrays.asList("p", "q", "r", "s"));
        mp.put(8, Arrays.asList("t", "u", "v"));
        mp.put(9, Arrays.asList("w", "x", "y", "z"));
    
        return letterCombinationsHelper(digits, mp);
    }

    public static void main(String[] args) {
        // List<String> result = generateBinaryStrings(4);
        // System.out.println(result);

        // List<String> result = generateParenthesis(3);
        // System.out.println(result);

        // int[] nums = {1, 2, 3};
        // List<List<Integer>> result = subsets(nums);
        // System.out.println(result);

        // int[] nums = {4, 9, 2, 5, 1};
        // System.out.println(subsequenceWithTargetSum(nums, 10));
        // System.out.println(countSubsequenceWithTargetSum(nums, 10));
        // int[] nums2 = {4, 2, 10, 5, 1, 3};
        // System.out.println(subsequenceWithTargetSum(nums2, 5));
        // System.out.println(countSubsequenceWithTargetSum(nums2, 5));

        // int[] nums = {1, 2, 3, 4, 5};
        // System.out.println(checkSubsequenceSum(nums, 8));
        // int[] nums2 = {4, 3, 9, 2};
        // System.out.println(checkSubsequence(nums2, 0, 10));

        // int[] candidates = {2, 3, 5};
        // System.out.println(combinationSum(candidates, 8));

        // int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        // System.out.println(combinationSum2(candidates, 8));

        // int[] nums = {1,2,3};
        // System.out.println(subsetSums(nums));

        // int[] nums = {1, 2, 2};
        // System.out.println(subsetsWithDup(nums));

        // System.out.println(combinationSum3(3, 9));

        System.out.println(letterCombinations("23"));
    }
}

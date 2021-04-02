import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TargetSumOfPossibleSubSetArray {
    static void sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial) {
        int s = 0;
        for (int x : partial) {
            s += x;
        }
        if (s == target)
            System.out.println("sum(" + Arrays.toString(partial.toArray()) + ")=" + target);
        if (s >= target)
            return;
        for (int i = 0; i < numbers.size(); i++) {
            ArrayList<Integer> remaining = new ArrayList<Integer>();
            int n = numbers.get(i);
            for (int j = i + 1; j < numbers.size(); j++) {
                remaining.add(numbers.get(j));
            }
            ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);
            partial_rec.add(n);
            sum_up_recursive(remaining, target, partial_rec);
        }
    }

    static void sum_up(ArrayList<Integer> numbers, int target) {
        sum_up_recursive(numbers, target, new ArrayList<Integer>());
    }

    public static void main(String args[]) {
        int[] numbers = {3, 9, 8, 4, 5, 7, 10};
        int target = 15;
        //sum_up(new ArrayList<Integer>(Arrays.asList(numbers)), target);

        combinationSum3(numbers, target).forEach(l -> {
            l.forEach(n -> System.out.print(n + " "));
            System.out.println();
        });
    }

    static List<List<Integer>> res = new ArrayList<>();
    public static List<List<Integer>> combinationSum3(int[] candidates, int target) {
        Arrays.sort(candidates);
        helper(candidates, 0, target, new ArrayList<Integer>());
        return res;
    }

    private static void helper(int[] can, int start, int target,List<Integer> each) {
        for (int i = start; i < can.length; i++) {
            List<Integer> temp = new ArrayList<>(each);
            if (can[i] == target) {
                temp.add(can[i]);
                res.add(temp);
                break;
            } else if (can[i] < target) {
                temp.add(can[i]);
                helper(can, i, target - can[i], new ArrayList<>(temp));
            } else {break;}
        }
        return;
    }

    public static List<List<Integer>> combinationSum2(int[] cands, int t) {
        Arrays.sort(cands); // sort candidates to try them in asc order
        List<List<List<Integer>>> dp = new ArrayList<>();
        for (int i = 1; i <= t; i++) { // run through all targets from 1 to t
            List<List<Integer>> newList = new ArrayList(); // combs for curr i
            // run through all candidates <= i
            for (int j = 0; j < cands.length && cands[j] <= i; j++) {
                // special case when curr target is equal to curr candidate
                if (i == cands[j]) newList.add(Arrays.asList(cands[j]));
                    // if current candidate is less than the target use prev results
                else for (List<Integer> l : dp.get(i-cands[j]-1)) {
                    if (cands[j] <= l.get(0)) {
                        List cl = new ArrayList<>();
                        cl.add(cands[j]); cl.addAll(l);
                        newList.add(cl);
                    }
                }
            }
            dp.add(newList);
        }
        return dp.get(t-1);
    }


    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // sort candidates to try them in asc order
        Arrays.sort(candidates);
        // dp[t] stores all combinations that add up to t
        List<List<Integer>>[] dp = new ArrayList[target + 1];


        // build up dp
        for (int t = 0; t <= target; t++) {
            // initialize
            dp[t] = new ArrayList();
            // initialize
            List<List<Integer>> combList = new ArrayList();

            // for each t, find possible combinations
            for (int j = 0; j < candidates.length && candidates[j] <= t; j++) {
                if (candidates[j] == t) {
                    combList.add(Arrays.asList(candidates[j])); // itself can form a list
                } else {
                    for (List<Integer> prevlist : dp[t - candidates[j]]) { // here use our dp definition
                        // i thought it makes more sense to compare with the last element
                        // only add to list when the candidates[j] >= the last element
                        // so the list remains ascending order, can prevent duplicate (ex. has [2 3 3], no [3 2 3])
                        // equal is needed since we can choose the same element many times
                        if (candidates[j] >= prevlist.get(prevlist.size() - 1)) {
                            List temp = new ArrayList(prevlist); // temp is needed since
                            temp.add(candidates[j]); // cannot edit prevlist inside 4eeach looop
                            combList.add(temp);
                        }
                    }
                }
            }
            dp[t] = combList;
        }
        return dp[target];
    }
}
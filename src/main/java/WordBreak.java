import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordBreak {

    static Map<String, Boolean> map = new HashMap();

    public static void main(String[] args) {
        String s = "abcd";
        String wordDict[] = {"a", "abc", "b", "cd"};

        String s1 = "catsandog";
        String wordDict1[] = {"cats", "dog", "sand", "and", "cat"};

        System.out.println(wordBreakAgain(s, Arrays.asList(wordDict1)));
    }

    //Top Down DP
    public static boolean wordBreak(String s, List<String> wordDict) {

        if(wordDict.contains(s))
            return true;
        if(map.containsKey(s))
            return map.get(s);
        for(int i = 1; i <= s.length(); i++) {
            String left = s.substring(0, i);
            if(wordDict.contains(left) && wordBreak(s.substring(i), wordDict)) {
                map.put(s, true);
                return true;
            }
        }
        map.put(s, false);
        return false;
    }

    //Bottom UP DP
    public static boolean wordBreakAgain(String s, List<String> wordDict) {

        boolean[] dp = new boolean[s.length() + 1];
        Set<String> wordDictSet = new HashSet(wordDict);
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                if(wordDictSet.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}



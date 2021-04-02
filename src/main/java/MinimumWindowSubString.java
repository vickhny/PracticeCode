import java.util.Comparator;
import java.util.HashMap;

public class MinimumWindowSubString {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";

        System.out.println(minWindowBest(s, t));
        System.out.println(minWindowWithOrderOfT(s, t));
    }


    public static String minWindowBest(String str, String pattern) {

        HashMap<Character, Integer> patMap = new HashMap<>();
        int start = 0;
        int minStart = 0;
        int minStringLength = Integer.MAX_VALUE;
        int matchedElements = 0;

        if (pattern.length() > str.length()) {
            return "";
        }

        for (int i = 0; i < pattern.length(); i++) {
            patMap.put(pattern.charAt(i), patMap.getOrDefault(pattern.charAt(i), 0) + 1);
        }

        for (int end = 0; end < str.length(); end++) {

            char currentChar = str.charAt(end);

            if (patMap.containsKey(currentChar)) {
                patMap.put(currentChar, patMap.get(currentChar) - 1);
                if (patMap.get(currentChar) >= 0) {
                    matchedElements++;
                }
            }

            while (matchedElements == pattern.length()) {

                if (end - start + 1 < minStringLength) {
                    minStringLength = end - start + 1;
                    minStart = start;
                }

                char charAtStart = str.charAt(start++);

                if (patMap.containsKey(charAtStart)) {
                    if (patMap.get(charAtStart) == 0) {
                        matchedElements--;
                    }
                    patMap.put(charAtStart, patMap.get(charAtStart) + 1);
                }
            }
        }
        if (minStringLength > str.length()) {
            return "";
        }
        return str.substring(minStart, minStart + minStringLength);
    }


    /*    S = "abcdebdde", T = "bde"
        Output: "bcde"
        Explanation:
                "bcde" is the answer because it occurs before "bdde" which has the same length.
                "deb" is not a smaller window because the elements of T in the window must occur in order.*/
    public static String minWindowWithOrderOfT(String S, String T) {
        int start = 0;
        String result = "";

        while (start < S.length()) {
            int j = 0;

            for (int i = start; i < S.length(); i++) {
                if (S.charAt(i) == T.charAt(j) && j == 0) {
                    start = i;
                }

                if (S.charAt(i) == T.charAt(j)) {
                    j++;
                }

                if (j == T.length()) {
                    if (result.equals("") || (i - start + 1) < result.length()) {
                        result = S.substring(start, i + 1);
                    }
                    start = start + 1;
                    break;
                }

                if (i == S.length() - 1) {
                    return result;
                }
            }
        }

        return result;
    }
}

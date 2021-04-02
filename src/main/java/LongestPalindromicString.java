import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class LongestPalindromicString {

    public static void main(String[] args) {
        String s = "eababbabbacdegdgvcdhcdbhbdbjdffdbjfvbhfhibbufbuzub fiubfbubfibbzbh h cbhchhdvbuhudhhvfyufyebjdvbvydshjVBJjkbLJshdjvbchjxvjhCVVjhvLVHxvHJXVJDvjhvhdsdjhvbxjhchvbhcbvdfudfbnb b nb cbhbhfhbhbfhbhbhbhdbxmcz,jbsb,ks";
        LongestPalindromicString l = new LongestPalindromicString();
        //System.out.println(l.longestPalindromeBestofbest(s));
        System.out.println(findLongestPalindromicSubstring(s,s.length()));

    }

    public String longestPalindrome(String s) {
        long sTime = Calendar.getInstance().getTimeInMillis();
        String longestPalindrome = "";
        StringBuilder sb = new StringBuilder();
        sb.append(s);

        if (s.equalsIgnoreCase(sb.reverse().toString())){
            return s;
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                if (s.substring(i, j).equalsIgnoreCase(new StringBuilder(s.substring(i, j)).reverse().toString()) && longestPalindrome.length() < s.substring(i, j).length()) {
                    longestPalindrome = s.substring(i, j);
                }
            }
        }

        long eTime = Calendar.getInstance().getTimeInMillis();
        System.out.println(eTime-sTime);
        return longestPalindrome;
    }

    public String longestPalindromeBestofbest(String str){
//eabbabbacede
        // eababbabbacde
        long sTime = Calendar.getInstance().getTimeInMillis();
        Map<Character, List<Integer>> map = new HashMap<>();
        AtomicReference<String> longest = new AtomicReference<>("");

        for (int i = 0; i < str.length(); i++) {

            if (map.containsKey(str.charAt(i) )){
                map.get(str.charAt(i)).add(i);
                List<Integer> indexes = map.get(str.charAt(i));
                int finalI = i;
                indexes.forEach(integer -> {
                    String sub = str.substring(integer, finalI +1);
                    if (sub.equalsIgnoreCase(new StringBuilder(sub).reverse().toString()) && sub.length() > longest.get().length()){
                        longest.set(sub);
                    }
                });

            }else {
                List<Integer> indexes = new LinkedList<>();
                indexes.add(i);
                map.put(str.charAt(i), indexes);
            }
        }
        long eTime = Calendar.getInstance().getTimeInMillis();
        System.out.println(eTime-sTime);
        return longest.get();
    }

    // Expand in both directions of `low` and `high` to find
    // maximum length palindrome
    public static String expand(String str, int low, int high)
    {
        int len = str.length();

        // expand in both directions
        while (low >= 0 && high < len &&
                (str.charAt(low) == str.charAt(high))) {
            low--;
            high++;
        }

        // return palindromic substring
        return str.substring(low + 1, high);
    }

    // Function to find the longest palindromic substring in `O(n²)` time
    // and `O(1)` space
    public static String findLongestPalindromicSubstring(String str, int len)
    {
        long sTime = Calendar.getInstance().getTimeInMillis();

        // `max_str` stores the maximum length palindromic substring
        // found so far

        String max_str = "", curr_str;

        // `max_length` stores the maximum length of palindromic
        // substring found so far

        int max_length = 0, curr_length;

        // consider every character of the given string as a midpoint and expand
        // in both directions to find maximum length palindrome

        for (int i = 0; i < len; i++)
        {
            // find the longest odd length palindrome with `str[i]` as a midpoint

            curr_str = expand(str, i, i);
            curr_length = curr_str.length();


            // update maximum length palindromic substring if odd length
            // palindrome has a greater length

            if (curr_length > max_length)
            {
                max_length = curr_length;
                max_str = curr_str;
            }

            // Find the longest even length palindrome with str[i] and
            // str[i+1] as midpoints. Note that an even length palindrome
            // has two midpoints.

            curr_str = expand(str, i, i + 1);
            curr_length = curr_str.length();

            // update maximum length palindromic substring if even length
            // palindrome has a greater length

            if (curr_length > max_length)
            {
                max_length = curr_length;
                max_str = curr_str;
            }
        }
        long eTime = Calendar.getInstance().getTimeInMillis();
        System.out.println(eTime-sTime);
        return max_str;
    }
}

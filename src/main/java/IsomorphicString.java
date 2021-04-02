import java.util.HashMap;
import java.util.HashSet;

/*Given two strings s and t, determine if they are isomorphic. Two strings are isomorphic if the characters in s can be replaced to get t.

        For example,"egg" and "add" are isomorphic, "foo" and "bar" are not.*/
public class IsomorphicString {

    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg","add"));
        System.out.println(isIsomorphic("foo","bar"));
    }

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
            } else {
                map.put(c1, c2);
            }
        }

        HashSet<Character> set = new HashSet<>(map.values());
        if (set.size() == map.values().size()) {
            return true;
        }

        return false;
    }
}

public class StringPermutations {

    int counter = 0;

    public static void main(String[] args) {
        String str = "ABC";
        int n = str.length();
        StringPermutations permutation = new StringPermutations();
        //permutation.permute(str, 0, n - 1);
        findPermutation(str);
    }

    private static void findPermutation(String str) {

        if (str == null || str.length() == 0) {
            System.out.println("String length is 0, Invalid input!!");
            return;
        }

        permutation("", str);
    }

    private static void permutation(String prefix, String remaining) {

        //Note : If you will remove if condition you will get all possible sub sequence from given string
        if (remaining.length() == 0)
        {
            System.out.println(prefix);
        }

        for (int i = 0; i < remaining.length(); i++) {
            permutation(prefix + remaining.charAt(i), remaining.substring(0, i) + remaining.substring(i + 1, remaining.length()));
        }
    }
}
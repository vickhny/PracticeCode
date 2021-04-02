/*Combinations in a String of Digits

        Given an input string of numbers, find all combinations of numbers that can be formed using digits in the same order.
        Examples:

        Input : 123
        Output :1 2 3
        1 23
        12 3
        123

        Input : 1234
        Output : 1 2 3 4
        1 2 34
        1 23 4
        1 234
        12 3 4
        12 34
        123 4
        1234*/
public class CombinationOfStringDigits {

    static void printCombinations(char s[]) {

        // find length of char array
        int l = s.length;

        // we can give space between characters
        // ex. ('1' & '2') or ('2' & '3') or
        // ('3' & '4') or ('3' & '4') or all
        // that`s why here we have maximum
        // space length - 1
        for (int i = 0;
             i < Math.pow(2, l - 1); i++) {
            int k = i, x = 0;

            // first character will be printed
            // as well
            System.out.print(s[x]);
            x++;
            for (int j = 0;
                 j < s.length - 1; j++) {

                // if bit is set, means provide
                // space
                if (k % 2 == 1)
                    System.out.print(" ");
                k = k >> 1;
                System.out.print(s[x]);

                // always increment index of
                // input string
                x++;
            }
            System.out.print("\n");
        }
    }


    // Driver Code
    public static void main(String[] args) {
        char input[] = "1214".toCharArray();
        printCombinations(input);
    }
}

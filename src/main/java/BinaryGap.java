/*BinaryGap
        VIEW START
        Find longest sequence of zeros in binary representation of an integer.*/
public class BinaryGap {

    public static void main(String[] args) {

        int longestGap = 0;
        int max = 0;

        int n = 1041;

        int counter = 0;

        boolean flag = false;

        while (n >= 1 && counter <= 1) {

            if (n % 2 == 1 && flag == false) {
                flag = true;
            }
            if (flag && n % 2 == 0) {
                longestGap++;
            }

            n = n / 2;
            if (n % 2 == 1) {
                flag = false;
                max = Math.max(max, longestGap);
                longestGap = 0;

            }
            if (n == 1) {
                counter++;
            }
        }
        System.out.println(max);
    }
}
//

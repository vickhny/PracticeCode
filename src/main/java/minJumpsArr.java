import java.util.Scanner;

public class minJumpsArr {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        /* input size of the array */
        int n = scn.nextInt();
        int[] arr = new int[n];

        /* input elements in the array */
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        System.out.println(solve(arr));

    }

    public static boolean solve(int[] arr) {
        /* dp array to store the result of every index, that is,
         * if they are reachable or not, true indicates that there
         * exists one or more jump combinations to reach that index,
         * and no jump combination exist in case of a false*/
        boolean[] dp = new boolean[arr.length];

        /* first index is always reachable, as this is the position
         * we start from*/
        dp[0] = true;

        /*finding result for every index*/
        for (int currPos = 0; currPos < arr.length; currPos++) {
            /* if the index we are currently at is not reachable from
             * 0th index, then we obviously can not make further jumps
             * from this position.
             * Also, number of jumps possible from the current position
             * needs to be greater than zero, as in case of zero, we can
             * not move to any other position by making a jump*/
            if (dp[currPos] && arr[currPos] > 0) {
                int maxJumps = arr[currPos];
                /* mark all the reachable positions from current position
                 * true because, if they can be reached from an intermediate
                 * spot, and that intermediate spot can be reached from zero,
                 * then the jumped position will also be reachable from zeroth
                 * index*/
                for (int jump = 1; jump <= maxJumps; jump++) {
                    if (currPos + jump < dp.length) {
                        dp[currPos + jump] = true;
                    }
                }
            }
        }

        /*return the result of last index of the array if it is reachable
         * from zeroth index or not*/
        return dp[arr.length - 1];
    }

}

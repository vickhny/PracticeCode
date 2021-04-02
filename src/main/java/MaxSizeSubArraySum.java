import java.util.HashMap;

public class MaxSizeSubArraySum {
/*  Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

    Note:
    The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.


    Example 1:
    Given nums = [1, -1, 5, -2, 3], k = 3,
            return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)*/

    public static void main(String[] args) {
        int arr[] = {1, 4, 20, 3, 10};
        int sum = 33;
        int i = 0;
        int j = 0;
        System.out.println(findMinSubArraySum(arr, sum, i, j));

    }

    private static int findMinSubArraySum(int[] arr, int sum, int i, int j) {
        int max = 0;

        while (i < arr.length || j < arr.length) {

            if (max < sum) {
                max += arr[i];
                i++;
            }
            if (max > sum) {
                max -= arr[j];
                j++;
            }

            if (max == sum) {
                System.out.println(i + "  " + j);
                return Math.abs(i - j);
            }
        }
        return -1;
    }
}

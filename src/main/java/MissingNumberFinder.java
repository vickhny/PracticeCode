import java.util.Arrays;

public class MissingNumberFinder {
    public static void main(String args[]) throws Exception {
        System.out.println("Test #1 : Missing number in sorted array ");
        int[] input = new int[]{1, 2, 3, 4, 6};
        int missing = missingNumberFromSortedArray(input);

        int missingEasy = getMissingNo(input, input.length);
        System.out.println("Missing number from array : " + Arrays.toString(input) + " is : " + missing);

        System.out.println("missingEasy number from array : " + Arrays.toString(input) + " is : " + missingEasy);

        int arr[] = {2, 3, 5};
        int missingPositive = missingSmallestPositiveNum(arr);
        System.out.println("The smallest positive missing number is " + missingPositive);
    }

    static int getMissingNo(int a[], int n) {
        int i, total;
        total = (n + 1) * (n + 2) / 2;
        for (i = 0; i < n; i++)
            total -= a[i];
        return total;
    }

    //1 2 3 4 = 4*5/2

    public static int missingNumberFromSortedArray(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            throw new IllegalArgumentException("Null or Empty array not permitted");
        }
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int middle = (right + left) >> 1;
            if (numbers[middle] != middle) {
                if (middle == 0 || numbers[middle - 1] == middle - 1) {
                    return middle;
                }
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        throw new RuntimeException("No missing number");
    }

    //{ 2, 3, -7, 6, 8, 1, -10, 15 }  || {0, 10, 2, -10, -20}; || {1, 1, 2, 0, -1, -2}; || {-1, -5, -7}
    public static int missingSmallestPositiveNum(int a[]) throws Exception {
        Arrays.sort(a);

        int num = Integer.MAX_VALUE;

        int counter = 0;

        boolean flag = false;

        if (a[0] <= 0) {
            flag = true;
        }

        for (int i = 0; i < a.length - 1; i++) {

            if (a[i] > 0) {

                counter++;

                if (counter == 1 && flag && a[i] > 1) {
                    return 1;
                }

                if (a[i] == a[i + 1]) {
                    continue;
                }

                if (a[i] + 1 != a[i + 1]) {
                    num = a[i] + 1;
                    return num;
                }
            }
        }

        if (num == Integer.MAX_VALUE && counter > 0) {
            return a[a.length - 1] + 1;
        }
        if (counter == 0) {
            return 1;
        }
        throw new Exception("Invalid Argument!!");
    }
}

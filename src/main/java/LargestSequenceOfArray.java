import java.util.Set;
import java.util.TreeSet;

public class LargestSequenceOfArray {

    static int numbers[];
    static int length;

    public static void main(String[] args) {
        //System.out.println(reverse(-2147483648));

        int arr[] = {1, 2, 0, 1};

        numbers = arr;

        sort(numbers);

        int count = 1;
        int maxCount = 0;
        int i;
        for (i = 0; i < numbers.length; i++) {
            while (i < numbers.length - 1 && (numbers[i] + 1 == numbers[i + 1] || numbers[i] == numbers[i + 1])) {
                if (numbers[i] != numbers[i + 1]) {
                    count++;
                }
                i++;
            }
            maxCount = Math.max(maxCount, count);
            count = 1;
        }
        System.out.println(maxCount);
    }

    public static void sort(int[] values) {
        // check for empty or null array
        if (values == null || values.length == 0) {
            return;
        }
        numbers = values;
        length = values.length;
        quicksort(0, length - 1);
    }

    private static void quicksort(int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = numbers[low + (high - low) / 2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    private static void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    private static int findSequenceLength(int[] arr) {

        Set<Integer> sortedSet = new TreeSet<>();

        for (int i = 0; i < arr.length; i++) {
            sortedSet.add(arr[i]);
        }

        int maxLength = 0;

        for (Integer cur : sortedSet) {
            int curCheck = cur;
            int counter = 0;
            while (sortedSet.contains(curCheck)) {
                counter++;
                curCheck += 1;
            }
            maxLength = Math.max(maxLength, counter);
        }
        return maxLength;
    }

}

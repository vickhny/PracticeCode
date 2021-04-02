import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubArrayGivenSum {

    public static void print(int[] arr, int i, int j) {
        System.out.println(IntStream.range(i, j + 1)
                .mapToObj(k -> arr[k])
                .collect(Collectors.toList()));
    }

    // Function to find subarrays with the given sum in an array
    public static void findSubarrays(int[] arr, int sum) {
        for (int i = 0; i < arr.length; i++) {
            int sum_so_far = 0;

            // consider all subarrays starting from `i` and ending at `j`
            for (int j = i; j < arr.length; j++) {
                // sum of elements so far
                sum_so_far += arr[j];

                // if the sum so far is equal to the given sum
                if (sum_so_far == sum) {
                    print(arr, i, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, -7, 1, 3, 3, 1, -4};
        int sum = 7;

        findSubarrays(arr, sum);
    }


    //Hashing
    // Utility function to insert <key, value> pair into the multimap
    private static <K, V> void insert(Map<K, List<V>> hashMap, K key, V value) {
        // if the key is seen for the first time, initialize the list
        hashMap.putIfAbsent(key, new ArrayList<>());
        hashMap.get(key).add(value);
    }

    // Utility function to print subarray `A[i, j]`
    public static void printSubarray(int[] A, int i, int j) {
        System.out.println(IntStream.range(i, j + 1)
                .mapToObj(k -> A[k])
                .collect(Collectors.toList()));
    }

    // Function to find subarrays with the given sum in an array
    public static void printAllSubarrays(int[] A, int sum) {
        // create a map for storing the end index of all subarrays with
        // the sum of elements so far
        Map<Integer, List<Integer>> hashMap = new HashMap<>();

        // To handle the case when the subarray with the given sum starts
        // from the 0th index
        insert(hashMap, 0, -1);

        int sum_so_far = 0;

        // traverse the given array
        for (int index = 0; index < A.length; index++) {
            // sum of elements so far
            sum_so_far += A[index];

            // check if there exists at least one subarray with the given sum
            if (hashMap.containsKey(sum_so_far - sum)) {
                List<Integer> list = hashMap.get(sum_so_far - sum);
                for (Integer value : list) {
                    printSubarray(A, value + 1, index);
                }
            }

            // insert (sum so far, current index) pair into the map
            insert(hashMap, sum_so_far, index);
        }
    }
}

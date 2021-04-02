import java.util.HashMap;
import java.util.Map;

public class PrintPairWithGivenSum {

    static void printPairs(int arr[], int n,
                           int sum) {

        // Store counts of all elements in map m
        HashMap<Integer,
                Integer> mp = new HashMap<Integer, Integer>();

        // Traverse through all elements
        for (int i = 0; i < n; i++) {

            // Search if a pair can be formed with
            // arr[i].
            int rem = sum - arr[i];
            if (mp.containsKey(rem)) {
                int count = mp.get(rem);

                for (int j = 0; j < count; j++)
                    System.out.print("(" + rem +
                            ", " + arr[i] +
                            ")" + "\n");
            }
            if (mp.containsKey(arr[i])) {
                mp.put(arr[i], mp.get(arr[i]) + 1);
            } else {
                mp.put(arr[i], 1);
            }
        }
    }

    static void printPair(int arr[],int sum){
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {

            if (!map.containsKey(arr[i])){
                map.put(arr[i],1);
            }
            int rem = sum - arr[i];
            if (map.containsKey(rem)){
                System.out.println("("+arr[i]+","+rem+")");
            }
        }
    }

    // Driver code
    public static void main(String[] args) {
        int arr[] = {1, 5, 7, -1, 5};
        int n = arr.length;
        int sum = 6;
        //printPair(arr,sum);
        printPairs(arr, n, sum);
    }
}

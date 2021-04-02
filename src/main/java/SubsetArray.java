public class SubsetArray {
    // Works with both Integer and String to find subset/sub sequence
    // Same concept can also be possible to find target sum subset
    public static void main(String[] args) {
        int t[] = {1, 2, 3};
        String a[] = {"a", "b", "c"};
        int n = a.length;
        int target = 3;

        //int total = (int) Math.pow(2,n)
        int total = 1 << n ; // Number of possible sets

        for (int i = 0; i < total; i++) {
            //int sum = 0;
            //String s = "";
            System.out.print("[");
            for (int j = 0; j < n; j++) {
                int f = 1 << j; //Represent 0,1,2,4.. in binary for start from 0 and in every iteration it shift 1 by j
                if ((i & f) != 0) //After shifting perform bitwise AND (&) operation for i and j to verify a[j] is a part of subset or not
                {
                    // sum += a[j];
                    // s += a[j];
                    //if (sum == target) {
                    System.out.print(a[j] + " ");
                    // }
                }
            }
            System.out.print("]");
            System.out.println();
        }
    }
}

/*Schedule elevator to reduce the total time taken

        Given an integer k and an array arr[] representing the destination floors for N people waiting currently at the ground floor and k is the capacity of the elevator i.e. maximum number of people it can hold at the same time. It takes 1 unit time for the elevator to reach any consecutive floor from the current floor. The task is to schedule the elevator in a way to minimize the total time taken to get all the people to their destination floor and then return back to the ground floor.

        Examples:

        Input: arr[] = {2, 3, 4}, k = 2
        Output: 12
        Second and the third persons (destination floors 3 and 4) shall go in the first turn taking 8 (4 + 4) unit time. The only person left will take 2 unit time to get to the destination
        And then the elevator will take another 2 unit time to get back to the ground floor.
        Total time taken = 8 + 2 + 2 = 12

        Input: arr[] = {5, 5, 4}, k = 3
        Output: 10
        Every person can get on the elevator at the same time
        Time required will be 10 (5 + 5).*/

class ReduceEvevatorTimeTaken {

    // Function to return the minimum time taken
// by the elevator when operating optimally
    static int minTime(int n, int k, int a[]) {
        // Sort in descending order
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] < a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }


        int minTime = 0;

        // Iterate through the groups
        for (int i = 0; i < n; i += k)

            // Update the time taken for each group
            minTime += (2 * a[i]);

        // Return the total time taken
        return minTime;
    }

    // Driver code
    public static void main(String args[]) {
        int k = 2;
        int arr[] = {2, 3, 4};
        int n = arr.length;
        System.out.println(minTime(n, k, arr));
    }
}

public class sort012 {

    public static void main(String[] args) {

        int[] a = {1, 0, 2, 0, 0, 1, 2, 2, 1};
        sort(a);
        for (int val : a) {
            System.out.println(val + " ");
        }

    }

    /*Function to sort the given array*/
    public static void sort(int[] a) {
        /* array to keep the count of 0s, 1s, 2s*/
        int[] freq = new int[3];

        /* traverse the given array for filling the
         * frequency array*/
        for (int val : a) {
            freq[val]++;
        }
        /*pointer to traverse the given array again*/
        int pointer = 0;
        for (int i = 0; i < freq.length; i++) {
            /* loop to exhaust the number of 0s, 1s, 2s*/
            while (freq[i]-- > 0) {
                /*at the current pointer plot the current number*/
                a[pointer] = i;
                /*increment the pointer*/
                pointer++;
            }
        }
    }
}

import java.util.Arrays;

class Mergesort {
    // merge arrays : intArray[start...mid] and intArray[mid+1...end]
    public static void merge(int[] intArray, int[] temp, int start, int mid, int end) {
        int k = start, i = start, j = mid + 1;

        // traverse through elements of left and right arrays
        while (i <= mid && j <= end) {
            if (intArray[i] < intArray[j]) {
                temp[k++] = intArray[i++];
            } else {
                temp[k++] = intArray[j++];
            }
        }

        // Copy remaining elements
        while (i <= mid) {
            temp[k++] = intArray[i++];
        }

        // copy temp array back to the original array to reflect sorted order
        for (i = start; i <= end; i++) {
            intArray[i] = temp[i];
        }
    }

    // sorting intArray[low...high] using iterative approach
    public static void mergeSort(int[] intArray) {
        int low = 0;
        int high = intArray.length - 1;

        // sort array intArray[] using temporary array temp
        int[] temp = Arrays.copyOf(intArray, intArray.length);

        // divide the array into blocks of size m
        // m = [1, 2, 4, 8, 16...]
        for (int m = 1; m <= high - low; m = 2 * m) {
            for (int i = low; i < high; i += 2 * m) {
                int start = i;
                int mid = i + m - 1;
                int end = Integer.min(i + 2 * m - 1, high);
                //call merge routine to merge the arrays
                merge(intArray, temp, start, mid, end);
            }
        }
    }

    public static void main(String[] args) {
        //define array to be sorted
        int[] intArray = {10, 23, -11, 54, 2, 9, -10, 45};
        //print the original array
        System.out.println("Original Array : " + Arrays.toString(intArray));
        //call mergeSort routine
        mergeSort(intArray);
        //print the sorted array
        System.out.println("Sorted Array : " + Arrays.toString(intArray));
    }
}
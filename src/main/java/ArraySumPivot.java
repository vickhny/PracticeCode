public class ArraySumPivot {
    static int sum = 0;

    public static void main(String args[]) {
        int a[] = {3, 5, 7, 10, 7, 15, 1, 4, 2, 3};

        int a1[] = {1, 2, 5, 2, 3, 3, 4};
        System.out.println(findPivotIndexA(a));
    }

    private static int findPivotIndexA(int[] a) {

        if (a.length == 0) {
            return -1;
        }


        for (int i = 1; i < a.length; i++) {
            a[i] += a[i - 1];
        }

        int last = a.length - 1;

        if (a[last] - a[0] == 0) {
            return 0;
        }

        //1, 2, 5, 2, 3, 3, 4
        //1, 3, 8, 10, 13, 16, 20
        for (int i = 1; i < a.length; i++) {
            if (a[last] - a[i] == a[i - 1]) {
                return i;
            }
        }
        return -1;
    }
}

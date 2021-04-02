public class Sort012Array {

    public static void main(String[] args) {
        int a[] = {1, 0,1, 2, 1, 0, 2};
        int b[] = new int[a.length];

        int i = 0, j = 0, k = 0;

        for (int l = 0; l < a.length; l++) {
            if (a[l] == 0) {
                i++;
            }
            if (a[l] == 1) {
                j++;
            }
            if (a[l] == 2) {
                k++;
            }
        }
        int l =0;
        for (l = 0; l < i; l++) {
            b[l] = 0;
            System.out.print(b[l]);
        }
        for (l = 0; l < j; l++) {
            b[l] = 1;
            System.out.print(b[l]);
        }
        for (l = 0; l < k; l++) {
            b[l] = 2;
            System.out.print(b[l]);
        }
    }
}

import java.util.Arrays;

public class ZigZagArray {

    public static void main(String[] args) {
        int a[] = {3, 4, 6, 2, 1, 8, 9};
        int i = 0;
        int length = a.length;
        boolean flag = true;

        while (i < length - 1) {

            if (flag) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
            } else {
                if (a[i] < a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
            }
            flag = flag == true ? false : true;
            i++;
        }

        Arrays.stream(a).forEach(n -> System.out.print(n));
    }
}

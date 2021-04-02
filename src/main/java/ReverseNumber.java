public class ReverseNumber {

    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
    }

    public static int reverse(int x) {

        String result = "";
        boolean flag = true;
        boolean isNegative = false;

        if (x > ((1 << 31) - 1)) {
            return 0;
        }
        if (x <= Math.pow(-2, 31)) {
            return 0;
        }

        if (x < 0) {
            isNegative = true;
            x = Math.abs(x);
        }

        if (x >= 0 && x < 10) {
            return x;
        }

        while (flag) {

            result += x % 10;
            x = x / 10;
            if (x < 10) {
                result += x;
                flag = false;
            }
        }

        if (Long.parseLong(result) > ((1 << 31) - 1)) {
            return 0;
        }
        if (isNegative && (-1 * Long.parseLong(result)) < Math.pow(-2, 31)) {
            return 0;
        }


        if (isNegative) {
            return Integer.parseInt(result) * -1;
        }
        return Integer.parseInt(result);
    }
}
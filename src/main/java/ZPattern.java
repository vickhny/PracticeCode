public class ZPattern {

    public static void main(String[] args) {
        int len = 6;
        for (int i = len; i > 0; i--) {
            if (i == len) {
                for (int j = 0; j < len; j++) {
                    System.out.print("*");
                    System.out.print(" ");
                }
                System.out.println();
            }
            for (int j = 1; j < i - 1; j++) {
                System.out.print("  ");
            }
            System.out.print("*");
            System.out.println();

            if (i == 3) {
                for (int j = 0; j < len - 1; j++) {
                    System.out.print("*");
                    System.out.print(" ");
                }
            }
        }
    }
}

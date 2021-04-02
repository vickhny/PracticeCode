public class Knapsack_PackageIndex {

    public static void main(String[] args) {
        int[] val = {10, 40, 30, 50};
        double[] wt = {5, 4, 6, 3};
        int w = 10;
        boolean visited[] = new boolean[val.length];

        System.out.println(maximizeCost(w, wt, val, val.length, visited));

        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == true) {
                System.out.println(i + " ");
            }
        }

    }

    /**
     * @param W       the maximum capacity of the package
     * @param wt      array of items weights
     * @param cost    array of items values
     * @param N       total number of items
     * @param visited keeping track of items which can be processed
     * @return maximum value for the package
     */
    private static Integer maximizeCost(Integer W, double wt[], int cost[], int N, boolean visited[]) {

        // Populate base cases
        int[][] mat = new int[N + 1][W + 1];
        for (int r = 0; r < W + 1; r++) {
            mat[0][r] = 0;
        }
        for (int c = 0; c < N + 1; c++) {
            mat[c][0] = 0;
        }

        for (int item = 1; item <= N; item++) {
            for (int capacity = 1; capacity <= W; capacity++) {
                int maxValWithoutCurr = mat[item - 1][capacity];
                int maxValWithCurr = 0;

                Double weightOfCurr = wt[item - 1];

                if (capacity >= weightOfCurr) {
                    maxValWithCurr = cost[item - 1];

                    int remainingCapacity = (int) Math.ceil(capacity - weightOfCurr);
                    maxValWithCurr += mat[item - 1][remainingCapacity];
                }

                mat[item][capacity] = Math.max(maxValWithoutCurr, maxValWithCurr);
            }
        }

        int i;
        double w;
        int res = mat[N][W];

        w = W;
        for (i = N; i > 0 && res > 0; i--) {
            if (res == mat[i - 1][(int) Math.ceil(w)])
                continue;
            else {
                visited[i - 1] = true;
                // Since this cost is included its
                // value is deducted
                res = res - cost[i - 1];
                w = w - wt[i - 1];
                if (w < 0) {
                    break;
                }
            }
        }

        return mat[N][W];
    }
}

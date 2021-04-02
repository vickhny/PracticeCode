import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        //int prices[] = {7, 1, 5, 3, 6, 4};
        int a[] = {2, 7, 4, 8, 9, 12, 15, 1};
        binarySearch(a, 0, a.length - 1, 15);


        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(2);

        callQueue(queue);

        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        System.out.println("Adding to queue");
        queue.add(1);
        queue.put(2);
        queue.put(3);
        queue.add(4);



    }

    private static void binarySearch(int[] a, int first, int last, int key) {

        int mid = (first + last) / 2;

        while (first <= last) {

            if (a[mid] < key) {
                first = mid + 1;
            } else if (a[mid] == key) {
                System.out.println("Key found at index : " + mid);
                break;
            } else {
                last = mid - 1;
            }
            mid = (first + last) / 2;
        }

        if (first > last){
            System.out.println("Key not present!!");
        }

    }

    static void callQueue(PriorityBlockingQueue<Integer> queue) {
        new Thread(() -> {
            System.out.println("Polling...");

            try {
                Integer poll = queue.take();
                System.out.println("Polled: " + poll);
                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                if (!queue.isEmpty()) {
                    callQueue(queue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static int buyStockProfit(int[] prices, int n) {

        if (n == 1) {
            return 0;
        }
        int i = 0;
        int profit = 0;
        List<StockInterval> sol = new LinkedList<>();
        StockInterval interval = new StockInterval();

        //7, 1, 5, 3, 6, 4
        while (i < n) {

            if (i < n && prices[i] < prices[i + 1] && interval.buy != 0) {
                interval.sell = i;
                sol.add(interval);
                interval = new StockInterval();
                i++;
                continue;
            }

            if (i < n && prices[i] > prices[i + 1]) {
                if (interval.buy != 0) {
                    interval.sell = i;
                    sol.add(interval);
                    interval = new StockInterval();
                }
                i++;
                continue;
            }

            if (i == n) {
                break;
            }
            interval.buy = i;

            if (i < n && prices[i] < prices[i + 1]) {
                i++;
                continue;
            }
            i++;
        }

        for (StockInterval intervals : sol) {
            profit += prices[intervals.sell] - prices[intervals.buy];
        }

        return profit;
    }

    static class StockInterval {
        int buy;
        int sell;
    }
}

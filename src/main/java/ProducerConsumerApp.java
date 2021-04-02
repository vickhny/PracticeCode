import java.util.LinkedList;

public class ProducerConsumerApp {

    public static void main(String[] args) throws InterruptedException {

        PC pc = new PC();

        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }

    private static class PC {

        int capacity = 2;
        LinkedList<Integer> list = new LinkedList<>();


        public void produce() throws InterruptedException {
            int value = 0;

            while (true) {
                synchronized (this) {
                    while (list.size() == capacity) {
                        wait();
                    }

                    System.out.println("Producer produced value- " + value);
                    list.add(value++);
                    notify();

                    Thread.sleep(1000);
                }
            }
        }

        public void consume() throws InterruptedException {
            while (true) {
                synchronized (this) {
                    while (list.size() == 0) {
                        wait();
                    }

                    System.out.println("Consumer consumed value- " + list.removeFirst());
                    notify();
                    Thread.sleep(1000);
                }
            }
        }
    }
}

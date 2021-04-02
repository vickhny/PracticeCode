public class OddEvenPrintMain {

    boolean odd;
    int count = 1;
    int MAX = 20;

    public static void main(String[] args) {

        OddEvenPrintMain oep = new OddEvenPrintMain();
        oep.odd = true;
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    oep.printEvenCount();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    oep.printOddCount();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void printOdd() {
        synchronized (this) {
            while (count < MAX) {
                System.out.println("Checking odd loop");

                while (!odd) {
                    try {
                        System.out.println("Odd waiting : " + count);
                        wait();
                        System.out.println("Notified odd :" + count);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                System.out.println("Odd Thread :" + count);
                count++;
                odd = false;
                notify();
            }
        }
    }

    public void printEven() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        synchronized (this) {
            while (count < MAX) {
                System.out.println("Checking even loop");

                while (odd) {
                    try {
                        System.out.println("Even waiting: " + count);
                        wait();
                        System.out.println("Notified even:" + count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Even thread :" + count);
                count++;
                odd = true;
                notify();

            }
        }
    }

    void printOddCount() throws InterruptedException {
        synchronized (this) {
            while (count < MAX) {
                while (!odd) {
                    wait();
                }
                System.out.println("Odd: " + count);
                count++;
                odd = false;
                notify();
            }
        }
    }

    void printEvenCount() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            while (count < MAX) {
                while (odd) {
                    wait();
                }
                System.out.println("Even: " + count);
                count++;
                odd = true;
                notify();
            }
        }
    }
}
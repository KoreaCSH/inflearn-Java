package section3;

import static util.MyLogger.*;

public class StartTest3Main {

    public static void main(String[] args) {
        Thread threadA = new Thread(new PrintWork("A", 1000), "Thread-A");
        Thread threadB = new Thread(new PrintWork("B", 500), "Thread-B");

        threadA.start();
        threadB.start();
    }

    static class PrintWork implements Runnable {

        private String printStr;
        private long waitMs;

        public PrintWork(String printStr, long waitMs) {
            this.printStr = printStr;
            this.waitMs = waitMs;
        }

        @Override
        public void run() {
            while (true) {
                log(printStr);
                try {
                    Thread.sleep(waitMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}

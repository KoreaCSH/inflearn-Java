package section3;

import java.util.stream.IntStream;

import static util.MyLogger.log;

public class StartTest2Main {

    public static void main(String[] args) {
        Thread thread = new Thread(new CounterRunnable(), "counter");
        thread.start();
    }

    static class CounterRunnable implements Runnable {
        @Override
        public void run() {
            int start = 1;
            int end = 5;
            IntStream.rangeClosed(start, end)
                    .forEach((i) -> {
                        log(i);
                        if (i < end) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        }
    }

}

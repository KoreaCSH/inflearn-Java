package section3;

import util.MyLogger;

import java.util.stream.IntStream;

import static util.MyLogger.*;

public class CounterThread extends Thread {

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

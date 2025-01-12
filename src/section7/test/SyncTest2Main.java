package section7.test;

import static util.MyLogger.*;

public class SyncTest2Main {

    public static void main(String[] args) {
        MyCounter counter = new MyCounter();

        Runnable task = () -> {
            counter.count();
        };

        Thread thread1 = new Thread(task, "counter-1");
        Thread thread2 = new Thread(task, "counter-2");

        thread1.start();
        thread2.start();
    }

    // 지역 변수는 절대 다른 스레드와 공유되지 않는다.

    static class MyCounter {

        public void count() {
            int localValue = 0;
            for (int i = 0; i < 1000; i++) {
                localValue += 1;
            }
            log("localValue = " + localValue);
        }
    }

}

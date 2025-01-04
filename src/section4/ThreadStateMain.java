package section4;

import static util.MyLogger.*;

public class ThreadStateMain {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable(), "myThread");
        log("myThread.state1() " + thread.getState());

        // Thread 의 State : NEW -> RUNNABLE -> TERMINATED

        thread.start();
        Thread.sleep(1000);
        log("myThread.state3() " + thread.getState());

        Thread.sleep(4000);
        log("myThread.state3() " + thread.getState());
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                log("start");
                log("myThread.state2() " + Thread.currentThread().getState());

                log("sleep start");
                Thread.sleep(3000);
                log("sleep end");
                log("myThread.state5() " + Thread.currentThread().getState());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log("end");
        }
    }

}

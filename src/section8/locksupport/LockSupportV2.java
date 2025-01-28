package section8.locksupport;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportV2 {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new ParkTest(), "Thread-1");
        thread1.start();

        sleep(100);
        log("Thread-1 state: " + thread1.getState());
    }

    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");

            // parkNanos() 시 RUNNABLE -> TIMED_WAITING
            LockSupport.parkNanos(2_000_000_000);
            // Nano 초 이우 자동으로 unpark() 된다.
            log("park 종료, status : " + Thread.currentThread().getState());
            log("인터럽트 상태 : " + Thread.currentThread().isInterrupted());
        }
    }

}

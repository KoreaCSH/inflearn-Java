package section8.locksupport;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.*;
import static util.ThreadUtils.sleep;

public class LockSupportV1 {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new ParkTest(), "Thread-1");
        thread1.start();

        sleep(100);
        log("Thread-1 state: " + thread1.getState());

        // 1. unpark test
        log("main -> unpark(Thread-1)");
        LockSupport.unpark(thread1);


        // park() 를 호출하여 스스로 대기상태에 빠질 수 있지만,
        // 대기 상태의 스레드는 자신의 코드를 실행할 수 없기에 unpark() 에는 매개변수 존재한다.

        // 2. interrupt test
        // interrupt() 로도 WAITING 상태의 스레드를 깨울 수 있다.
        /**
        log("main -> interrupt(Thread-1)");
        thread1.interrupt();
         */
    }

    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");

            // park() 시 RUNNABLE -> WAITING
            LockSupport.park();

            log("park 종료, status : " + Thread.currentThread().getState());
            log("인터럽트 상태 : " + Thread.currentThread().isInterrupted());
        }
    }

}

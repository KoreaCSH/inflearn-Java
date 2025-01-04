package section5.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");

        thread.start();
        sleep(100);

        log("작업 중단 지시 : thread.interrupt()");
        thread.interrupt();
        log("Work 스레드 인터럽트 상태1 : " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            log("작업 시작");
            while (!Thread.currentThread().isInterrupted()) {
                log("작업 중");
            }

            // interrupt() 를 호출하면 isInterrupted() = true 가 되었다가,
            log("Work 스레드 인터럽트 상태2 : " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 Start");
                Thread.sleep(1000);
                log("자원 정리 End");
            } catch (InterruptedException e) {
                // InterruptedException 가 catch 될 때 다시 isInterrupted() = false 된다.
                log("자원 정리 중 인터럽트 발생");
                log("Work 스레드 인터럽트 상태3 : " + Thread.currentThread().isInterrupted());
            }
            log("작업 종료");
        }
    }

}

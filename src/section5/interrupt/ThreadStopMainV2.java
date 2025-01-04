package section5.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");

        thread.start();
        sleep(4000);

        log("작업 중단 지시 : thread.interrupt()");
        // interrupt() 메서드를 실행하면 해당 스레드에 InterruptedException 를 던지며 Runnable 상태가 된다.
        // 단, 해당 메서드를 실행한다고 해서 바로 InterruptedException 가 발생하는 것은 아니다.
        // InterruptedException 를 던지는 sleep() 과 같은 메서드를 호출하거나 호출 대기 중일 때 예외가 발생한다.
        thread.interrupt();
        log("Work 스레드 인터럽트 상태1 : " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            log("작업 시작");
            int cnt = 0;
            try {
                while (true) {
                    log(++cnt + "번 째 작업 중");
                    // while 과 log 에서 interrupt() 가 실행되었다면
                    // 바로 InterruptedException 가 발생하지 않는다는 문제가 있다.
                    // 이를 V3 에서 보완하자.
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                log("Work 스레드 인터럽트 상태2 : " + Thread.currentThread().isInterrupted());
                log("Interrupt message : " + e.getMessage());
                log("state : " + Thread.currentThread().getState());
            }
            log("Work 스레드 인터럽트 상태3 : " + Thread.currentThread().isInterrupted());
            log("자원 정리");
            log("작업 종료");
        }
    }

}

package section5.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

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

        // 요점 : 자바에서는 인터러븥 예외가 한 번 발생하면, 스레드의 인터럽트 상태를 다시 정상(false) 으로 돌린다.
        // 정상으로 돌리지 않으면 이후에도 계속 인터럽트가 발생하게 되므로 목적을 달성하면 인터럽트 상태를 다시 정상으로 돌려두어야 한다.

        @Override
        public void run() {
            log("작업 시작");
            // Thread.currentThread().isInterrupted() 는 interrupt 여부만 판단한다면,
            // Thread.interrupted 는 true 이면 false 로 변경해준다.
            // 즉, 아래에 있는 자원 정리 코드를 우리가 의도한대로 실행할 수 있다.
            while (!Thread.interrupted()) {
                log("작업 중");
            }

            // interrupt() 를 호출하면 isInterrupted() = true 가 되었다가,
            log("Work 스레드 인터럽트 상태2 : " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 시작");
                Thread.sleep(1000);
                log("자원 정리 성공");
            } catch (InterruptedException e) {
                // InterruptedException 가 catch 될 때 다시 isInterrupted() = false 된다.
                log("자원 정리 중 인터럽트 발생");
                log("Work 스레드 인터럽트 상태3 : " + Thread.currentThread().isInterrupted());
            }
            log("작업 종료");
        }
    }

}

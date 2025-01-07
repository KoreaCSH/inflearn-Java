package section6;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "myTask");
        thread.start();

        sleep(1000);
        log("flag = false 시도");

        // 메모리 가시성 + 캐시 메모리로 인해 myTask thread 는 종료되지 않는다.
        // 메모리 가시성 = 한 스레드가 변경한 값이 다른 스레드에서 언제 보이는 지에 대한 문제
        myTask.flag = false;
        log("Main 종료");
    }

    static class MyTask implements Runnable {

        boolean flag = true;

        @Override
        public void run() {
            log("작업 시작");
            while (flag) {

            }
            log("작업 종료");
        }
    }

}

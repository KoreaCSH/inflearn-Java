package section5.interrupt;

import static util.MyLogger.*;
import static util.ThreadUtils.*;

public class ThreadStopMainV1 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");

        thread.start();
        sleep(4000);

        log("작업 중단 지시 : runFlag = false");
        task.runFlag = false;
    }

    static class MyTask implements Runnable {

        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("작업 시작");
            int cnt = 0;
            while (runFlag) {
                log(++cnt + "번 째 작업 중");
                sleep(3000);
            }
            log("자원 정리");
            log("작업 종료");
        }
    }

}

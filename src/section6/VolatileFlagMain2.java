package section6;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain2 {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "myTask");
        thread.start();

        sleep(1000);
        log("flag = false 시도");

        // 메모리 가시성 + 캐시 메모리로 인해 myTask thread 는 종료되지 않는다.
        // 메모리 가시성 = 한 스레드가 변경한 값이 다른 스레드에서 언제 보이는 지에 대한 문제

        // volatile 키워드를 붙이면 캐시 메모리가 아닌, 메인 메모리에서 바로 값을 읽어오고 쓰기 때문에
        // 의도한대로 코드를 동작시킬 수 있다.
        // 단, 기존 캐시 메모리를 사용하는 것보단 성능이 저하되는 단점이 있다.
        myTask.flag = false;
        log("Main 종료");
    }

    static class MyTask implements Runnable {

        volatile boolean flag = true;

        @Override
        public void run() {
            log("작업 시작");
            while (flag) {

            }
            log("작업 종료");
        }
    }

}

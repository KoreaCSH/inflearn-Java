package section6;

import static util.MyLogger.*;
import static util.ThreadUtils.sleep;

public class VolatileCountMain {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(1000);

        task.flag = false;
        log("flag = " + task.flag + ", count = " + task.count + " in main()");
    }

    static class MyTask implements Runnable {

        // 단, volatile 은 캐시 메모리를 사용하지 않기에, 성능은 저하될 수 있다.
        // 현재 PC 기준 대략 10배 차이
        volatile boolean flag = true;
        volatile long count = 1;

        @Override
        public void run() {
            while (flag) {
                count++;
                // 1억 번에 한번씩 출력
                if (count % 100_000_000 == 0) {
                    log("flag = " + flag + ", count = " + count + " in while()");
                }
            }
            log("flag = " + flag + ", count = " + count + " 종료");
        }
    }

}

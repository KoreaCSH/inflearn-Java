package section4.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV1 {

    public static void main(String[] args) throws InterruptedException {
        log("start");
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        // thread1 이 TERMINATED 상태가 될 때까지 main Thread 는 대기한다.
        thread1.join();

        // thread2 가 TERMINATED 상태가 될 때까지 main Thread 는 대기한다.
        thread2.join();

        log("Task1 + Task2 = " + (task1.result + task2.result));
        log("end");
    }

    static class SumTask implements Runnable {

        int startValue;
        int endValue;
        int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("Job start");
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }

            sleep(2000);

            result = sum;
            log("Job end. result = " + result);
        }
    }

}
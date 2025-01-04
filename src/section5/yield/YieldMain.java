package section5.yield;

import static util.ThreadUtils.sleep;

public class YieldMain {

    private final static int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);

                // sleep() 은 RUNNABLE -> TIMED_WAITING 상태로 변함
                // sleep(1);

                // yield() 를 하면 Runnable 상태는 유지하면서 스케줄링 큐에 들어간다.
                // 단, Java 의 스레드가 Runnable 상태일 때 OS 의 스케줄링은 Running, Ready 상태로 구분할 수 있다.
                // 즉, Java 에서는 Runnable 을 유지하지만, OS 관점에서는 Running -> Ready
                // 단, yield() 는 OS 의 스케줄링에 힌트만 제공할 뿐 강제적인 실행 순서를 제공하진 않는다. (OS 가 최적화)
                Thread.yield();
            }
        }
    }

}

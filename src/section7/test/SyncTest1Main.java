package section7.test;

public class SyncTest1Main {

    // synchronized 의 단점
    // 1. 무한 대기 : `BLOCKED` 상태의 스레드는 락이 풀릴 때 까지 무한 대기한다.
    //      - 특정 시간까지만 대기하는 타임아웃 X, 중간에 인터럽트 X
    // 2. 공정성 : 락이 돌아왔을 때 `BLOCKED` 상태의 여러 스레드 중에 어떤 스레드가 락을 획득할 지 알 수 없다.
    //           최악의 경우 특정 스레드가 너무 오랜기간 락을 획득하지 못할 수 있다.

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        };

        Thread thread1 = new Thread(runnable, "counter1");
        Thread thread2 = new Thread(runnable, "counter1");
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter.getCount());
    }

    static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count += 1;
        }

        public int getCount() {
            return this.count;
        }
    }

}

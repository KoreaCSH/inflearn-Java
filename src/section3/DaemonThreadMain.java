package section3;

public class DaemonThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " : main() start");

        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();

        // 여기에서 바로 main Thread (사용자 스레드) 종료되므로 Daemon Thread 도 바로 종료된다.
        System.out.println(Thread.currentThread().getName() + " : main() end");
    }

    // Thread 에는 non-Daemon Thread (사용자 스레드) 와 Daemon Thread (데몬 스레드) 가 있다.
    // JVM 은 모든 사용자 스레드가 종료되어야 프로세스를 종료한다.
    // 즉, 데몬 스레드는 실행 중이더라도 나머지 사용자 스레드가 모두 종료되면 JVM 은 프로세스를 종료한다.
    static class DaemonThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : run() start");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + " : run() end");
        }
    }

}

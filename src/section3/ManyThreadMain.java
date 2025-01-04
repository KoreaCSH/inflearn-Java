package section3;

import static util.MyLogger.log;

public class ManyThreadMain {

    public static void main(String[] args) {
        log("main() start");

        // 여러 스레드 만들기 예제
        RunnableThread runnableThread = new RunnableThread();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(runnableThread);
            thread.start();
        }

        log("main() end");
    }

}

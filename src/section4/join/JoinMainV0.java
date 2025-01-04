package section4.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV0 {

    public static void main(String[] args) {
        log("start");

        Thread thread = new Thread(new Job());
        thread.start();

        log("end");
    }

    static class Job implements Runnable {

        @Override
        public void run() {
            log("Job start");
            sleep(1000);
            log("Job end");
        }

    }

}

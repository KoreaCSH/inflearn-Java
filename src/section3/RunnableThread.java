package section3;

import util.MyLogger;

import static util.MyLogger.*;

public class RunnableThread implements Runnable {

    @Override
    public void run() {
        log(Thread.currentThread().getName() + " : run()");
    }
}

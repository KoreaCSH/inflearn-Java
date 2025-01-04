package section3;

import util.MyLogger;

import static util.MyLogger.*;

public class MyLoggerMain {

    public static void main(String[] args) {

        log(123);
        log("MyLogger test");

        Thread thread = new Thread(() -> log("Thread Logger Test"));
        thread.start();
    }

}

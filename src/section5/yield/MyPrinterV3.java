package section5.yield;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;

public class MyPrinterV3 {

    public static void main(String[] args) {

        Printer printer = new Printer();
        Thread thread = new Thread(printer, "printer");
        thread.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            log("프린터할 문서를 입력하세요. 종료 (q): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                thread.interrupt();
                break;
            }
            printer.addJob(input);
        }
    }

    static class Printer implements Runnable {

        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            // 해당 코드는 최악의 경우 q 가 입력되었을 때 3초 이후에 Printer thread 가 종료된다.
            try {
                while (!Thread.interrupted()) {
                    if (jobQueue.isEmpty()) {
                        // yield() 를 추가하지 않으면, 해당 스레드는 계속 jobQueue 의 empty 여부를 확인할 것이다.
                        // 굉장히 많은 횟수가 반복될 수 있으므로 yield() 를 추가하여 CPU 를 덜 사용하도록 하자.
                        Thread.yield();
                        continue;
                    }
                    String job = jobQueue.poll();
                    log("출력 시작: " + job + ", 대기 문서: " + jobQueue);
                    Thread.sleep(3000);
                    log("출력 완료");
                }
                log("Printer 정상 종료");
            } catch (InterruptedException e) {
                log("Printer 강제 종료");
            }
        }

        public void addJob(String job) {
            jobQueue.offer(job);
        }

    }

}

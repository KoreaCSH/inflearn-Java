package section5.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.*;
import static util.ThreadUtils.sleep;

public class MyPrinterV1 {

    public static void main(String[] args) {

        Printer printer = new Printer();
        Thread thread = new Thread(printer, "printer");
        thread.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            log("프린터할 문서를 입력하세요. 종료 (q): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                printer.work = false;
                break;
            }
            printer.addJob(input);
        }
    }

    static class Printer implements Runnable {

        volatile boolean work = true;
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            // 해당 코드는 최악의 경우 q 가 입력되었을 때 3초 이후에 Printer thread 가 종료된다.
            while (work) {
                if (jobQueue.isEmpty()) {
                    continue;
                }

                String job = jobQueue.poll();
                log("출력 시작: " + job + ", 대기 문서: " + jobQueue);
                sleep(3000);
                log("출력 완료");
            }
            log("Printer 종료");
        }

        public void addJob(String job) {
            jobQueue.offer(job);
        }

    }

}

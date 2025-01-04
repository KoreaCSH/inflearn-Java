package section4;

import section3.RunnableThread;
import util.MyLogger;

import static util.MyLogger.*;

public class ThreadInfoMain {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        // Thread 의 toString() 은 [ID, name, priority, groupName] 으로 구성된다.
        log("mainThread = " + thread);
        // threadId 는 unique 한 반면 Name 은 중복될 수 있다.
        log("thread.threadId() = " + thread.threadId());
        log("thread.getName() = " + thread.getName());
        // default 우선순위는 5. 우선순위(1 ~ 10) 높을수록 더 자주 실행될 확률 높아짐 (스케줄러, OS 에 따라 차이 O)
        log("thread.getPriority() = " + thread.getPriority());
        log("thread.getThreadGroup() = " + thread.getThreadGroup());
        log("thread.getState() = " + thread.getState());

        System.out.println();

        Thread myThread = new Thread(new RunnableThread(), "myThread");
        log("mainThread = " + myThread);
        log("myThread.threadId() = " + myThread.threadId());
        log("myThread.getName() = " + myThread.getName());
        log("myThread.getPriority() = " + myThread.getPriority());
        log("myThread.getThreadGroup() = " + myThread.getThreadGroup());
        log("myThread.getState() = " + myThread.getState());

        myThread.start();
        log("myThread.getState() = " + myThread.getState());

        // Thread 는 한 번 종료되면 다시 시작할 수 없기에 다시 Thread 를 생성해야 한다.
        // Thread 의 State : NEW -> RUNNABLE -> TERMINATED
    }

}
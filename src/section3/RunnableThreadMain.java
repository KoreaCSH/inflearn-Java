package section3;

public class RunnableThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " : main() start");

        RunnableThread runnableThread = new RunnableThread();
        Thread thread = new Thread(runnableThread);
        System.out.println(Thread.currentThread().getName() + " : start() 호출 전");
        thread.start();
        System.out.println(Thread.currentThread().getName() + " : start() 호출 후");

        System.out.println(Thread.currentThread().getName() + " : main() end");
    }

}

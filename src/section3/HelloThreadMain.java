package section3;

public class HelloThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " : main() start");

        HelloThread helloThread = new HelloThread();

        System.out.println(Thread.currentThread().getName() + " : start() 호출 전");
        // Thread 객체 생성 후 반드시 start() 호출해야 스택 공간 할당받고 스레드가 작동한다.
        // 그 후에 run() 메서드를 실행한다.
        // 만약 start() 를 하지 않고 run() 을 호출하면 현재 실행중인 main Thread 가 HelloThread 의 run() 을 호출한다.
        helloThread.start();
        System.out.println(Thread.currentThread().getName() + " : start() 호출 후");

        System.out.println(Thread.currentThread().getName() + " : main() end");
    }

}

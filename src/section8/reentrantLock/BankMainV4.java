package section8.reentrantLock;

import section7.sync.BankAccount;
import section7.sync.WithdrawTask;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankMainV4 {

    public static void main(String[] args) throws InterruptedException {

        BankAccount account = new BankAccountV4(1000);

        Thread t1 = new Thread(new WithdrawTask(account, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(account, 800), "t2");

        t1.start();
        t2.start();

        sleep(500);
        log("t1 state: " + t1.getState());
        log("t2 state: " + t2.getState());

        t1.join();
        t2.join();

        log("최종 잔액 : " + account.getBalance());
    }

}

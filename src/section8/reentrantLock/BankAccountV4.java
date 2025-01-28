package section8.reentrantLock;

import section7.sync.BankAccount;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV4 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV4(int initialBalance) {
        this.balance = initialBalance;
    }

    // V4 : synchronized 의 무한 대기 문제를 해결하기 위해 Lock 활용

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());

        lock.lock();

        try {
            // [임계 영역 시작]
            log("[검증 시작] 출금액 : " + amount + ", 잔액 : " + balance);

            // Validation 로직
            if (balance < amount) {
                log("[검증 실패] 출금액 : " + amount + ", 잔액 : " + balance);
                return false;
            }

            log("[검증 완료] 출금액 : " + amount + ", 잔액 : " + balance);
            // 출금에 1초 소요
            sleep(1000);
            balance -= amount;
            log("[출금 완료] 출금액 : " + amount + ", 잔액 : " + balance);
            // [임계 영역 종료]
        } finally {
            // Lock 은 반드시 unlock 해야 하기 때문에 finally 에 작성한다.
            lock.unlock();
        }

        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

}

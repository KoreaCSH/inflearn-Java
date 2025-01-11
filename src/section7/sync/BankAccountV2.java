package section7.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV2 implements BankAccount {

    private int balance;

    public BankAccountV2(int initialBalance) {
        this.balance = initialBalance;
    }

    // Java 의 인스턴스는 lock 이라는 값을 갖는다.

    @Override
    public synchronized boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());
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
        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }

}

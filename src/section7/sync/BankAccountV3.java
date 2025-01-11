package section7.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV3 implements BankAccount {

    private int balance;

    public BankAccountV3(int initialBalance) {
        this.balance = initialBalance;
    }

    // V3 to-do : 임계 영역 축소하기 위해 Java 는 synchronized 코드 블럭 기능을 제공한다.

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());

        // () 괄호 안에 들어가는 값은 lock 을 획득할 인스턴스의 참조이다.
        synchronized(this) {
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
        }

        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }

}

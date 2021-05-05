package ru.luxoft.courses.lab11;

import ru.luxoft.courses.lab11.annotation.OperationLimitATM;

@OperationLimitATM(limit = 3)
public class Atm {
    private static final String OPERATION_LIMIT_ENDS_MESSAGE = "Oper limit ends!";
    private final Account account = new Account(new Principal("Igor", "Ershov", "Vladimirovich", (short) 35), "login", "pass");
    private final Money rur = newMoney("RUR", 1000);
    private final Money usd = newMoney("USD", 1000);
    private final CreditScore creditScore = new CreditScore(account, 222, rur);
    private final DebitScore debitScore = new DebitScore(account, 223, usd);
    private final int operLimit;
    private int currentOpers = 0;

    public Atm() {
        account.addScore(creditScore);
        account.addScore(debitScore);
        OperationLimitATM annotation = this.getClass().getAnnotation(OperationLimitATM.class);
        this.operLimit = annotation == null ? -1 : annotation.limit();
    }

    public static void main(String[] args) {
        new Atm().run();
    }

    private void withdrawMoneyFromDebit(Money money) {
        if (notInLimit()) return;
        currentOpers++;
        debitScore.withdrawMoney(money);
    }

    private void withdrawMoneyFromCredit(Money money) {
        if (notInLimit()) return;
        currentOpers++;
        creditScore.withdrawMoney(money);
    }

    private void addMoneyToDebit(Money money) {
        if (notInLimit()) return;
        currentOpers++;
        debitScore.addMoney(money);
    }

    private void addMoneyToCredit(Money money) {
        if (notInLimit()) return;
        currentOpers++;
        creditScore.addMoney(money);
    }

    private boolean notInLimit() {
        if (operLimit != -1 && currentOpers >= operLimit) {
            System.out.println(OPERATION_LIMIT_ENDS_MESSAGE);
            return true;
        }
        return false;
    }

    public void run() {

        System.out.println("Начальный акк 1000USD + 1000RUR \n" + account);
        withdrawMoneyFromDebit(newMoney("USD", 500));

        System.out.println("Сняли с дебета 500 USD \n" + account);
        withdrawMoneyFromCredit(newMoney("RUR", 5000));

        System.out.println("Сняли с кредита 5000RUR \n " + account);
        addMoneyToDebit(newMoney("RUR", 100000));

        System.out.println("Положили 100000 RUR на дебет \n" + account);
        addMoneyToCredit(newMoney("RUR", 1000));

        System.out.println("Положили 1000 RUR на кредит \n" + account);
        addMoneyToDebit(newMoney("RUR", 1000001));
        addMoneyToDebit(newMoney("RUR", 1000001));

        System.out.println(account);
    }

    private Money newMoney(String currency, int value) {
        return new Money(CurrencyHolder.getCurrencyByName(currency), value);
    }

}
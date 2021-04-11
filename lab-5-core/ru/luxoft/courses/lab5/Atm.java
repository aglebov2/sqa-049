package ru.luxoft.courses.lab5;

import ru.luxoft.courses.lab5.annotation.MethodLimit;
import ru.luxoft.courses.lab5.annotation.OperationLimitATM;

import java.lang.annotation.Annotation;

@OperationLimitATM(limit = 3)
public class Atm {
    private int operLimit;
    private int currentOpers;
    private boolean operLimitToggl = true;

    private Account  account = new Account(new Principal("Igor", "Ershov", "Vladimirovich", (short) 35), "login", "pass");

    private Money rur = new Money(CurrencyHolder.getCurrencyByName("RUR"), 1000);
    private Money usd = new Money(CurrencyHolder.getCurrencyByName("USD"), 1000);

    private CreditScore creditScore = new CreditScore(account, 222, rur);
    private DebitScore debitScore = new DebitScore(account, 223, usd);
    {
        account.addScore(creditScore);
        account.addScore(debitScore);
    }

    public Atm(int operLimit, int currentOpers) {
        this.operLimit = operLimit;
        this.currentOpers = currentOpers;
        Class thisClass = this.getClass();
        for (Annotation annotation:
                thisClass.getAnnotations()) {
            if(annotation instanceof OperationLimitATM){
                this.operLimit = ((OperationLimitATM)annotation).limit();
                this.operLimitToggl = true;
            }
        }

    }


    private void withdrawMoneyFromDebet(Money money) {
        if(operLimitToggl && currentOpers >= operLimit){
            System.out.println("Oper limit ends!");
            return;
        }
        currentOpers++;
        debitScore.withdrawMoney(money);
    }

    private void withdrawMoneyFromCredet(Money money) {
        if(operLimitToggl && currentOpers >= operLimit){
            System.out.println("Oper limit ends!");
            return;
        }
        currentOpers++;
        creditScore.withdrawMoney(money);
    }

    private void addMoneyToDebet(Money money) {
        if(operLimitToggl && currentOpers >= operLimit){
            System.out.println("Oper limit ends!");
            return;
        }
        currentOpers++;
        debitScore.addMoney(money);
    }

    private void addMoneyToCredit(Money money) {
        if(operLimitToggl && currentOpers >= operLimit){
            System.out.println("Oper limit ends!");
            return;
        }
        currentOpers++;
        creditScore.addMoney(money);
    }

    public static void main(String[] args) {
      new Atm(2, 0).run();

    }

    public void run() {

        System.out.println("Начальный акк 1000USD + 1000RUR \n" + account);
        withdrawMoneyFromDebet(new Money(CurrencyHolder.getCurrencyByName("USD"), 500));
        System.out.println("Сняли с дебета 500 USD \n" + account);
        withdrawMoneyFromCredet(new Money(CurrencyHolder.getCurrencyByName("RUR"), 5000));
        System.out.println("Сняли с кредита 5000RUR \n " + account);
        addMoneyToDebet(new Money(CurrencyHolder.getCurrencyByName("RUR"), 100000));
        System.out.println("Положили 100000 RUR на дебет \n" + account);
        addMoneyToCredit(new Money(CurrencyHolder.getCurrencyByName("RUR"), 1000));
        System.out.println("Положили 1000 RUR на кредит \n" + account);
        addMoneyToDebet(new Money(CurrencyHolder.getCurrencyByName("RUR"), 1000001));
        addMoneyToDebet(new Money(CurrencyHolder.getCurrencyByName("RUR"), 1000001));
        System.out.println(account);


    }
}

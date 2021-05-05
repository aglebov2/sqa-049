package ru.luxoft.courses.lab11;

import ru.luxoft.courses.lab11.annotation.Loggable;

@Loggable
public class CreditScore extends Score {

    public CreditScore(Account owner, Integer number, Money balance) {
        super(owner, number, balance);

    }

    @Override
    protected boolean operationBlocked(double operationUsdAmount) {
        return false;
    }

    @Override
    protected boolean checkBalance(double operationUsdAmount) {
        return true;
    }

    @Override
    protected double getBonus(double operationUsdAmount) {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("CreditScore{%.2f%s}", this.balance().getValue(), this.balance().getCurrency().getName());

    }
}


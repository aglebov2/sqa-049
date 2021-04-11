package ru.luxoft.courses.lab5;


import ru.luxoft.courses.lab5.annotation.Loggable;



@Loggable
public class DebitScore extends Score {

    public DebitScore(Account owner, Integer number, Money balance) {
        super(owner, number, balance);
    }

    @Override
    protected boolean operationBlocked(double operationUsdAmount) {
        return getOwner().getScoreMap().values().stream()
                .filter(score -> score instanceof CreditScore)
                .anyMatch(score -> score.usdBalance() < -20000);
    }

    @Override
    protected boolean checkBalance(double operationUsdAmount) {
        return usdBalance() >= operationUsdAmount;
    }

    @Override
    protected double getBonus(double operationUsdAmount) {
        return operationUsdAmount > 1000000 ? 2000 : 0;
    }

    @Override
    public String toString() {
        return String.format("DebitScore{%.2f%s}", this.balance().getValue(), this.balance().getCurrency().getName());
    }

}

package ru.luxoft.courses.lab11;

import ru.luxoft.courses.lab11.annotation.Loggable;
import ru.luxoft.courses.lab11.annotation.MethodLimit;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class Score implements MoneyInterface {

    private static final String METHOD_LIMIT_IS_OVER_MESSAGE = "Method limit is over!";
    private static final String WITHDRAW_MONEY_METHOD_NAME = "withdrawMoney";
    private final Account owner;
    private final Integer number;
    private final Money balance;
    private final Map<String, Integer> methodConstraintMap = new HashMap<>();
    private final Map<String, Integer> methodCallMap = new HashMap<>();

    protected Score(Account owner, Integer number, Money balance) {
        this.owner = owner;
        this.number = number;
        this.balance = balance;
        for (Method method :
                this.getClass().getSuperclass().getDeclaredMethods()) {
            MethodLimit annotation = method.getDeclaredAnnotation(MethodLimit.class);
            if (annotation != null) {
                int limit = annotation.limit();
                methodConstraintMap.put(method.getName(), limit);
                methodCallMap.put(method.getName(), 0);
            }
        }

    }

    protected abstract boolean operationBlocked(double operationUsdAmount);

    protected abstract boolean checkBalance(double operationUsdAmount);

    protected abstract double getBonus(double operationUsdAmount);

    //________________
// Annotation method
    protected void logIfneeded(String methodName) {
        Loggable annotation = this.getClass().getAnnotation(Loggable.class);
        if (annotation != null) {
            System.out.println("We call method " + methodName);
        }
    }

    protected boolean isNotMethodAvailableByOperLimit(String methodName) {
        if (methodConstraintMap.containsKey(methodName)) {
            int currentCalls = methodCallMap.get(methodName);
            int limitCalls = methodConstraintMap.get(methodName);

            if (currentCalls >= limitCalls) {
                return true;
            }

            currentCalls++;
            methodCallMap.put(methodName, currentCalls);
        }
        return false;
    }

    //end Annotation method
    @Override
    public void addMoney(Money money) {
        logIfneeded("addMoney");
        //Annotation check
        if (isNotMethodAvailableByOperLimit("addMoney")) {
            System.out.println(METHOD_LIMIT_IS_OVER_MESSAGE);
            return;
        }

        double usdValueIn = money.getValue() * money.getCurrency().getUsdRate();

        if (operationBlocked(usdValueIn)) {
            System.out.println("Operation blocked!");
            return;
        }

        addBalance(usdValueIn + getBonus(usdValueIn));
    }

    @Override
    @MethodLimit(limit = 1)
    public void withdrawMoney(Money money) {
        logIfneeded(WITHDRAW_MONEY_METHOD_NAME);
        //Annotation check
        if (isNotMethodAvailableByOperLimit(WITHDRAW_MONEY_METHOD_NAME)) {
            System.out.println(METHOD_LIMIT_IS_OVER_MESSAGE);
            return;
        }
        double usdValueIn = money.getValue() * money.getCurrency().getUsdRate();

        if (operationBlocked(usdValueIn)) {
            System.out.println("Operation blocked!");
            return;
        }

        if (usdValueIn > 30000) {
            throw new IllegalArgumentException
                    (String.format("Maximum withdrawal amount is %.2f%s!",
                            30000 * money.getCurrency().getUsdRate(),
                            money.getCurrency().getName()));
        }

        if (!checkBalance(usdValueIn)) {
            System.out.println("You have no so much!");
            return;
        }
        withdrawBalance(usdValueIn);
    }

    @Override
    public Money balance() {
        logIfneeded("balance");
        //Annotation check
        if (isNotMethodAvailableByOperLimit("balance")) {
            System.out.println(METHOD_LIMIT_IS_OVER_MESSAGE);
            return null;
        }
        return this.balance;
    }

    @MethodLimit(limit = 2)
    private void withdrawBalance(double usdAmount) {
        logIfneeded("withdrawBalance");
        //Annotation check
        if (isNotMethodAvailableByOperLimit(WITHDRAW_MONEY_METHOD_NAME)) {
            System.out.println(METHOD_LIMIT_IS_OVER_MESSAGE);
            return;
        }
        addBalance(-usdAmount);
    }

    @MethodLimit(limit = 2)
    private void addBalance(double usdAmount) {
        logIfneeded("addBalance");
        //Annotation check
        if (isNotMethodAvailableByOperLimit("addBalance")) {
            System.out.println(METHOD_LIMIT_IS_OVER_MESSAGE);
            return;
        }
        this.balance.setValue(this.balance.getValue() + usdAmount / getUsdRate());
    }

    protected Double usdBalance() {
        logIfneeded("usdBalance");
        //Annotation check
        if (isNotMethodAvailableByOperLimit("usdBalance")) {
            System.out.println(METHOD_LIMIT_IS_OVER_MESSAGE);

            return null;
        }
        return this.balance.getValue() * getUsdRate();
    }

    private Float getUsdRate() {
        logIfneeded("getUsdRate");
        //Annotation check
        if (isNotMethodAvailableByOperLimit("getUsdRate")) {
            System.out.println(METHOD_LIMIT_IS_OVER_MESSAGE);
            //to prevent NullPointerException, Better to throw own xxException
            return 1f;
        }
        return this.balance.getCurrency().getUsdRate();
    }

    public Account getOwner() {
        logIfneeded("getOwner");
        //Annotation check
        if (isNotMethodAvailableByOperLimit("getOwner")) {
            System.out.println(METHOD_LIMIT_IS_OVER_MESSAGE);
            return null;
        }
        return owner;
    }

    @MethodLimit(limit = 2)
    public Integer getNumber() {
        logIfneeded("getNumber");
        //Annotation check
        if (isNotMethodAvailableByOperLimit("getNumber")) {
            System.out.println(METHOD_LIMIT_IS_OVER_MESSAGE);
            return null;
        }
        return number;
    }
}
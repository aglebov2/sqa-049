package ru.luxoft.courses.lab5;

import ru.luxoft.courses.lab5.annotation.Loggable;
import ru.luxoft.courses.lab5.annotation.MethodLimit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class Score implements MoneyInterface {

    private Account owner;
    private Integer number;
    private Money balance;
    private Map<String, Integer> methodConstraintMap = new HashMap<>();
    private Map<String, Integer> methodCallMap = new HashMap<>();


    public Score(Account owner, Integer number, Money balance) {
        this.owner = owner;
        this.number = number;
        this.balance = balance;
        Class thisClass = this.getClass().getSuperclass();
        for (Method method:
                thisClass.getDeclaredMethods()) {
            for (Annotation annotation:
                    method.getDeclaredAnnotations()) {
                if(annotation instanceof MethodLimit){
                    int limit = ((MethodLimit)annotation).limit();
                    methodConstraintMap.put(method.getName(), limit);
                    methodCallMap.put(method.getName(), 0);
                    boolean methodConstraintToggl = true;
                }
            }
        }

    }


    protected abstract boolean operationBlocked(double operationUsdAmount);
    protected abstract boolean checkBalance(double operationUsdAmount);
    protected abstract double getBonus(double operationUsdAmount);
//________________
// Annotation method
    protected void logIfneeded(String methodName){
        Class thisClass = this.getClass();
        for (Annotation annotation:
                thisClass.getAnnotations()) {
            if(annotation instanceof Loggable){
                System.out.println("We call method " + methodName);
            }
        }
    }

    protected boolean isMethodAvailableByOperLimit(String methodName){
        if(methodConstraintMap.containsKey(methodName)){
            int currentCalls = methodCallMap.get(methodName);
            int limitCalls = methodConstraintMap.get(methodName);

            if(currentCalls >= limitCalls){
                return false;
            }

            currentCalls++;
            methodCallMap.put(methodName, currentCalls);
        }
        return true;
    }


//end Annotation method
    @Override
    public void addMoney(Money money) {
        logIfneeded("addMoney");
        //Annotation check
        if(!isMethodAvailableByOperLimit("addMoney")){
            System.out.println("Method limit is over!");
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
        logIfneeded("withdrawMoney");
        //Annotation check
        if(!isMethodAvailableByOperLimit("withdrawMoney")){
            System.out.println("Method limit is over!");
            return;
        }
        double usdValueIn = money.getValue() * money.getCurrency().getUsdRate();

        if (operationBlocked(usdValueIn)) {
            System.out.println("Operation blocked!");
            return;
        }

        if(usdValueIn > 30000) {
            throw new IllegalArgumentException
                    (String.format("Maximum withdrawal amount is %.2f%s!",
                            30000 * money.getCurrency().getUsdRate(),
                            money.getCurrency().getName()));
        }

        if(!checkBalance(usdValueIn)) {
            System.out.println("You have no so much!");
            return;
        }

        withdrawBalance(usdValueIn);
    }

    @Override
    public Money balance() {
        logIfneeded("balance");
        //Annotation check
        if(!isMethodAvailableByOperLimit("balance")){
            System.out.println("Method limit is over!");
            return null;
        }
        return this.balance;
    }
    @MethodLimit(limit = 2)
    private void withdrawBalance(double usdAmount) {
        logIfneeded("withdrawBalance");
        //Annotation check
        if(!isMethodAvailableByOperLimit("withdrawMoney")){
            System.out.println("Method limit is over!");
            return;
        }
        addBalance(-usdAmount);
    }
    @MethodLimit(limit = 2)
    private void addBalance(double usdAmount) {
        logIfneeded("addBalance");
        //Annotation check
        if(!isMethodAvailableByOperLimit("addBalance")){
            System.out.println("Method limit is over!");
            return;
        }
        this.balance.setValue(this.balance.getValue() + usdAmount/getUsdRate());
    }

    protected Double usdBalance() {
        logIfneeded("usdBalance");
        //Annotation check
        if(!isMethodAvailableByOperLimit("usdBalance")){
            System.out.println("Method limit is over!");

            return null;
        }
        return this.balance.getValue() * getUsdRate();
    }

    private Float getUsdRate() {
        logIfneeded("getUsdRate");
        //Annotation check
        if(!isMethodAvailableByOperLimit("getUsdRate")){
            System.out.println("Method limit is over!");
            return null;
        }
        return this.balance.getCurrency().getUsdRate();
    }


    public Account getOwner() {
        logIfneeded("getOwner");
        //Annotation check
        if(!isMethodAvailableByOperLimit("getOwner")){
            System.out.println("Method limit is over!");
            return null;
        }
        return owner;
    }
    @MethodLimit(limit = 2)
    public Integer getNumber() {
        logIfneeded("getNumber");
        //Annotation check
        if(!isMethodAvailableByOperLimit("getNumber")){
            System.out.println("Method limit is over!");
            return null;
        }
        return number;
    }

}

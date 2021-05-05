package ru.luxoft.courses.lab11;

public class Currency {
    private final String name;
    private float usdRate;

    public Currency(String name, float usdRate) {
        this.name = name;
        this.usdRate = usdRate;
    }

    public String getName() {
        return name;
    }

    public float getUsdRate() {
        return usdRate;
    }

    public void setUsdRate(float usdRate) {
        this.usdRate = usdRate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", usdRate=" + usdRate +
                '}';
    }
}

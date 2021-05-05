package ru.luxoft.courses.lab11;

import java.util.HashMap;
import java.util.Map;

public class CurrencyHolder {

    private static final Map<String, Currency> currencies = new HashMap<>();

    static {
        currencies.put("USD", new Currency("USD", 1));
        currencies.put("RUR", new Currency("RUR", 1 / 65.5f));
    }

    private CurrencyHolder() {
    }

    public static Currency getCurrencyByName(String name) {
        return currencies.get(name);
    }
}



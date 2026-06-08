package com.example.konwerterwartociijednostek;

import java.util.HashMap;

public class CurrencyConverter {

    private static final HashMap<String, Double> rates = new HashMap<>();

    static {

        rates.put("PLN", 1.0);
        rates.put("USD", 3.95);
        rates.put("EUR", 4.30);
        rates.put("GBP", 5.05);
        rates.put("CHF", 4.55);
    }

    public static double convert(double amount,
                                 String from,
                                 String to) {

        double pln = amount * rates.get(from);

        return pln / rates.get(to);
    }
}
package com.example.konwerterwartociijednostek;

import java.util.HashMap;

public class AreaConverter {

    private static final HashMap<String, Double> units =
            new HashMap<>();

    static {

        units.put("mm²",0.000001);
        units.put("cm²",0.0001);
        units.put("m²",1.0);
        units.put("km²",1000000.0);
        units.put("ar",100.0);
        units.put("ha",10000.0);
    }

    public static double convert(double value,
                                 String from,
                                 String to) {

        double m2 =
                value * units.get(from);

        return m2 / units.get(to);
    }
}
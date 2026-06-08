package com.example.konwerterwartociijednostek;

import java.util.HashMap;

public class LengthConverter {

    private static final HashMap<String, Double> units =
            new HashMap<>();

    static {

        units.put("mm",0.001);
        units.put("cm",0.01);
        units.put("in",0.0254);
        units.put("ft",0.3048);
        units.put("yd",0.9144);
        units.put("m",1.0);
        units.put("km",1000.0);
    }

    public static double convert(double value,
                                 String from,
                                 String to) {

        double meters =
                value * units.get(from);

        return meters / units.get(to);
    }
}
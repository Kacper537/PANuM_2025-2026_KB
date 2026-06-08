package com.example.konwerterwartociijednostek;

public class BaseConverter {

    public static String convert(String value, int fromBase, int toBase) {

        int decimal = Integer.parseInt(value, fromBase);

        return Integer.toString(decimal, toBase).toUpperCase();
    }

    public static boolean isValid(String value, int base) {

        String pattern = "";

        switch (base) {

            case 2:
                pattern = "[01]+";
                break;

            case 4:
                pattern = "[0-3]+";
                break;

            case 8:
                pattern = "[0-7]+";
                break;

            case 10:
                pattern = "[0-9]+";
                break;

            case 16:
                pattern = "[0-9A-Fa-f]+";
                break;
        }

        return value.matches(pattern);
    }
}
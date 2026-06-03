package com.example.kafeteria;

public class Cafeteria {

    private String name;
    private String description;

    public static final Cafeteria[] cafeterias = {

            new Cafeteria(
                    "Kafeteria Centrum",
                    "ul. Główna 1"),

            new Cafeteria(
                    "Kafeteria Południe",
                    "ul. Kwiatowa 12")
    };

    private Cafeteria(String name,
                      String description) {

        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
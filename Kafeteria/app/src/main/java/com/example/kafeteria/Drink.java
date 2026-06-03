package com.example.kafeteria;

public class Drink {

    private String name;
    private String description;
    private String price;
    private int imageResourceId;

    public static final Drink[] drinks = {
            new Drink(
                    "Latte",
                    "Czarne espresso z gorącym mlekiem i mleczną pianką.",
                    "12,00 zł",
                    R.drawable.latte),

            new Drink(
                    "Cappuccino",
                    "Czarne espresso z dużą ilością spienionego mleka.",
                    "11,00 zł",
                    R.drawable.cappuccino),

            new Drink(
                    "Espresso",
                    "Czarna kawa ze świeżo mielonych ziaren najwyższej jakości.",
                    "9,00 zł",
                    R.drawable.espresso)
    };

    private Drink(String name,
                  String description,
                  String price,
                  int imageResourceId) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    @Override
    public String toString() {
        return name;
    }
}
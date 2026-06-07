package com.example.kafeteria;

public class Snack {

    private String name;
    private String description;
    private String price;
    private int imageResourceId;

    public Snack(String name,
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

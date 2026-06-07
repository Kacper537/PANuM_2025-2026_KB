package com.example.kafeteria;

public class Cafeteria {

    private String name;
    private String address;
    private String openingHours;
    private int imageResourceId;


    private Cafeteria(String name,
                      String address,
                      String openingHours,
                      int imageResourceId) {

        this.name = name;
        this.address = address;
        this.openingHours = openingHours;
        this.imageResourceId = imageResourceId;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}

package com.example.demo;

import java.io.Serializable;

public class Food implements Serializable {
    private int restaurantId;
    private String category;
    private String name;
    private double price;

    public Food(int restaurantId, String category, String name, double price) {
        this.restaurantId = restaurantId;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // You can add other methods here as needed
}

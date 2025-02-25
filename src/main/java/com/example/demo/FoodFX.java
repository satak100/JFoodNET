package com.example.demo;

import javafx.beans.property.*;

import java.io.Serializable;

public class FoodFX implements Serializable {
    private transient IntegerProperty restaurantId;
    private transient StringProperty category;
    private transient StringProperty name;
    private transient DoubleProperty price;

    public FoodFX(int restaurantId, String category, String name, double price) {
        this.restaurantId = new SimpleIntegerProperty(restaurantId);
        this.category = new SimpleStringProperty(category);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public IntegerProperty idProperty() {
        return restaurantId;
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    // Getter methods to access the values
    public int getRestaurantId() {
        return restaurantId.get();
    }

    public String getCategory() {
        return category.get();
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }

    // Setter methods if needed
    public void setRestaurantId(int id) {
        restaurantId.set(id);
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}

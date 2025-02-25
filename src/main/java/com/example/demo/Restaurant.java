package com.example.demo;

import java.io.Serializable;
import java.util.List;

public class Restaurant implements Serializable {
    private int id;
    private String name;
    private double score;
    private String price;
    private String zipCode;
    private List<String> categories;

    public Restaurant(int id, String name, double score, String price, String zipCode, List<String> categories) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.price = price;
        this.zipCode = zipCode;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getPrice() {
        return price;
    }

    public String getZipCode() {
        return zipCode;
    }

    public List<String> getCategories() {
        return categories;
    }

}

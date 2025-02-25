package com.example.demo;

import javafx.fxml.FXML;

import java.io.IOException;

public class OrderConfirmController {
    public Food food;
    public FoodCustomerClient client;

    public void setClient(FoodCustomerClient client,Food f){
        this.client = client;
        food = f;
        System.out.println("Exiting setclient");
    }
    @FXML
    public void yesClick() throws IOException {
        System.out.println("Entering into yesClick");
        NetworkUtil networkUtil = new NetworkUtil("127.0.0.1",33333);
        System.out.println("1");
        networkUtil.write(food.getRestaurantId());
        System.out.println("1");
        networkUtil.write(food);
        System.out.println("1");
        client.showFoodDetail();
        System.out.println("1");
    }

    @FXML
    public void noClick() throws IOException {
        client.showFoodDetail();
    }
}

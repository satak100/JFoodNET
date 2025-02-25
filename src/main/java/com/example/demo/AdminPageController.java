package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminPageController {
    @FXML
    private Button addRestaurantButton;
    @FXML
    private Button addFoodButton;
    public Server server;

    public void setServer(Server s){
        server = s;
    }
    @FXML
    public void addRestaurant() throws IOException {
        server.showAddRestaurantPage();
    }

    @FXML
    public void addFood() throws IOException {
        server.showAddFoodPage();
    }

}

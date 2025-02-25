package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddFoodFormController {

    @FXML
    private TextField FoodCategory;

    @FXML
    private Button Button;

    @FXML
    private TextField FoodPrice;

    @FXML
    private TextField Id;

    @FXML
    private TextField FoodName;

    public Server server;
    public RestaurantDatabase restaurants;

    public void setServer(Server server,RestaurantDatabase r){
        this.server = server;
        this.restaurants = r;
    }

    @FXML
    void AddFood() throws IOException {
        Food food = new Food(Integer.parseInt(Id.getText()),FoodCategory.getText(),FoodName.getText(),Double.parseDouble(FoodPrice.getText()));
        restaurants.addFoodItem(food);
        restaurants.saveFoodItemsToFile("D:\\demo\\src\\main\\java\\com\\example\\demo\\menu.txt");
        server.showAdminPage();
    }


}

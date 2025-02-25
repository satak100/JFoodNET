package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddRestaurantFormController {

    @FXML
    private TextField Score;

    @FXML
    private TextField Category;

    @FXML
    private TextField Price;

    @FXML
    private TextField ZipCode;

    @FXML
    private TextField RestaurantID;

    @FXML
    private Button Submit;

    @FXML
    private TextField Name;
    public Server server;
    public RestaurantDatabase restaurants;

    public void setServer(Server server,RestaurantDatabase r){
        this.server = server;
        this.restaurants = r;
    }

    @FXML
    void saveRestaurant() throws IOException {
        String[] strings = Category.getText().split(",",-1);
        List<String> categories = new ArrayList<>();
        for(String s : strings){
            categories.add(s);
        }
        Restaurant restaurant = new Restaurant(Integer.parseInt(RestaurantID.getText()),Name.getText(),Double.parseDouble(Score.getText()),Price.getText(),ZipCode.getText(),categories);
        restaurants.addRestaurant(restaurant);
        restaurants.saveRestaurantsToFile("D:\\demo\\src\\main\\java\\com\\example\\demo\\restaurant.txt");

        server.showAdminPage();
    }

}

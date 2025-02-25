package com.example.demo;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FoodCustomerAfterLoginController {

    @FXML
    private ListView<String> RestaurantList;

    @FXML
    private ListView<String> FoodList;
    @FXML
    private TextField search;

    @FXML
    private Button searchButton;

    @FXML
    private ChoiceBox <String> RestaurantChoiceBox;

    @FXML
    private Button reloadButton;
    @FXML
    private Button searchButton1;
    @FXML
    private ChoiceBox <String> FoodChoiceBox;
    private String[] choiceBox_menu={"Search by Name","Search by Category","Search by Price",
            "Search by Zip Code","All"};
    private String[] choiceBox_menu1={"Search by Name", "Search by Category" ,"Search by Price_Range",
            "Get Cosliest Items" };
    private FoodCustomerClient client;
    private RestaurantDatabase r;
    public void setClient(FoodCustomerClient client,RestaurantDatabase r){
        this.client=client;
        this.r=r;

        /*for(Restaurant res : r.restaurants){
            RestaurantList.getItems().addAll(res.getName());
        }*/

    }

    public void initialize() throws IOException{
        // Add an event listener to the RestaurantList
        RestaurantChoiceBox.getItems().addAll(choiceBox_menu);

        RestaurantList.setOnMouseClicked(this::handleRestaurantListClick);
        FoodList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @java.lang.Override
            public void handle(MouseEvent mouseEvent) {
                String selectedFood = FoodList.getSelectionModel().getSelectedItem();
                String[] strings = selectedFood.split(",",-1);
                Food food = new Food(Integer.parseInt(strings[2]),strings[1],strings[0],Double.parseDouble(strings[3]));
                try {
                    client.showOrderConfirm(food);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }



    private void handleRestaurantListClick(MouseEvent event) {
        // Get the selected restaurant
        String selectedRestaurant = RestaurantList.getSelectionModel().getSelectedItem();

        // Clear the current items in FoodList
        FoodList.getItems().clear();
        // Add the food items of the selected restaurant to FoodList
        for (Food f : r.foodItems) {
            if (r.getRestaurantNameWithId(f.getRestaurantId()).equals(selectedRestaurant)) {
                FoodList.getItems().add(f.getName() + "," + f.getCategory() + "," + f.getRestaurantId() + "," + f.getPrice());
            }
        }
    }

    @FXML
    public void searchRestaurantFunc(){
        /*String name = searchRestaurant.getText();
        List<Restaurant> rList = r.searchRestaurantsByName(name);
        RestaurantList.getItems().clear();
        FoodList.getItems().clear();
        for(Restaurant r : rList){
            RestaurantList.getItems().add(r.getName());
        }*/
        String selectedOption = RestaurantChoiceBox.getValue();
        String searchInput = search.getText();

        if (selectedOption != null && !searchInput.isEmpty()) {
            if(selectedOption.equals(choiceBox_menu[4])){
                List<Restaurant> matchingRestaurants=r.restaurants;
                updateRestaurantList(matchingRestaurants);
            }
            else if (selectedOption.equals(choiceBox_menu[0])) {
                List<Restaurant> matchingRestaurants = r.searchRestaurantsByName(searchInput);
                updateRestaurantList(matchingRestaurants);
            }
            else if (selectedOption.equals(choiceBox_menu[1])) {
                List<Restaurant> matchingRestaurants = r.searchRestaurantsByCategory(searchInput);
                updateRestaurantList(matchingRestaurants);
            }
            else if (selectedOption.equals(choiceBox_menu[2])) {
                List<Restaurant> matchingRestaurants = r.searchRestaurantsByPriceRange(searchInput);
                updateRestaurantList(matchingRestaurants);
            }
            else if (selectedOption.equals(choiceBox_menu[3])) {
                List<Restaurant> matchingRestaurants = r.searchRestaurantsByZipCode(searchInput);
                updateRestaurantList(matchingRestaurants);
            }
        } else {
            // Handle the case where no option is selected or the search input is empty
        }
    }
    private void updateRestaurantList(List<Restaurant> matchingRestaurants) {
        RestaurantList.getItems().clear();
        for (Restaurant res : matchingRestaurants) {
            RestaurantList.getItems().addAll(res.getName());
        }
    }

    private void updateFoodList(List<Restaurant> matchingFoods){

    }

    @FXML
    public void reload(){
        RestaurantList.getItems().clear();
        FoodList.getItems().clear();
        for(Restaurant res : r.restaurants){
            RestaurantList.getItems().addAll(res.getName());
        }
    }

}



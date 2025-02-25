package com.example.demo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FoodCustomerClient extends Application {
    public Stage foodCustomerStage;
    public RestaurantDatabase restaurantDatabase;
    public void start(Stage stage) throws IOException {
        foodCustomerStage = stage;
        restaurantDatabase = new RestaurantDatabase();
        showLoginPage();
    }
    public void showLoginPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FoodCustomerLogin.fxml"));
        Parent root = loader.load();

        // Loading the controller
        FoodCustomerLoginController controller = loader.getController();
        controller.setClient(this);

        // Set the primary stage
        foodCustomerStage.setTitle("Restaurant Login");
        foodCustomerStage.setScene(new Scene(root));
        foodCustomerStage.show();
    }

    public void showFoodDetail() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FoodCustomerAfterLogin.fxml"));
        Parent root = loader.load();

        // Loading the controller
        FoodCustomerAfterLoginController controller = loader.getController();
        controller.setClient(this,restaurantDatabase);

        // Set the primary stage
        foodCustomerStage.setTitle("Food List");
        foodCustomerStage.setScene(new Scene(root));
        foodCustomerStage.show();
    }

    public void showOrderConfirm(Food f) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OrderConfirm.fxml"));
        Parent root = loader.load();

        // Loading the controller
        OrderConfirmController controller = loader.getController();
        controller.setClient(this,f);

        // Set the primary stage
        foodCustomerStage.setTitle("Order");
        foodCustomerStage.setScene(new Scene(root));
        System.out.println("exiting show order");
        foodCustomerStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

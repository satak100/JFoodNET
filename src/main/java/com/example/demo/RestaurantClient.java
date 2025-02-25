package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class RestaurantClient extends Application {
    public Stage restaurantStage;
    public Restaurant restaurant;
    public Socket socket;
    public RestaurantDatabase restaurantDatabase;

    public void start(Stage stage) throws IOException {
        restaurantStage = stage;
        restaurantDatabase = new RestaurantDatabase();
        socket = new Socket("127.0.0.1",33333);
        ////Loading the files & creating restaurant objects & food objects

        //restaurantDatabase.DISPLAY();
        //restaurantDatabase.DISPLAY_1();
        showLoginPage(socket);
    }

    public void showLoginPage(Socket s) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setClient(this,s,restaurantDatabase);

        // Set the primary stage
        restaurantStage.setTitle("Restaurant Login");
        restaurantStage.setScene(new Scene(root));
        restaurantStage.show();

    }

    public void showRestaurantDetail(Restaurant r) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RestaurantDetail.fxml"));
        Parent root = loader.load();

        // Loading the controller
        RestaurantDetailController controller = loader.getController();
        controller.setClient(this,r,restaurantDatabase);

        // Set the primary stage
        restaurantStage.setTitle("Details");
        restaurantStage.setScene(new Scene(root));
        restaurantStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}

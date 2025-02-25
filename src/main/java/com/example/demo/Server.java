package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Server extends Application {
    public Stage mainStage;
    public RestaurantDatabase restaurantDatabase;

    @Override
    public void start(Stage stage) throws Exception {
        //stage is kept here in mainStage for future use
        mainStage = stage;
        //Loading the files & creating restaurant objects & food objects
        restaurantDatabase = new RestaurantDatabase();

        new ServerAcceptThread(restaurantDatabase);

        //this function will show the fxml AdminPage
        showAdminPage();
    }

    public void showAdminPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminPage.fxml"));

        //root node that will store everything like Anchor plane
        Parent root = loader.load();

        // Loading the controller
        // With this reference to the controller, you can now access its methods and properties to control and
        // manage the behavior of the GUI elements defined in the FXML file.
        AdminPageController controller = loader.getController();

        controller.setServer(this);
        //showAddRestaurantPage() && showAddFoodPage() is a function of this server method
        //but this two function will be called from AdminPageController.
        // So I need to send current server as a reference so that I can call this.

        // Set the primary stage
        mainStage.setTitle("Admin Page");
        mainStage.setScene(new Scene(root));
        mainStage.show();

    }

    public void showAddRestaurantPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddRestaurantForm.fxml"));
        Parent root = loader.load();

        // Loading the controller
        // I want to access the loader class's method setServer
        AddRestaurantFormController controller = loader.getController();

        controller.setServer(this,restaurantDatabase);
        //here server is sent to controller so that it can pop back to the previous window(ShowAdminPage)
        //ShowAdminPage is a function of Server class

        // Set the primary stage
        mainStage.setTitle("Add Restaurant Form");
        mainStage.setScene(new Scene(root));
        mainStage.show();

    }

    public void showAddFoodPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddFoodForm.fxml"));
        Parent root = loader.load();

        // Loading the controller
        AddFoodFormController controller = loader.getController();
        controller.setServer(this,restaurantDatabase);

        // Set the primary stage
        mainStage.setTitle("Add Restaurant Form");
        mainStage.setScene(new Scene(root));
        mainStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

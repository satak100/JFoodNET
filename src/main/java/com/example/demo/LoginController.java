package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginController {
    @FXML
    private Button LogIn;
    @FXML
    private Label WrongLogin;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;

    public RestaurantClient client;
    public Socket socket;
    public RestaurantDatabase restaurantDatabase;

    public void setClient(RestaurantClient client1, Socket s,RestaurantDatabase rd){
        client = client1;
        socket = s;
        restaurantDatabase = rd;
    }
    @FXML
    public void userLogIn() throws IOException, ClassNotFoundException {
        String name = Username.getText();
        ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
        writer.writeObject(name);
        ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
        Restaurant restaurant = (Restaurant) reader.readUnshared();
        //ServerAcceptThread
        //Server receives the Name of the Restaurant!
        //Server sends the object Restaurant to RestaurantClient
        //Server also added the Restaurant to a hashMap

        new RestaurantReadThread(restaurant,socket,restaurantDatabase,client);
        client.showRestaurantDetail(restaurant);
    }

}
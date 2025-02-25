package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FoodCustomerLoginController {

    @FXML
    private TextField Username;

    @FXML
    private Label WrongLogin;

    @FXML
    private Button LogIn;

    @FXML
    private PasswordField Password;

    public FoodCustomerClient client;

    public void setClient(FoodCustomerClient client1){
        client = client1;
    }

    @FXML
    public void userLogIn() throws IOException, ClassNotFoundException {
        //System.out.println("1"); //new
        //Socket socket = new Socket("127.0.0.1",33333);
        //System.out.println("1"); //new

        //Name taken from Input
        //String name = Username.getText();

        //ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
        //ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
        //writer.writeObject(name);

        client.showFoodDetail();


    }
}

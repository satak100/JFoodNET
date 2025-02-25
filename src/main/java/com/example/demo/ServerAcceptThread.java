package com.example.demo;

import javafx.application.Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerAcceptThread implements Runnable {
    Thread t;
    RestaurantDatabase restaurants;
    ServerSocket serverSocket;
    HashMap<String,Socket> restaurantHashMap;

    ServerAcceptThread(RestaurantDatabase restaurantDatabase) throws IOException {
        restaurants = restaurantDatabase;
        serverSocket = new ServerSocket(33333);
        restaurantHashMap = new HashMap<>();
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                //It's a blocking call - the program will stuck here until they find any socket connecting to it
                NetworkUtil networkUtil = new NetworkUtil(clientSocket);
                //opens a socket then
                Object obj = networkUtil.read();
                //receives a data

                if(obj instanceof String) {
                    String name = (String) obj;
                    restaurantHashMap.put(name, clientSocket);
                    List<Restaurant> res = restaurants.searchRestaurantsByName(name);

                    //why this is writing?
                    networkUtil.write(res.get(0));
                }
                else{
                    int id = (Integer) obj;
                    Food food = (Food) networkUtil.read();
                    System.out.println("Server receives");
                    String rName = restaurants.getRestaurantNameById(id);
                    Socket socket = restaurantHashMap.get(rName);
                    ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                    os.writeUnshared(food);
                    System.out.println("Server Sends");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.example.demo;

import javafx.application.Platform;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RestaurantReadThread implements Runnable{
    Thread t;
    Restaurant r;
    Socket socket;
    RestaurantDatabase restaurantDatabase;
    RestaurantClient rc;
    RestaurantReadThread(Restaurant r, Socket s,RestaurantDatabase rd,RestaurantClient rc){
        this.r = r;
        socket = s;
        restaurantDatabase = rd;
        this.rc = rc;
        t = new Thread(this);
        t.start();
    }
    public void run(){
        try {
            while(true) {
                ObjectInputStream oos = new ObjectInputStream(socket.getInputStream());
                Food f = (Food) oos.readUnshared();
                Pair<Integer,String> p = new Pair<>(f.getRestaurantId(),f.getName());
                int oc = restaurantDatabase.foodOrderMap.get(p);
                restaurantDatabase.foodOrderMap.put(p,oc + 1);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            rc.showRestaurantDetail(r);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
        }

    }

}

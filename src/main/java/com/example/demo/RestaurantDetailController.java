package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantDetailController {

    @FXML
    private Label Score;

    @FXML
    private Label Category;

    @FXML
    private Label Price;

    @FXML
    private Label Zipcode;

    @FXML
    private Label Id;

    @FXML
    private Label Name;

    @FXML
    private ListView<String> MenuList;

    public RestaurantClient client;
    public Restaurant r;

    public void setClient(RestaurantClient rc, Restaurant r, RestaurantDatabase rd){
        client = rc;
        this.r = r;
        List<String> categories = r.getCategories();
        String str = String.join(",",categories);

        Name.setText(r.getName());
        Id.setText(String.valueOf(r.getId()));
        Price.setText(String.valueOf(r.getPrice()));
        Score.setText(String.valueOf(r.getScore()));
        Category.setText(str);
        Zipcode.setText(r.getZipCode());

        HashMap<String,Integer> resFoodOrderMap = new HashMap<>();

        for (Map.Entry<Pair<Integer,String>, Integer> entry : rd.foodOrderMap.entrySet()) {
            if(entry.getKey().getKey() == r.getId()){
                resFoodOrderMap.put(entry.getKey().getValue(),entry.getValue());
            }
        }

        for (Map.Entry<String, Integer> entry : resFoodOrderMap.entrySet()) {
            MenuList.getItems().add(entry.getKey() + "," + entry.getValue());
        }

    }



}

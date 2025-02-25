package com.example.demo;

import java.util.*;
public interface RestaurantService {
    List<Restaurant> searchRestaurantsByName(String restaurantName);
    List<Restaurant> searchRestaurantsByScoreRange(double minScore, double maxScore);
    List<Restaurant> searchRestaurantsByCategory(String category);
    List<Restaurant> searchRestaurantsByPriceRange(String priceRange);
    List<Restaurant> searchRestaurantsByZipCode(String zipCode);
    Map<String, List<String>> getCategoryWiseRestaurantList();
    boolean addRestaurant(Restaurant restaurant);

    public List<Restaurant> getRestaurants();
    public void saveRestaurantsToFile(String fileName);

    //void DISPLAY_1();
}

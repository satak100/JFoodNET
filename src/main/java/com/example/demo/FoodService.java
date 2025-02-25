package com.example.demo;

import java.util.*;
public interface FoodService {
    List<Food> searchFoodItemsByName(String foodItemName);
    List<Food> searchFoodItemsByNameInRestaurant(String foodItemName, String restaurantName);
    List<Food> searchFoodItemsByCategory(String category);
    List<Food> searchFoodItemsByCategoryInRestaurant(String category, String restaurantName);
    List<Food> searchFoodItemsByPriceRange(double minPrice, double maxPrice);
    List<Food> searchFoodItemsByPriceRangeInRestaurant(double minPrice, double maxPrice, String restaurantName);
    List<Food> getCostliestFoodItemsInRestaurant(String restaurantName);
    Map<String, Integer> getRestaurantFoodItemCount();
    boolean addFoodItem(Food foodItem);
    void saveFoodItemsToFile(String fileName);

}

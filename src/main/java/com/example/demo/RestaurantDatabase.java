package com.example.demo;

import javafx.util.Pair;

import java.io.*;
import java.util.*;


public class RestaurantDatabase implements RestaurantService, FoodService, Serializable {
    public List<Restaurant> restaurants;
    public List<Food> foodItems;
    public HashMap<Pair<Integer,String>,Integer> foodOrderMap; //new

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurants;
    } //new
    public int last_added_ID=0;
    public RestaurantDatabase() {
        this.restaurants = new ArrayList<>();
        this.foodItems = new ArrayList<>();
        this.foodOrderMap = new HashMap<>(); //new
        loadRestaurantsFromFile("D:\\demo5\\src\\main\\java\\com\\example\\demo\\restaurant.txt");
        loadFoodItemsFromFile("D:\\demo5\\src\\main\\java\\com\\example\\demo\\menu.txt");

        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() > last_added_ID) {
               last_added_ID = restaurant.getId();
            }
        }
    }

    // Methods from RestaurantService interface
	//@Override
    public void DISPLAY(){
            System.out.println("Food Item List");
            //
        // List<Food> foodItems = foodService.getAllFoodItems();
        System.out.println(foodItems.size());
            for (Food food : foodItems) {
                System.out.println("Restaurant ID: " + food.getRestaurantId());
                System.out.println("Category: " + food.getCategory());
                System.out.println("Name: " + food.getName());
                System.out.println("Price: $" + food.getPrice());
                System.out.println();
            }
    }
    //@Override
    public void DISPLAY_1() {
        System.out.println("Restaurant List:");
        System.out.println(restaurants.size());
        for (Restaurant restaurant : restaurants) {
            System.out.println("ID: " + restaurant.getId());
            System.out.println("Name: " + restaurant.getName());
            System.out.println("Score: " + restaurant.getScore());
            System.out.println("Price Range: " + restaurant.getPrice());
            System.out.println("Zip Code: " + restaurant.getZipCode());
            System.out.println("Categories: ");
            for (String category : restaurant.getCategories()) {
                System.out.println("    "+ category);
            }
            System.out.println("\n");
        }
    }


    @Override
        public List<Restaurant> searchRestaurantsByName(String restaurantName) {
            List<Restaurant> matchingRestaurants = new ArrayList<>();
            for (Restaurant restaurant : restaurants) {
                /*if (restaurant.getName().equalsIgnoreCase(restaurantName)) {
                    matchingRestaurants.add(restaurant);
                }*/
                if(restaurant.getName().equals(restaurantName)){
                    matchingRestaurants.add(restaurant);
                    break;
                }
            }
            return matchingRestaurants;
        }

        @Override
        public List<Restaurant> searchRestaurantsByScoreRange(double minScore, double maxScore) {
            List<Restaurant> matchingRestaurants = new ArrayList<>();
            for (Restaurant restaurant : restaurants) {
                if (restaurant.getScore() >= minScore && restaurant.getScore() <= maxScore) {
                    matchingRestaurants.add(restaurant);
                }
            }
            return matchingRestaurants;
        }
    @Override
    public List<Restaurant> searchRestaurantsByCategory(String category) {
        List<Restaurant> matchingRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            for (String storedCategory : restaurant.getCategories()) {
                if (storedCategory.equalsIgnoreCase(category)) {
                    matchingRestaurants.add(restaurant);
                }
            }
        }
        return matchingRestaurants;
    }


    @Override
        public List<Restaurant> searchRestaurantsByPriceRange(String priceRange) {
            List<Restaurant> matchingRestaurants = new ArrayList<>();
            for (Restaurant restaurant : restaurants) {
                if (restaurant.getPrice().equalsIgnoreCase(priceRange)) {
                    matchingRestaurants.add(restaurant);
                }
            }
            return matchingRestaurants;
        }

        @Override
        public List<Restaurant> searchRestaurantsByZipCode(String zipCode) {
            List<Restaurant> matchingRestaurants = new ArrayList<>();
            for (Restaurant restaurant : restaurants) {
                if (restaurant.getZipCode().equals(zipCode)) {
                    matchingRestaurants.add(restaurant);
                }
            }
            return matchingRestaurants;
        }

        @Override
        public Map<String, List<String>> getCategoryWiseRestaurantList() {
            Map<String, List<String>> categoryWiseList = new HashMap<>();
            for (Restaurant restaurant : restaurants) {
                for (String category : restaurant.getCategories()) {
                    categoryWiseList.computeIfAbsent(category, k -> new ArrayList<>()).add(restaurant.getName());
                }
            }
            return categoryWiseList;
        }

        @Override
        public boolean addRestaurant(Restaurant restaurant) {
            boolean isDuplicate = false;

            for (Restaurant existingRestaurant : restaurants) {
                if (existingRestaurant.getName().equalsIgnoreCase(restaurant.getName())) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                restaurants.add(restaurant);
                return true;
            } else {
                return false;
            }
        }


    // Methods from FoodService interface
    public boolean addFoodItem(Food food) {
        for (Food existingFood : foodItems) {
            if(existingFood.getRestaurantId()== food.getRestaurantId()){
                if (existingFood.getCategory().equalsIgnoreCase(food.getCategory())){
                    if(existingFood.getName().equalsIgnoreCase(food.getName()))
                        return false;
                }
            }
            if(existingFood.getRestaurantId()== food.getRestaurantId()){
                if(!(existingFood.getCategory().equalsIgnoreCase(food.getCategory())))
                    foodItems.add(food);
                    Pair<Integer,String> p = new Pair<>(food.getRestaurantId(),food.getName());
                    foodOrderMap.put(p,0);
                    return true;
            }
            if(existingFood.getRestaurantId()== food.getRestaurantId()){
                if (existingFood.getCategory().equalsIgnoreCase(food.getCategory())){
                    if(!(existingFood.getName().equalsIgnoreCase(food.getName())))
                        foodItems.add(food);
                        Pair<Integer,String> p = new Pair<>(food.getRestaurantId(),food.getName());
                        foodOrderMap.put(p,0);

                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public List<Food> searchFoodItemsByName(String foodItemName) {
        List<Food> matchingFoodItems = new ArrayList<>();
        for (Food food : foodItems) {
            if (food.getName().toLowerCase().contains(foodItemName.toLowerCase())) {
                matchingFoodItems.add(food);
            }
        }
        return matchingFoodItems;
    }




    public List<Food> searchFoodItemsByNameInRestaurant(String foodItemName, String restaurantName) {
        List<Food> matchingFoodItems = new ArrayList<>();
        for (Food food : foodItems) {
            if (food.getName().toLowerCase().contains(foodItemName.toLowerCase()) &&
                    getRestaurantNameById(food.getRestaurantId()).equalsIgnoreCase(restaurantName)) {
                matchingFoodItems.add(food);
            }
        }
        return matchingFoodItems;
    }

    public String getRestaurantNameById(int restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == restaurantId) {
                return restaurant.getName();
            }
        }
        return "NO!";
    }

    @Override
    public List<Food> searchFoodItemsByCategory(String category) {
        List<Food> matchingFoodItems = new ArrayList<>();
        for (Food food : foodItems) {
            if (food.getCategory().toLowerCase().contains(category.toLowerCase())) {
                matchingFoodItems.add(food);
            }
        }
        return matchingFoodItems;
    }

    @Override
    public List<Food> searchFoodItemsByCategoryInRestaurant(String category, String restaurantName) {
        List<Food> matchingFoodItems = new ArrayList<>();
        for (Food food : foodItems) {
            if (food.getCategory().toLowerCase().contains(category.toLowerCase()) &&
                    getRestaurantNameById(food.getRestaurantId()).equalsIgnoreCase(restaurantName)) {
                matchingFoodItems.add(food);
            }
        }
        return matchingFoodItems;
    }

    @Override
        public List<Food> searchFoodItemsByPriceRange(double minPrice, double maxPrice) {
            List<Food> matchingFoodItems = new ArrayList<>();
            for (Food food : foodItems) {
                if (food.getPrice() >= minPrice && food.getPrice() <= maxPrice) {
                    matchingFoodItems.add(food);
                }
            }
            return matchingFoodItems;
        }

        @Override
        public List<Food> searchFoodItemsByPriceRangeInRestaurant(double minPrice, double maxPrice, String restaurantName) {
            List<Food> matchingFoodItems = new ArrayList<>();
            for (Food food : foodItems) {
                if (food.getPrice() >= minPrice && food.getPrice() <= maxPrice &&
                        getRestaurantNameById(food.getRestaurantId()).equalsIgnoreCase(restaurantName)) {
                    matchingFoodItems.add(food);
                }
            }
            return matchingFoodItems;
        }

        @Override
        public List<Food> getCostliestFoodItemsInRestaurant(String restaurantName) {
            List<Food> costliestFoodItems = new ArrayList<>();
            double maxPrice = Double.MIN_VALUE;

            for (Food food : foodItems) {
                if (getRestaurantNameById(food.getRestaurantId()).equalsIgnoreCase(restaurantName)) {
                    if (food.getPrice() > maxPrice) {
                        maxPrice = food.getPrice();
                        costliestFoodItems.clear();
                        costliestFoodItems.add(food);
                    } else if (food.getPrice() == maxPrice) {
                        costliestFoodItems.add(food);
                    }
                }
            }

            return costliestFoodItems;
        }

        @Override
        public Map<String, Integer> getRestaurantFoodItemCount() {
            Map<String, Integer> restaurantFoodItemCount = new HashMap<>();
            for (Restaurant restaurant : restaurants) {
                int itemCount = 0;
                for (Food food : foodItems) {
                    if (food.getRestaurantId() == restaurant.getId()) {
                        itemCount++;
                    }
                }
                restaurantFoodItemCount.put(restaurant.getName(), itemCount);
            }
            return restaurantFoodItemCount;
        }

    private void loadRestaurantsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double score = Double.parseDouble(parts[2]);
                    String price = parts[3];
                    String zipCode = parts[4];

                    List<String> categories = new ArrayList<>();
                    for (int i = 5; i < parts.length; i++) {
                        if (!parts[i].isEmpty()) {
                            categories.add(parts[i]);
                        }
                    }
                    Restaurant restaurant = new Restaurant(id, name, score, price, zipCode, categories);
                    restaurants.add(restaurant);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void loadFoodItemsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Assuming the format: RestaurantId,Category,Name,Price
                int restaurantId = Integer.parseInt(parts[0]);
                String category = parts[1];
                String name = parts[2];
                double price = Double.parseDouble(parts[3]);
                Food food = new Food(restaurantId, category, name, price);
                foodItems.add(food);
                Pair<Integer,String> p = new Pair<>(food.getRestaurantId(),food.getName());
                foodOrderMap.put(p,0);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
    @Override
    public void saveRestaurantsToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            //System.out.println(restaurants.size());
            for (Restaurant restaurant : restaurants) {
                //System.out.println("in1");
                // Assuming the format: Id,Name,Score,Price,ZipCode,Category1,Category2,Category3
                String line = restaurant.getId() + "," +
                        restaurant.getName() + "," +
                        restaurant.getScore() + "," +
                        restaurant.getPrice() + "," +
                        restaurant.getZipCode() + "," +
                        String.join(",", restaurant.getCategories());
                writer.write(line);
                writer.newLine();
                //System.out.println(restaurant.getId());
            }

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
    @Override
    public void saveFoodItemsToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Food food : foodItems) {
                // Assuming the format: RestaurantId,Category,Name,Price
                String line = food.getRestaurantId() + "," +
                        food.getCategory() + "," +
                        food.getName() + "," +
                        food.getPrice();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    public String getRestaurantNameWithId(int restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == restaurantId) {
                return restaurant.getName();
            }
        }
        return "!";
    }
}

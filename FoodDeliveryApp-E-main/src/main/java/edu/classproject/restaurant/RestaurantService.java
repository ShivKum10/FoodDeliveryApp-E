package edu.classproject.restaurant;

import edu.classproject.common.Money;

import java.util.List;

public interface RestaurantService {
    Restaurant createRestaurant(String name);

    MenuItem addMenuItem(String restaurantId, String itemName, Money price);

    Restaurant getRestaurant(String restaurantId);

    List<Restaurant> getAllRestaurants();
}

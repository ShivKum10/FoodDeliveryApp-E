package edu.classproject.restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {
    void save(Restaurant restaurant);

    Optional<Restaurant> findById(String restaurantId);

    List<Restaurant> findAll();
}

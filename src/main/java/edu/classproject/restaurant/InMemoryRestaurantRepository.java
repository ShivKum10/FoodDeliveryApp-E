package edu.classproject.restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryRestaurantRepository implements RestaurantRepository {
    private final Map<String, Restaurant> storage = new HashMap<>();

    @Override
    public void save(Restaurant restaurant) {
        storage.put(restaurant.restaurantId(), restaurant);
    }

    @Override
    public Optional<Restaurant> findById(String restaurantId) {
        return Optional.ofNullable(storage.get(restaurantId));
    }

    @Override
    public List<Restaurant> findAll() {
        return new ArrayList<>(storage.values());
    }
}

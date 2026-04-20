package edu.classproject.restaurant;

import edu.classproject.common.IdGenerator;
import edu.classproject.common.Money;

import java.util.List;

public class DefaultRestaurantService implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public DefaultRestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant createRestaurant(String name) {
        Restaurant restaurant = new Restaurant(IdGenerator.nextId("RST"), name);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @Override
    public MenuItem addMenuItem(String restaurantId, String itemName, Money price) {
        Restaurant restaurant = getRestaurant(restaurantId);
        MenuItem menuItem = new MenuItem(IdGenerator.nextId("ITM"), itemName, price);
        restaurant.addMenuItem(menuItem);
        restaurantRepository.save(restaurant);
        return menuItem;
    }

    @Override
    public Restaurant getRestaurant(String restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found: " + restaurantId));
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}

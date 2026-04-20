package edu.classproject.restaurant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Restaurant {
    private final String restaurantId;
    private final String name;
    private final Map<String, MenuItem> menuById = new HashMap<>();

    public Restaurant(String restaurantId, String name) {
        this.restaurantId = restaurantId;
        this.name = name;
    }

    public String restaurantId() {
        return restaurantId;
    }

    public String name() {
        return name;
    }

    public void addMenuItem(MenuItem item) {
        menuById.put(item.itemId(), item);
    }

    public MenuItem getMenuItem(String itemId) {
        MenuItem item = menuById.get(itemId);
        if (item == null) {
            throw new IllegalArgumentException("Menu item not found: " + itemId);
        }
        return item;
    }

    public Map<String, MenuItem> menu() {
        return Collections.unmodifiableMap(menuById);
    }
}

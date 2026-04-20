package edu.classproject.restaurantops;

import edu.classproject.order.Order;

import java.util.List;

public interface RestaurantOrderBoardService {
    List<Order> getActiveOrders(String restaurantId);

    void markPreparing(String orderId);

    void markReadyForPickup(String orderId);
}

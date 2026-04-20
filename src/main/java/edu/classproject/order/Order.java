package edu.classproject.order;

import edu.classproject.common.Money;
import edu.classproject.common.OrderStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final String orderId;
    private final String customerId;
    private final String restaurantId;
    private final List<OrderItem> items;
    private OrderStatus status;

    public Order(String orderId, String customerId, String restaurantId, List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.items = new ArrayList<>(items);
        this.status = OrderStatus.CREATED;
    }

    public String orderId() {
        return orderId;
    }

    public String customerId() {
        return customerId;
    }

    public String restaurantId() {
        return restaurantId;
    }

    public List<OrderItem> items() {
        return Collections.unmodifiableList(items);
    }

    public OrderStatus status() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Money totalAmount() {
        Money total = Money.of(0);
        for (OrderItem item : items) {
            total = total.add(item.lineTotal());
        }
        return total;
    }
}

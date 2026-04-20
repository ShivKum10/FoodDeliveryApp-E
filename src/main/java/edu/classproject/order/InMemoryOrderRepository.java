package edu.classproject.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<String, Order> storage = new HashMap<>();

    @Override
    public void save(Order order) {
        storage.put(order.orderId(), order);
    }

    @Override
    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(storage.get(orderId));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(storage.values());
    }
}

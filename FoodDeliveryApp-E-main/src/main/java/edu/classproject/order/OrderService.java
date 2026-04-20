package edu.classproject.order;

public interface OrderService {
    Order placeOrder(OrderRequest request);

    Order getOrder(String orderId);

    void updateStatus(String orderId, String status);
}

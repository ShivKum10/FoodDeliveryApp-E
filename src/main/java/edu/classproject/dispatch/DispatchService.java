package edu.classproject.dispatch;

public interface DispatchService {
    DispatchAssignment assignPartner(String orderId, String restaurantId, String customerId);
}

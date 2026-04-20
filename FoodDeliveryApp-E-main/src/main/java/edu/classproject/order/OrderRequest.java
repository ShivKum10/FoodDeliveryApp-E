package edu.classproject.order;

import java.util.List;

public record OrderRequest(String customerId, String restaurantId, List<OrderRequestItem> items) {
}

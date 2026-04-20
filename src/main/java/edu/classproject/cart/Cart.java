package edu.classproject.cart;

import java.util.List;

public record Cart(String cartId, String customerId, String restaurantId, List<CartLine> lines) {
}

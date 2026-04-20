package edu.classproject.order;

import edu.classproject.common.Money;

public class OrderItem {
    private final String itemId;
    private final String itemName;
    private final Money unitPrice;
    private final int quantity;

    public OrderItem(String itemId, String itemName, Money unitPrice, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.itemId = itemId;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String itemId() {
        return itemId;
    }

    public String itemName() {
        return itemName;
    }

    public Money unitPrice() {
        return unitPrice;
    }

    public int quantity() {
        return quantity;
    }

    public Money lineTotal() {
        return unitPrice.multiply(quantity);
    }
}

package edu.classproject.restaurant;

import edu.classproject.common.Money;

public class MenuItem {
    private final String itemId;
    private final String name;
    private final Money price;

    public MenuItem(String itemId, String name, Money price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    public String itemId() {
        return itemId;
    }

    public String name() {
        return name;
    }

    public Money price() {
        return price;
    }
}

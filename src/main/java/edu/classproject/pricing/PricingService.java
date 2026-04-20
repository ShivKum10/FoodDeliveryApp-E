package edu.classproject.pricing;

import edu.classproject.cart.Cart;

public interface PricingService {
    PriceBreakdown calculate(Cart cart, String couponCode);
}

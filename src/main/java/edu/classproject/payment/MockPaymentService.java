package edu.classproject.payment;

import java.math.BigDecimal;

import edu.classproject.common.IdGenerator;
import edu.classproject.common.Money;

/**
 * Mock implementation of PaymentService for testing and demonstration purposes.
 * This class simulates payment processing with basic validation rules.
 *
 * In a real application, this would be replaced with integration to actual payment gateways
 * such as Stripe, PayPal, or bank APIs. The processPayment method would make HTTP calls
 * to the payment provider's API, handle authentication, and process the response.
 */
public class MockPaymentService implements PaymentService {

    @Override
    public PaymentResult processPayment(String userId, Money amount) {

        // Check invalid user
        if (userId == null || userId.isBlank()) {
            return PaymentResult.declined("Invalid user");
        }

        // Amount must be positive
        if (amount.amount().compareTo(BigDecimal.ZERO) <= 0) {
            return PaymentResult.declined("Amount must be positive");
        }

        // Mock limit rule (simulating bank decline)
        if (amount.amount().compareTo(new BigDecimal("10000")) > 0) {
            return PaymentResult.declined("Payment declined: amount exceeds mock limit");
        }

        // Payment success
        return PaymentResult.approved(IdGenerator.nextId("TXN"));
    }
}
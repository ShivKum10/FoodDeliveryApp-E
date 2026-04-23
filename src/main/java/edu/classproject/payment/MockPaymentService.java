package edu.classproject.payment;

import java.math.BigDecimal;

import edu.classproject.common.IdGenerator;
import edu.classproject.common.Money;

public class MockPaymentService implements PaymentService {

    @Override
    public PaymentResult processPayment(String userId, Money amount) {

        // Auto-generate user ID if null or blank
        if (userId == null || userId.isBlank()) {
            userId = IdGenerator.nextId("USR");
            return PaymentResult.declined(userId, "Invalid user");
        }

        if (amount.amount().compareTo(BigDecimal.ZERO) <= 0) {
            return PaymentResult.declined(userId, "Amount must be positive");
        }

        if (amount.amount().compareTo(new BigDecimal("10000")) > 0) {
            return PaymentResult.declined(userId, "Payment declined: amount exceeds mock limit");
        }

        return PaymentResult.approved(userId, IdGenerator.nextId("TXN"));
    }
}
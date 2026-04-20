package edu.classproject.payment;

import edu.classproject.common.IdGenerator;
import edu.classproject.common.Money;

import java.math.BigDecimal;

public class MockPaymentService implements PaymentService {
    @Override
    public PaymentResult processPayment(String userId, Money amount) {
        if (amount.amount().compareTo(BigDecimal.ZERO) <= 0) {
            return PaymentResult.declined("Amount must be positive");
        }
        return PaymentResult.approved(IdGenerator.nextId("TXN"));
    }
}

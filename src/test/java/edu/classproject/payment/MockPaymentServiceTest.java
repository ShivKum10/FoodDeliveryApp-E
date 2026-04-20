package edu.classproject.payment;

import edu.classproject.common.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MockPaymentServiceTest {
    private final MockPaymentService paymentService = new MockPaymentService();

    @Test
    void processPayment_shouldApprove_whenAmountIsPositive() {
        PaymentResult result = paymentService.processPayment("USR-1", Money.of(12.0));
        assertTrue(result.success());
    }

    @Test
    void processPayment_shouldDecline_whenAmountIsZero() {
        PaymentResult result = paymentService.processPayment("USR-1", Money.of(0.0));
        assertFalse(result.success());
    }
}

package edu.classproject.payment;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.classproject.common.Money;

/**
 * Unit tests for MockPaymentService.
 */
public class MockPaymentServiceTest {

    private final PaymentService paymentService = new MockPaymentService();

    @Test
    public void testSuccessfulPayment() {
        Money amount = new Money(new BigDecimal("50.00"));
        PaymentResult result = paymentService.processPayment("user123", amount);

        assertTrue(result.success());
        assertNotNull(result.transactionId());
        assertEquals("Payment approved", result.message());
    }

    @Test
    public void testZeroAmount() {
        Money amount = new Money(BigDecimal.ZERO);
        PaymentResult result = paymentService.processPayment("user123", amount);

        assertFalse(result.success());
        assertNull(result.transactionId());
        assertEquals("Amount must be positive", result.message());
    }

    @Test
    public void testNegativeAmount() {
        Money amount = new Money(new BigDecimal("-10.00"));
        PaymentResult result = paymentService.processPayment("user123", amount);

        assertFalse(result.success());
        assertNull(result.transactionId());
        assertEquals("Amount must be positive", result.message());
    }

    @Test
    public void testInvalidUserId() {
        Money amount = new Money(new BigDecimal("50.00"));
        PaymentResult result = paymentService.processPayment("", amount);

        assertFalse(result.success());
        assertNull(result.transactionId());
        assertEquals("Invalid user", result.message());
    }
}
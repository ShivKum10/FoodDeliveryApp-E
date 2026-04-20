package edu.classproject.payment;

import java.math.BigDecimal;

import edu.classproject.common.Money;

/**
 * Demo class to demonstrate the PaymentService functionality.
 * This class shows how to use the PaymentService interface with MockPaymentService
 * to process payments in different scenarios.
 *
 * In a real application, this would be replaced with actual payment gateway integration.
 */
public class PaymentDemo {

    public static void main(String[] args) {
        // Create a PaymentService instance using MockPaymentService
        PaymentService paymentService = new MockPaymentService();

        // Scenario 1: Valid payment (positive amount)
        System.out.println("=== Scenario 1: Valid Payment ===");
        Money validAmount = new Money(new BigDecimal("100.00"));
        PaymentResult result1 = paymentService.processPayment("user123", validAmount);
        printResult(result1);

        // Scenario 2: Zero amount (should fail)
        System.out.println("\n=== Scenario 2: Zero Amount ===");
        Money zeroAmount = new Money(BigDecimal.ZERO);
        PaymentResult result2 = paymentService.processPayment("user123", zeroAmount);
        printResult(result2);

        // Scenario 3: Negative amount (should fail)
        System.out.println("\n=== Scenario 3: Negative Amount ===");
        Money negativeAmount = new Money(new BigDecimal("-50.00"));
        PaymentResult result3 = paymentService.processPayment("user123", negativeAmount);
        printResult(result3);

        // Bonus scenario: Invalid userId (should fail)
        System.out.println("\n=== Bonus Scenario: Invalid User ID ===");
        Money someAmount = new Money(new BigDecimal("50.00"));
        PaymentResult result4 = paymentService.processPayment("", someAmount);
        printResult(result4);
    }

    /**
     * Helper method to print payment result in a clear format.
     */
    private static void printResult(PaymentResult result) {
        System.out.println("Success: " + result.success());
        System.out.println("Transaction ID: " + result.transactionId());
        System.out.println("Message: " + result.message());
    }
}
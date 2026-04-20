package edu.classproject.payment;

/**
 * Immutable record representing the result of a payment processing operation.
 * Contains success status, transaction ID (if successful), and a descriptive message.
 *
 * This design follows the Value Object pattern, ensuring thread-safety and immutability.
 * Static factory methods provide clear ways to create success and failure results.
 */
public record PaymentResult(boolean success, String transactionId, String message) {
    public static PaymentResult approved(String transactionId) {
        return new PaymentResult(true, transactionId, "Payment approved");
    }

    public static PaymentResult declined(String reason) {
        return new PaymentResult(false, null, reason);
    }
}

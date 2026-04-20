package edu.classproject.payment;

public record PaymentResult(boolean success, String transactionId, String message) {
    public static PaymentResult approved(String transactionId) {
        return new PaymentResult(true, transactionId, "Payment approved");
    }

    public static PaymentResult declined(String reason) {
        return new PaymentResult(false, null, reason);
    }
}

package edu.classproject.payment;

import java.util.HashMap;

import edu.classproject.common.IdGenerator;

public record PaymentResult(boolean success, String transactionId, String message, HashMap<String, String> details) {

    public static PaymentResult approved(String userId, String transactionId) {
        HashMap<String, String> details = new HashMap<>();
        details.put("userId", userId);
        details.put("transactionId", transactionId);
        details.put("status", "approved");
        return new PaymentResult(true, transactionId, "Payment approved", details);
    }

    public static PaymentResult declined(String userId, String reason) {
        String transactionId = IdGenerator.nextId("TXN");
        HashMap<String, String> details = new HashMap<>();
        details.put("userId", userId != null ? userId : "unknown");
        details.put("transactionId", transactionId);
        details.put("reason", reason);
        return new PaymentResult(false, transactionId, reason, details);
    }
}
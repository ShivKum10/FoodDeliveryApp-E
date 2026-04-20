package edu.classproject.payment;

import edu.classproject.common.Money;

public interface PaymentService {
    PaymentResult processPayment(String userId, Money amount);
}

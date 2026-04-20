package edu.classproject.payment;

import edu.classproject.common.Money;

/**
 * Interface for payment processing services.
 * This abstraction allows different payment implementations (mock, real gateways)
 * to be used interchangeably, following the Dependency Inversion Principle.
 *
 * Real implementations would integrate with payment providers like Stripe, PayPal,
 * or bank APIs to process actual financial transactions.
 */
public interface PaymentService {
    PaymentResult processPayment(String userId, Money amount);
}
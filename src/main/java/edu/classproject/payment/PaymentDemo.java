package edu.classproject.payment;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import edu.classproject.common.IdGenerator;
import edu.classproject.common.Money;

public class PaymentDemo {

    private static final String LOG_FILE = "payment_log.txt";

    public static void main(String[] args) {

        PaymentService paymentService = new MockPaymentService();

        // Scenario 1: Valid payment
        System.out.println("=== Scenario 1: Valid Payment ===");
        String user1 = IdGenerator.nextId("USR");
        Money validAmount = new Money(new BigDecimal("100.00"));
        PaymentResult result1 = paymentService.processPayment(user1, validAmount);
        printResult(result1);
        saveToFile(result1);

        // Scenario 2: Zero amount
        System.out.println("\n=== Scenario 2: Zero Amount ===");
        String user2 = IdGenerator.nextId("USR");
        Money zeroAmount = new Money(BigDecimal.ZERO);
        PaymentResult result2 = paymentService.processPayment(user2, zeroAmount);
        printResult(result2);
        saveToFile(result2);

        // Scenario 3: Negative amount
        System.out.println("\n=== Scenario 3: Negative Amount ===");
        String user3 = IdGenerator.nextId("USR");
        Money negativeAmount = new Money(new BigDecimal("-50.00"));
        PaymentResult result3 = paymentService.processPayment(user3, negativeAmount);
        printResult(result3);
        saveToFile(result3);

        // Bonus: Invalid userId
        System.out.println("\n=== Bonus Scenario: Invalid User ID ===");
        Money someAmount = new Money(new BigDecimal("50.00"));
        PaymentResult result4 = paymentService.processPayment("", someAmount);
        printResult(result4);
        saveToFile(result4);

        System.out.println("\n✓ All payment details saved to: " + LOG_FILE);
    }

    private static void printResult(PaymentResult result) {
        System.out.println("Success: " + result.success());
        System.out.println("Transaction ID: " + result.transactionId());
        System.out.println("Message: " + result.message());
        System.out.println("Details: " + result.details());
    }

    private static void saveToFile(PaymentResult result) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println("--------------------------------------------------");
            writer.println("Timestamp     : " + timestamp);
            writer.println("Success       : " + result.success());
            writer.println("Transaction ID: " + result.transactionId());
            writer.println("Message       : " + result.message());
            writer.println("Details       : " + result.details());
            writer.println("--------------------------------------------------");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}
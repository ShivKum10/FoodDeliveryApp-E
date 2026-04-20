package edu.classproject.review;

public record Review(String reviewId, String orderId, String customerId, int rating, String comment) {
}

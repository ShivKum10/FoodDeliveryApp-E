package edu.classproject.support;

public record SupportTicket(String ticketId, String orderId, String customerId, String issueType, String status) {
}

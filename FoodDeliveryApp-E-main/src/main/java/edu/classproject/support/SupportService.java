package edu.classproject.support;

import java.util.List;

public interface SupportService {
    SupportTicket createTicket(String orderId, String customerId, String issueType);

    SupportTicket updateStatus(String ticketId, String status);

    List<SupportTicket> listByCustomer(String customerId);
}

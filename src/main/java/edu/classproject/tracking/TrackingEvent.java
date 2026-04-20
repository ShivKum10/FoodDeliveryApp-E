package edu.classproject.tracking;

import java.time.Instant;

public record TrackingEvent(String orderId, String status, String message, Instant occurredAt) {
}

package edu.classproject.auth;

import java.time.Instant;

public record AuthSession(String sessionId, String userId, Instant issuedAt, Instant expiresAt) {
}

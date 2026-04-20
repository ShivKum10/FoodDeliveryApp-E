package edu.classproject.auth;

public interface AuthService {
    AuthSession login(String email, String password);

    void logout(String sessionId);

    boolean isSessionActive(String sessionId);
}

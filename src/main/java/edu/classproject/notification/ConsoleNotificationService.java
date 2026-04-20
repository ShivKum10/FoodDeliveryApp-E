package edu.classproject.notification;

public class ConsoleNotificationService implements NotificationService {
    @Override
    public void notifyUser(String userId, String message) {
        System.out.println("[Notification] user=" + userId + " message=" + message);
    }
}

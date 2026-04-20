package edu.classproject.user;

import java.util.List;

public interface UserService {
    User registerCustomer(String name, String email);

    User getUser(String userId);

    List<User> getAllUsers();
}

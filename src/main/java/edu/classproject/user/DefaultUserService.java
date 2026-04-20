package edu.classproject.user;

import edu.classproject.common.IdGenerator;

import java.util.List;

public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerCustomer(String name, String email) {
        User user = new User(IdGenerator.nextId("USR"), name, email, UserRole.CUSTOMER);
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

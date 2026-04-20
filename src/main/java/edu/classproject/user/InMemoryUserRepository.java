package edu.classproject.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> storage = new HashMap<>();

    @Override
    public void save(User user) {
        storage.put(user.userId(), user);
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(storage.get(userId));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(storage.values());
    }
}

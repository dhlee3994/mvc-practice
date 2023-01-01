package org.example.mvc.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.example.mvc.model.User;

public class UserRepository {

    private static final Map<String, User> users = new HashMap<>();

    public static void save(final User user) {
        users.put(user.getId(), user);
    }

    public static Collection<User> findAll() {
        return users.values();
    }
}

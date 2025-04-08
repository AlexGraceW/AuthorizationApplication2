package org.example.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<String, String> users = Map.of(
            "admin", "admin",
            "user", "password"
    );

    public List<String> getUserAuthorities(String user, String password) {
        if (users.containsKey(user) && users.get(user).equals(password)) {
            return List.of("READ", "WRITE");
        }
        return List.of();
    }
}

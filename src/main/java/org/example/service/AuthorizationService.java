package org.example.service;

import org.example.exception.InvalidCredentials;
import org.example.exception.UnauthorizedUser;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationService {
    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAuthorities(String user, String password) {
        if (user == null || password == null) {
            throw new InvalidCredentials("User name or password is empty");
        }

        var authorities = userRepository.getUserAuthorities(user, password);
        if (authorities.isEmpty()) {
            throw new UnauthorizedUser("Unknown user " + user);
        }

        return authorities;
    }
}

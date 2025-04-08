package org.example.controller;

import org.example.model.User;
import org.example.resolver.UserArgumentResolver;
import org.example.service.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthorizationService service;

    public LoginController(AuthorizationService service) {
        this.service = service;
    }

    @PostMapping
    public String login(@Valid User user) {
        service.getAuthorities(user.getUser(), user.getPassword());
        return "redirect:/conf.html";
    }
}

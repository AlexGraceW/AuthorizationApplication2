package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigPageController {
    @GetMapping("/conf.html")
    public String confPage() {
        return "conf";
    }
}

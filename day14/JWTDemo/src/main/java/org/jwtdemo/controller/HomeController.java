package org.jwtdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "Welcome! You accessed protected API using valid JWT.";
    }
}

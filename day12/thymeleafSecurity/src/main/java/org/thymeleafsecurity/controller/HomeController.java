package org.thymeleafsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

        @GetMapping("/")
        public String home() {
            return "Welcome to the Home Page!";
        }

        @GetMapping("/user")
        public String userPage() {
            return "Welcome to the User Page!";
        }

        @GetMapping("/admin")
        public String adminPage() {
            return "Welcome to the Admin Page!";
        }
}

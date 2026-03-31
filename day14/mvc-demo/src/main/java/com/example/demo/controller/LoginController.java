package com.example.demo.controller;
import com.example.demo.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class LoginController {
@GetMapping("/login")
    public String showLoginPage() {
        return "login"; // loads login.html
    }
@PostMapping("/login")
    public String processLogin(User user, Model model) {
if (user.getUsername().equals("admin")
                && user.getPassword().equals("1234")) {
model.addAttribute("username", user.getUsername());
            return "welcome"; // welcome.html
        }
model.addAttribute("error", "Invalid username or password");
        return "login";
    }
}

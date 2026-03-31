package org.jwtdemo.service;

import org.springframework.stereotype.Service;
@Service
public class AuthService {
    public boolean authenticate(String username, String password) {
        return "ravi".equals(username) && "12345".equals(password);
    }
}


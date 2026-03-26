package org.crudswagger.controller;

import org.crudswagger.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ArrayList<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        users.add(user);
        return "User created successfully";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setName(updatedUser.getName());
                return "User updated successfully";
            }
        }
        return "User not found";
    }

}

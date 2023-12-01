package com.example.techolution.controller;

import com.example.techolution.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    public UserController() {

    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return Collections.emptyList();
    }

    // Get a specific user by ID
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return null;
    }

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return null;
    }

    // Update an existing user
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        return null;
    }

    // Delete a user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {

    }
}

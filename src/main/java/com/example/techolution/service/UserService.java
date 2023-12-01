package com.example.techolution.service;


import com.example.techolution.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long userId);

    User addUser(User user);

    User updateUser(Long userId, User user);

    void deleteUserById(Long userId);

}

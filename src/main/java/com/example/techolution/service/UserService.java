package com.example.techolution.service;


import com.example.techolution.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long userId);

    User save(User user);

    User updateUser(Long userId, User user);

    void deleteById(Long userId);

}

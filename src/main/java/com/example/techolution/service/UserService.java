package com.example.techolution.service;


import com.example.techolution.dto.UserResponse;
import com.example.techolution.entity.User;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long userId);

    UserResponse addUser(User user);

    UserResponse updateUser(Long userId, User user);

    UserResponse deleteUserById(Long userId);

}

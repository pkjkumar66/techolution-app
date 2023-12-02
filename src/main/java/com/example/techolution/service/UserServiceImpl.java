package com.example.techolution.service;

import com.example.techolution.dto.UserErrorResponse;
import com.example.techolution.dto.UserResponse;
import com.example.techolution.entity.User;
import com.example.techolution.exception.AccessDeniedException;
import com.example.techolution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> response = users.stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .userName(user.getUserName())
                        .password(user.getPassword())
                        .build())
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public UserResponse getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        UserResponse response = UserResponse.builder().build();
        if (user.isPresent()) {
            User existingUser = user.get();
            response.setId(existingUser.getId());
            response.setUserName(existingUser.getUserName());
            response.setPassword(existingUser.getPassword());
        } else {
            UserErrorResponse error = UserErrorResponse.builder()
                    .errorCode("404")
                    .errorMessage("User not found with ID: " + userId)
                    .build();
            response.setError(error);
        }
        return response;

    }

    @Override
    public UserResponse addUser(User user) {
        UserResponse response = UserResponse.builder().build();
        try {
            checkAuthorizationForRole("MANAGER", "ADMIN");
            User savedUser = userRepository.save(user);
            response.setId(savedUser.getId());
            response.setUserName(savedUser.getUserName());
            response.setPassword(savedUser.getPassword());
        } catch (AccessDeniedException e) {
            UserErrorResponse error = UserErrorResponse.builder()
                    .errorMessage(e.getMessage())
                    .build();
            response.setError(error);
        }
        return response;

    }

    public UserResponse updateUser(Long userId, User user) {
        UserResponse response = UserResponse.builder().build();
        try {
            checkAuthorizationForRole("MANAGER", "ADMIN");
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                User existingUser = optionalUser.get();
                response.setId(existingUser.getId());
                response.setUserName(existingUser.getUserName());
                response.setPassword(existingUser.getPassword());
            } else {
                UserErrorResponse error = UserErrorResponse.builder()
                        .errorCode("404")
                        .errorMessage("User not found with ID: " + userId)
                        .build();
                response.setError(error);
            }
        } catch (AccessDeniedException e) {
            UserErrorResponse error = UserErrorResponse.builder()
                    .errorMessage(e.getMessage())
                    .build();
            response.setError(error);
        }
        return response;
    }

    @Override
    public UserResponse deleteUserById(Long userId) {
        UserResponse response = UserResponse.builder().build();
        try {
            checkAuthorizationForRole("ADMIN");
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                userRepository.deleteById(userId);
            } else {
                UserErrorResponse error = UserErrorResponse.builder()
                        .errorCode("404")
                        .errorMessage("User not found with ID: " + userId)
                        .build();
                response.setError(error);
            }
        } catch (AccessDeniedException e) {
            UserErrorResponse error = UserErrorResponse.builder()
                    .errorMessage(e.getMessage())
                    .build();
            response.setError(error);
        }

        return response;
    }

    private void checkAuthorizationForRole(String... allowedRoles) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (String allowedRole : allowedRoles) {
            if (authentication.getAuthorities().stream().anyMatch(authority ->
                    authority.getAuthority().equals("ROLE_" + allowedRole))) {
                return;
            }
        }
        throw new AccessDeniedException("Access denied. User does not have the required role.");
    }
}







package com.example.techolution.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String userName;
    private String password;
    private UserErrorResponse error;
}

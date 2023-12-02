package com.example.techolution.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class UserErrorResponse {
    private String message;

    public UserErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

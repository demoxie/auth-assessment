package com.saultech.authassessment.dto.ApiResponse;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
    private String message;
    private HttpStatus status;
    private int statusCode;
    private Object data;
}

package com.saultech.authassessment.service;


import com.saultech.authassessment.dto.SignupDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.concurrent.CompletableFuture;

public interface AuthenticationService {
    CompletableFuture<Object> validateFieldsAsync(SignupDTO dto);

    String generateJwtToken(SignupDTO dto);

    boolean validateJwtToken(String token);

    String getJwtFromRequest(HttpServletRequest request);
}

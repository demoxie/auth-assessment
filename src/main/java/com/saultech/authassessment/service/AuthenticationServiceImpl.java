package com.saultech.authassessment.service;

import com.saultech.authassessment.dto.SignupDTO;
import com.saultech.authassessment.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService{
    private final JwtUtils jwtUtils;

    @Override
    @Async
    public CompletableFuture<Object> validateFieldsAsync(SignupDTO dto) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<SignupDTO>> violations = validator.validate(dto);
        if (violations.isEmpty()) {
            log.info("No violations");
            return CompletableFuture.completedFuture(true);
        }
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<SignupDTO> violation : violations) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return CompletableFuture.completedFuture(errors);
    }

    @Override
    public String generateJwtToken(SignupDTO dto) {
        Object response = validateFieldsAsync(dto).join();

        if (response instanceof Boolean) {
            return jwtUtils.generateJwtToken(dto.getUsername());
        }
        return null;
    }

    @Override
    public boolean validateJwtToken(String token) {
        return jwtUtils.validateJwtToken(token);
    }

    @Override
    public String getJwtFromRequest(HttpServletRequest request) {
        return jwtUtils.getJwtFromRequest(request);
    }
}

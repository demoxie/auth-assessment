package com.saultech.authassessment.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class DateValidator implements ConstraintValidator<ValidDate, LocalDate> {

    @Override
    public void initialize(ValidDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        log.info("Value: {}", value);
        return !value.toString().isEmpty() && value.isBefore(LocalDate.now().minusYears(16));
    }
}
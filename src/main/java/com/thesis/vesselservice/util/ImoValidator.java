package com.thesis.vesselservice.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImoValidator implements ConstraintValidator<ValidImo, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || !value.matches("\\d{7}")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 6; i++) {
            int digit = Character.getNumericValue(value.charAt(i));
            sum += digit * (7 - i);
        }

        int checkDigit = Character.getNumericValue(value.charAt(6));
        return sum % 10 == checkDigit;
    }
}

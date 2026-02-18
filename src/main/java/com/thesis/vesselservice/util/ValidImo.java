package com.thesis.vesselservice.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImoValidator.class)
public @interface ValidImo {
    String message() default "Invalid IMO number integrity";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

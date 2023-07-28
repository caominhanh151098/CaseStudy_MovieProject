package com.example.casestudy_movieproject.validation.valiQuality;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidateQuality.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidQuality {
    String message() default "Đừng nghịch nữa";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

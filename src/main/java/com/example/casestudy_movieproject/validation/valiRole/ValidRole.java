package com.example.casestudy_movieproject.validation.valiRole;

import com.example.casestudy_movieproject.validation.valiQuality.ValidateQuality;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidateRole.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRole {
    String message() default "Đừng nghịch nữa";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

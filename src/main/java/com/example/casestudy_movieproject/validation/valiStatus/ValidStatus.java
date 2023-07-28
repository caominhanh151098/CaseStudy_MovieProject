package com.example.casestudy_movieproject.validation.valiStatus;

import com.example.casestudy_movieproject.validation.valiRole.ValidateRole;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidateStatus.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStatus {
    String message() default "Đừng nghịch nữa";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

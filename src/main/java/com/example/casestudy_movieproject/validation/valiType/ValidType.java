package com.example.casestudy_movieproject.validation.valiType;

import com.example.casestudy_movieproject.validation.valiStatus.ValidateStatus;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidateType.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidType {
    String message() default "Đừng nghịch nữa";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package com.example.casestudy_movieproject.validation.valiType;

import com.example.casestudy_movieproject.model.enums.EType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidateType implements ConstraintValidator<ValidType,String> {
    @Override
    public void initialize(ValidType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for (var value: EType.values()) {
            if (Objects.equals(s, value.name())){
                return true;
            }
        }
        return false;
    }
}

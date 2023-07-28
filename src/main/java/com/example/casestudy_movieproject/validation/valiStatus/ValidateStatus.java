package com.example.casestudy_movieproject.validation.valiStatus;

import com.example.casestudy_movieproject.model.enums.EStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidateStatus implements ConstraintValidator<ValidStatus,String> {
    @Override
    public void initialize(ValidStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for (var value: EStatus.values()) {
            if (Objects.equals(s, value.name())){
                return  true;
            }
        }
        return false;
    }
}

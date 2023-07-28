package com.example.casestudy_movieproject.validation.valiRole;

import com.example.casestudy_movieproject.model.enums.ERoleEKip;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidateRole implements ConstraintValidator<ValidRole,String> {
    @Override
    public void initialize(ValidRole constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for (var value: ERoleEKip.values()) {
            if (Objects.equals(s, value.name())){
                return true;
            }
        }
        return false;
    }
}

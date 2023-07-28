package com.example.casestudy_movieproject.validation.valiEkip;

import com.example.casestudy_movieproject.model.enums.ERoleEKip;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class ValidateEkip implements ConstraintValidator<ValidEkip,String> {
    @Override
    public void initialize(ValidEkip constraintAnnotation) {
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

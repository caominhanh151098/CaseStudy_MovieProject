package com.example.casestudy_movieproject.validation.valiQuality;

import com.example.casestudy_movieproject.model.enums.EQuality;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidateQuality implements ConstraintValidator<ValidQuality,String> {


    @Override
    public void initialize(ValidQuality constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for (var value:EQuality.values()) {
            if (Objects.equals(s, value.name())) {
                return true;
            }
        }
        return false;
    }

    public boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

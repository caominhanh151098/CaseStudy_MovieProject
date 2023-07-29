package com.example.casestudy_movieproject.validation.valiGenre;

import com.example.casestudy_movieproject.repository.GenreRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidateGenre implements ConstraintValidator<ValidGenre,String> {


    private final GenreRepository genreRepository;
    @Override
    public void initialize(ValidGenre constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (isNumeric(s)){
            for (var value: genreRepository.findAll()){
                if(Integer.parseInt(s) == value.getId()){
                    return true;
                }
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

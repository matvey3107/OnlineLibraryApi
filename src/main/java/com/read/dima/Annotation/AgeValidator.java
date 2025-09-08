package com.read.dima.Annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<anotationDate, LocalDate> {
    private int min;
    private  int max;

    @Override
    public void initialize(anotationDate constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if(localDate == null){
            return true;

        }
        int age = Period.between(localDate,LocalDate.now()).getYears();
        return age >= min && age<= max;
    }
}

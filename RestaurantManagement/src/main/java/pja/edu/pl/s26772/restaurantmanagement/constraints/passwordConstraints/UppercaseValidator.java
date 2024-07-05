package pja.edu.pl.s26772.restaurantmanagement.constraints.passwordConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UppercaseValidator implements ConstraintValidator<HasUppercase,String> {
    private int numberOfUppercase;
    @Override
    public void initialize(HasUppercase constraintAnnotation) {
        numberOfUppercase = constraintAnnotation.numberOfUppercase();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isEmpty())
            return true;

        int count = (int) s.chars().filter(Character::isUpperCase).count();
        return count >= numberOfUppercase;
    }
}

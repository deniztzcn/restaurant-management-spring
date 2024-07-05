package pja.edu.pl.s26772.restaurantmanagement.constraints.passwordConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LowercaseValidator implements ConstraintValidator<HasLowercase,String> {
    private int numberOfLowercase;

    @Override
    public void initialize(HasLowercase constraintAnnotation) {
        numberOfLowercase = constraintAnnotation.numberOfLowercase();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isEmpty())
            return true;

        int count = (int) s.chars().filter(Character::isLowerCase).count();
        return count >= numberOfLowercase;
    }
}

package pja.edu.pl.s26772.restaurantmanagement.constraints.passwordConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DigitValidator implements ConstraintValidator<HasDigit,String> {
    private int numberOfDigits;
    @Override
    public void initialize(HasDigit constraintAnnotation) {
        numberOfDigits = constraintAnnotation.numberOfDigits();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isEmpty())
            return true;
        int count = (int) s.chars().filter(Character::isDigit).count();

        return count >= numberOfDigits;
    }
}

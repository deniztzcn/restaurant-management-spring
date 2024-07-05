package pja.edu.pl.s26772.restaurantmanagement.constraints.passwordConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SpecialCharacterValidator implements ConstraintValidator<HasSpecialCharacters,String> {
    private int numberOfSpecialCharacters;

    @Override
    public void initialize(HasSpecialCharacters constraintAnnotation) {
        numberOfSpecialCharacters = constraintAnnotation.numberOfSpecialCharacters();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isEmpty())
            return true;

        int count = (int) s.chars().filter(Character::isLetterOrDigit).count();
        count = s.length() - count;
        return count >= numberOfSpecialCharacters;
    }
}

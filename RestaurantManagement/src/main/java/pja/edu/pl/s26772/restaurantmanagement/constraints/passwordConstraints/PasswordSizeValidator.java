package pja.edu.pl.s26772.restaurantmanagement.constraints.passwordConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordSizeValidator implements ConstraintValidator<PasswordSize,String> {
    private int minSize;
    private int maxSize = Integer.MAX_VALUE;
    @Override
    public void initialize(PasswordSize constraintAnnotation) {
        minSize = constraintAnnotation.minSize();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isEmpty())
            return true;

        return s.length() >= minSize && s.length() <= maxSize;
    }
}

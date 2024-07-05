package pja.edu.pl.s26772.restaurantmanagement.constraints.uniqueConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pja.edu.pl.s26772.restaurantmanagement.auth.UserService;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {
    private UserService userService;

    public UniqueEmailValidator() {}
    @Autowired
    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {}

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.isEmailExists(s);
    }
}

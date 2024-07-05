package pja.edu.pl.s26772.restaurantmanagement.constraints.passwordConstraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SpecialCharacterValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HasSpecialCharacters {
    String message() default "the password must have at least {numberOfSpecialCharacters}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int numberOfSpecialCharacters();
}

package pja.edu.pl.s26772.restaurantmanagement.constraints.passwordConstraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UppercaseValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HasUppercase {
    String message() default "the password must have at least {numberOfUppercase}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int numberOfUppercase();
}

package pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomerExistsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerExists {
    String message() default "customer is not found";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

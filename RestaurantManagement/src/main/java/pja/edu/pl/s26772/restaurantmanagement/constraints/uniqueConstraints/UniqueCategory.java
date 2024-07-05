package pja.edu.pl.s26772.restaurantmanagement.constraints.uniqueConstraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueCategoryValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCategory {
    String message() default "category already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
